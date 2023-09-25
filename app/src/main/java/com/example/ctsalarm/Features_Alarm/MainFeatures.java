package com.example.ctsalarm.Features_Alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Sign_Up_In.SignIn;

public class MainFeatures extends AppCompatActivity {

    Window window;
    ImageView DVD,speedoMeter,intruder,openDoor,sdeMirror;
    TextView  TextDVD,TextSpeedoMeter,TextIntruder,TextOpenDoor,TextSideMirror;
    Button BackPress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_features);
        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        DVD=findViewById(R.id.imageDVD);
        DVD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, DVD_Alert.class);
                startActivity(intent);
            }
        });
        TextDVD=findViewById(R.id.textDVD);
        TextDVD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, DVD_Alert.class);
                startActivity(intent);
            }
        });
        //FFFF9C00

        speedoMeter=findViewById(R.id.imageSpeedoMeter);
        speedoMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, SpeedoMeter_Alert.class);
                startActivity(intent);
            }
        });
        TextSpeedoMeter=findViewById(R.id.textSpeedoMeter);
        TextSpeedoMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, SpeedoMeter_Alert.class);
                startActivity(intent);
            }
        });

        intruder=findViewById(R.id.imageIntruder);
        intruder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, MainIntruder_Alert.class);
                startActivity(intent);
            }
        });
        TextIntruder=findViewById(R.id.textIntruder);
        TextIntruder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, MainIntruder_Alert.class);
                startActivity(intent);
            }
        });

        openDoor=findViewById(R.id.imageOpenDoor);
        openDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, OpenDoor_Alert.class);
                startActivity(intent);
            }
        });
        TextOpenDoor=findViewById(R.id.textOpenDoor);
        TextOpenDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, OpenDoor_Alert.class);
                startActivity(intent);
            }
        });

        sdeMirror=findViewById(R.id.imageSideMirror);
        sdeMirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, SideMirror_Alert.class);
                startActivity(intent);
            }
        });
        TextSideMirror=findViewById(R.id.textSideMirror);
        TextSideMirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, SideMirror_Alert.class);
                startActivity(intent);
            }
        });


        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFeatures.this, HomeScreen.class);
                startActivity(intent);
            }
        });

    }
}