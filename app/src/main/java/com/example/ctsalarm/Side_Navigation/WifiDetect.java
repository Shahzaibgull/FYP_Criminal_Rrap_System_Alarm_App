package com.example.ctsalarm.Side_Navigation;

import android.os.Bundle;
import android.view.Window;

import com.example.ctsalarm.R;
import com.example.ctsalarm.databinding.ActivityHomeScreenBinding;
import com.example.ctsalarm.databinding.ActivityWifiDetectBinding;

public class WifiDetect extends DrawerBaseActivity{

    Window window;
    ActivityWifiDetectBinding activityWifiDetectBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWifiDetectBinding = ActivityWifiDetectBinding.inflate(getLayoutInflater());
        setContentView(activityWifiDetectBinding.getRoot());
        allocateActivityTitle("Wifi Detect");

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
    }
}