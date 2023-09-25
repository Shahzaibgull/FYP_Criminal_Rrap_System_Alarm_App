package com.example.ctsalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    //Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        //window=this.getWindow();
        //window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread=new Thread(){
            public void run() {
                try {
                    sleep(3000);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(SplashScreen.this, IntroductionScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}