package com.example.ctsalarm.Side_Navigation;

import android.os.Bundle;
import android.view.Window;

import com.example.ctsalarm.R;
import com.example.ctsalarm.databinding.ActivityHomeScreenBinding;
import com.example.ctsalarm.databinding.ActivityNotificationBinding;

public class Notification extends DrawerBaseActivity {

    Window window;
    ActivityNotificationBinding activityNotificationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotificationBinding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(activityNotificationBinding.getRoot());
        allocateActivityTitle("Notification");

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
    }
}