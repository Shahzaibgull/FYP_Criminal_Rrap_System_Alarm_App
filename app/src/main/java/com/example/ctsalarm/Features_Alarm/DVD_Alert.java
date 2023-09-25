package com.example.ctsalarm.Features_Alarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Settings.SetAlarmTone;

public class DVD_Alert extends AppCompatActivity {

    Window window;
    public SwitchCompat dvdSwitch1,dvdSwitch2;
    Button DVD_AlarmSettings,BackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvd_alert);
        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        getFragmentManager()
                .beginTransaction()
                .add(R.id.preference_dvd_activated,new SettingFragment())
                .add(R.id.preference_dvd_ringAlarm,new SettingFragment())
                .commit();

        DVD_AlarmSettings=findViewById(R.id.dvd_Alarm_Setting);
        DVD_AlarmSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeDVD2="1";
                Intent intent=new Intent(DVD_Alert.this, SetAlarmTone.class);
                intent.putExtra("keyDVD",codeDVD2);
                startActivity(intent);
            }
        });

        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DVD_Alert.this, MainFeatures.class);
                startActivity(intent);
            }
        });


        //dvdSwitch1=findViewById(R.id.dvdActivate);
        //dvdSwitch2=findViewById(R.id.dvdRingAlarm);

        /*
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        dvdSwitch1.setChecked(sharedPreferences.getBoolean("DVD Key",false));
        dvdSwitch2.setChecked(sharedPreferences.getBoolean("DVD Ring Alarm",false));

        dvdSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dvdSwitch1.isChecked())
                {
                    //when switch checked
                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("DVD Key",true);
                    editor.apply();
                    dvdSwitch1.setChecked(true);
                }
                else
                {
                    //when switch unchecked
                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("DVD Key",false);
                    editor.apply();
                    dvdSwitch1.setChecked(false);
                }
            }
        });

        dvdSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dvdSwitch1.isChecked())
                {
                    //when switch checked
                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("DVD Ring Alarm",true);
                    editor.apply();
                    dvdSwitch1.setChecked(true);
                }
                else
                {
                    //when switch unchecked
                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("DVD Ring Alarm",false);
                    editor.apply();
                    dvdSwitch1.setChecked(false);
                }
            }
        });

         */
    }
    public static  class SettingFragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.dvd_settings);
        }
    }
}