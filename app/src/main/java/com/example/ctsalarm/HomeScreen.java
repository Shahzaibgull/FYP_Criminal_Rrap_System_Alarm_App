package com.example.ctsalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ctsalarm.Features_Alarm.MainFeatures;
import com.example.ctsalarm.Settings.NodeMCU;
import com.example.ctsalarm.Side_Navigation.DrawerBaseActivity;
import com.example.ctsalarm.Side_Navigation.Settings;
import com.example.ctsalarm.Sign_Up_In.OTP_Code;
import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.Sign_Up_In.SignUp;
import com.example.ctsalarm.databinding.ActivityHomeScreenBinding;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class HomeScreen extends DrawerBaseActivity {

    //ViewStub Intruder_Image;
    Window window;
    ActivityHomeScreenBinding activityHomeScreenBinding;
    TextView activate,See_Activate_Alarm;
    ImageView DVD_Image,Speedo_Image,OpenDoor_Image,sidemirror_Image,Intruder_Image;
    public boolean checkDVD_Activated,checkSpeedo_Activated,checkOpenDoor_Activated,checkSideMirror_Activated,checkIntruder_Activated;
    private SharedPreferences.OnSharedPreferenceChangeListener checkListener1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeScreenBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityHomeScreenBinding.getRoot());
        allocateActivityTitle("Anti Theft Alarm");

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        See_Activate_Alarm=findViewById(R.id.textTapToSeeActivatedAlarm);


        DVD_Image=findViewById(R.id.DVDImage);
        Speedo_Image=findViewById(R.id.SpeedoImage);
        OpenDoor_Image=findViewById(R.id.OpenDoorImage);
        sidemirror_Image=findViewById(R.id.SideMirrorImage);
        Intruder_Image=findViewById(R.id.IntruderImage);
        //Intruder_Image=findViewById(R.id.intruder_viewStub);
        //Intruder_Image.inflate();

        SharedPreferences settingPrefs1=PreferenceManager.getDefaultSharedPreferences(this);
        checkListener1=new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if(key.equals("dvd_key"))
                {
                    checkDVD_Activated=sharedPreferences.getBoolean("dvd_key",false);
                    if(checkDVD_Activated)
                    {
                        DVD_Image.setVisibility(View.VISIBLE);
                    }
                    else {
                        DVD_Image.setVisibility(View.GONE);
                    }
                }
                if (key.equals("speedo_Key"))
                {
                    checkSpeedo_Activated=sharedPreferences.getBoolean("speedo_Key",false);
                    if(checkSpeedo_Activated)
                    {
                        Speedo_Image.setVisibility(View.VISIBLE);
                    }
                    else {
                        Speedo_Image.setVisibility(View.GONE);
                    }
                }
                if (key.equals("opendoor_Key"))
                {
                    checkOpenDoor_Activated=sharedPreferences.getBoolean("opendoor_Key",false);
                    if(checkOpenDoor_Activated)
                    {
                        OpenDoor_Image.setVisibility(View.VISIBLE);
                    }
                    else {
                        OpenDoor_Image.setVisibility(View.GONE);
                    }
                }
                if (key.equals("sidemirror_Key"))
                {
                    checkSideMirror_Activated=sharedPreferences.getBoolean("sidemirror_Key",false);
                    if(checkSideMirror_Activated)
                    {
                        sidemirror_Image.setVisibility(View.VISIBLE);
                    }
                    else {
                        sidemirror_Image.setVisibility(View.GONE);
                    }
                }
                if (key.equals("intruder_Key"))
                {
                    checkIntruder_Activated=sharedPreferences.getBoolean("intruder_Key",false);
                    if(checkIntruder_Activated)
                    {
                        Intruder_Image.setVisibility(View.VISIBLE);
                    }
                    else {
                        Intruder_Image.setVisibility(View.GONE);
                    }
                }
            }
        };
        settingPrefs1.registerOnSharedPreferenceChangeListener(checkListener1);


        activate=findViewById(R.id.textActivate);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this, MainFeatures.class);
                startActivity(intent);
            }
        });

        See_Activate_Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this, NodeMCU.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                startActivity(new Intent(this, Settings.class));

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}