package com.example.ctsalarm.Features_Alarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.ctsalarm.R;
import com.example.ctsalarm.Settings.SetAlarmTone;

public class MainIntruder_Alert extends AppCompatActivity {

    Window window;
    Button Intruder_AlarmSettings,BackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intruder_alert);
        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        getFragmentManager()
                .beginTransaction()
                .add(R.id.preference_intruder_activated,new SettingFragment())
                .add(R.id.preference_intruder_ringAlarm,new SettingFragment())
                .commit();

        Intruder_AlarmSettings=findViewById(R.id.intruder_Alarm_Setting);
        Intruder_AlarmSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeIntruder2="1";
                Intent intent=new Intent(MainIntruder_Alert.this, SetAlarmTone.class);
                intent.putExtra("keyIntruder",codeIntruder2);
                startActivity(intent);
            }
        });
        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainIntruder_Alert.this, MainFeatures.class);
                startActivity(intent);
            }
        });
    }
    public static  class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.intruder_settings);
        }
    }
}