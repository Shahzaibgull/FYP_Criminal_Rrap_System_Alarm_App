package com.example.ctsalarm.Side_Navigation;

import android.os.Bundle;
import android.view.Window;

import com.example.ctsalarm.R;
import com.example.ctsalarm.databinding.ActivityIntruderAlertBinding;


public class IntruderAlert extends DrawerBaseActivity {

    Window window;
    ActivityIntruderAlertBinding activityIntruderAlertBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityIntruderAlertBinding = ActivityIntruderAlertBinding.inflate(getLayoutInflater());
        setContentView(activityIntruderAlertBinding.getRoot());
        allocateActivityTitle("Intruder Alert");

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
    }
}