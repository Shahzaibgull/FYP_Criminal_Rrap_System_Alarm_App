package com.example.ctsalarm.Side_Navigation;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.R;
import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.menu_drawer_open, R.string.menu_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.teal_200));
        toggle.getDrawerArrowDrawable().setBarThickness(10);
        toggle.getDrawerArrowDrawable().setBarLength(60);

        toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId())
        {

            case R.id.home:
                startActivity(new Intent(this, HomeScreen.class));
                overridePendingTransition(0,0);
                break;
            case R.id.wifi:
                startActivity(new Intent(this, WifiDetect.class));
                overridePendingTransition(0,0);
                break;
            case R.id.intruderAlert:
                startActivity(new Intent(this, IntruderAlert.class));
                overridePendingTransition(0,0);
                break;
            case R.id.notification:
                startActivity(new Intent(this, Notification.class));
                overridePendingTransition(0,0);
                break;
            case R.id.share:
                startActivity(new Intent(this, Share.class));
                overridePendingTransition(0,0);
                break;
            case R.id.setting:
                startActivity(new Intent(this, Settings.class));
                overridePendingTransition(0,0);
                break;
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(titleString);
        }
    }
}
