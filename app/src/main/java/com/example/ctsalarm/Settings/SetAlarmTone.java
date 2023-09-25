package com.example.ctsalarm.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ctsalarm.Features_Alarm.DVD_Alert;
import com.example.ctsalarm.Features_Alarm.MainIntruder_Alert;
import com.example.ctsalarm.Features_Alarm.OpenDoor_Alert;
import com.example.ctsalarm.Features_Alarm.SideMirror_Alert;
import com.example.ctsalarm.Features_Alarm.SpeedoMeter_Alert;
import com.example.ctsalarm.Forgot_Password.PhoneNo_Use;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Side_Navigation.Settings;
import com.example.ctsalarm.Sign_Up_In.SignIn;

import java.util.Objects;

public class SetAlarmTone extends AppCompatActivity {

    Window window;
    RadioGroup radioGroup;
    Button BackPress;

    private SoundPool soundPool;
    private int Tone1,Tone2,Tone3,Tone4,Tone5,Tone6;
    private int sound1,sound2,sound3,sound4,sound5,sound6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_tone);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        getFragmentManager()
                .beginTransaction()
                .add(R.id.preference_system_ring_tone_activated,new SettingFragment())
                .commit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_SYSTEM, 0);
        }

        Tone1 = soundPool.load(this, R.raw.cartone, 1);
        Tone2 = soundPool.load(this, R.raw.tone2, 1);
        Tone3 = soundPool.load(this, R.raw.tone3, 1);
        Tone4 = soundPool.load(this, R.raw.tone4, 1);
        Tone5 = soundPool.load(this, R.raw.tone5, 1);

        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.Tone1:
                        soundPool.pause(sound2);
                        soundPool.pause(sound3);
                        soundPool.pause(sound4);
                        soundPool.pause(sound5);
                        soundPool.pause(sound6);
                        sound1=soundPool.play(Tone1, 1, 1, 0, -1, 1);
                        break;

                    case R.id.Tone2:
                        soundPool.pause(sound1);
                        soundPool.pause(sound3);
                        soundPool.pause(sound4);
                        soundPool.pause(sound5);
                        soundPool.pause(sound6);
                        sound2=soundPool.play(Tone2, 1, 1, 0, -1, 1);
                        break;

                    case R.id.Tone3:
                        soundPool.pause(sound1);
                        soundPool.pause(sound2);
                        soundPool.pause(sound4);
                        soundPool.pause(sound5);
                        soundPool.pause(sound6);
                        sound3=soundPool.play(Tone3, 1, 1, 0, -1, 1);
                        break;

                    case R.id.Tone4:
                        soundPool.pause(sound1);
                        soundPool.pause(sound2);
                        soundPool.pause(sound3);
                        soundPool.pause(sound5);
                        soundPool.pause(sound6);
                        sound4=soundPool.play(Tone4, 1, 1, 0, -1, 1);
                        break;
                    case R.id.Tone5:
                        soundPool.pause(sound1);
                        soundPool.pause(sound2);
                        soundPool.pause(sound3);
                        soundPool.pause(sound4);
                        soundPool.pause(sound6);
                        sound5=soundPool.play(Tone5, 1, 1, 0, -1, 1);
                        break;
                    case R.id.Tone6:
                        soundPool.pause(sound1);
                        soundPool.pause(sound2);
                        soundPool.pause(sound3);
                        soundPool.pause(sound4);
                        soundPool.pause(sound5);
                        sound6=soundPool.play(Tone6, 1, 1, 0, -1, 1);
                        break;
                }
            }
        });

        /*BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeDVD1="1",codeSpeedo1="1",codeIntruder1="1",codeOpen1="1",codeSide1="1";

                String codeDVD2=getIntent().getStringExtra("keyDVD");
                String codeSpeedo2=getIntent().getStringExtra("keySpeedo");
                String codeIntruder2=getIntent().getStringExtra("keyIntruder");
                String codeOpen2=getIntent().getStringExtra("keyOpen");
                String codeSide2=getIntent().getStringExtra("keySide");

                if(Objects.equals(codeDVD1, codeDVD2))
                {
                    Intent intent = new Intent(SetAlarmTone.this, DVD_Alert.class);
                    startActivity(intent);
                }
                else if(Objects.equals(codeSpeedo1, codeSpeedo2))
                {
                    Intent intent = new Intent(SetAlarmTone.this, SpeedoMeter_Alert.class);
                    startActivity(intent);
                }
                else if(Objects.equals(codeIntruder1, codeIntruder2))
                {
                    Intent intent = new Intent(SetAlarmTone.this, MainIntruder_Alert.class);
                    startActivity(intent);
                }
                else if(Objects.equals(codeOpen1, codeOpen2))
                {
                    Intent intent = new Intent(SetAlarmTone.this, OpenDoor_Alert.class);
                    startActivity(intent);
                }
                else if(Objects.equals(codeSide1, codeSide2))
                {
                    Intent intent = new Intent(SetAlarmTone.this, SideMirror_Alert.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SetAlarmTone.this, Settings.class);
                    startActivity(intent);
                }
            }
        });*/
    }
    public static  class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.systems_tones);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}