package com.example.ctsalarm.Side_Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.ctsalarm.Forgot_Password.New_Password;
import com.example.ctsalarm.Forgot_Password.PhoneNo_Use;
import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Settings.Profile;
import com.example.ctsalarm.Settings.SetAlarmTone;
import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.databinding.ActivityHomeScreenBinding;
import com.example.ctsalarm.databinding.ActivitySettingsBinding;


public class Settings extends DrawerBaseActivity {

    Window window;
    TextView SetAlarmTone,Profile,SetPinCode,Uninstall;
    ActivitySettingsBinding activitySettingsBinding;
    SwitchCompat switcher;
    boolean nightMode;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor;
    AudioManager audioManager;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        allocateActivityTitle("Settings");
        window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        int currentVolume=audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVolume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        switcher = findViewById(R.id.switchButton);
        //we use sharedPreference to save mode if exit the app and go back again
        sharedPreferences1 = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences1.getBoolean("night", false); //light mode is the default mode
        if(nightMode)
        {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightMode)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=sharedPreferences1.edit();
                    editor.putBoolean("night",false);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=sharedPreferences1.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();
            }
        });

        SetAlarmTone = findViewById(R.id.setAlarmTone);
        SetAlarmTone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, com.example.ctsalarm.Settings.SetAlarmTone.class);
                startActivity(intent);
            }
        });

        SetPinCode = findViewById(R.id.setPinCode);
        SetPinCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code2="1";
                Intent intent = new Intent(Settings.this, PhoneNo_Use.class);
                intent.putExtra("keyCode",code2);
                startActivity(intent);
            }
        });

        Profile = findViewById(R.id.profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, com.example.ctsalarm.Settings.Profile.class);
                startActivity(intent);
            }
        });

        Uninstall=findViewById(R.id.uninstall);
        Uninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:com.example.ctsalarm"));
                startActivity(intent);
            }
        });
    }
}