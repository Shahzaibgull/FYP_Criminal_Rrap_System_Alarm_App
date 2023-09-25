package com.example.ctsalarm.Forgot_Password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.ctsalarm.R;
import com.example.ctsalarm.Sign_Up_In.SignIn;

public class Status_Complete extends AppCompatActivity {

    Window window;
    Button againSigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_complete);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        againSigin = findViewById(R.id.buttonAgainSignIn);
        againSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Status_Complete.this, SignIn.class);
                startActivity(intent);
            }
        });

    }
}