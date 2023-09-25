package com.example.ctsalarm.Forgot_Password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.ctsalarm.Features_Alarm.DVD_Alert;
import com.example.ctsalarm.Features_Alarm.MainFeatures;
import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Side_Navigation.Settings;
import com.example.ctsalarm.Sign_Up_In.OTP_Code;
import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.Sign_Up_In.SignUp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PhoneNo_Use extends AppCompatActivity {

    Window window;
    Button sendCode,BackPress;
    TextInputEditText mobileNumber4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_no_use);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        mobileNumber4 = findViewById(R.id.mobileNumber44);
        sendCode = findViewById(R.id.buttonSendCode);
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check input fields
                if (!validateMobileNumber()) {
                    return;
                }
                String mobileNumber44 = mobileNumber4.getText().toString().trim();
                Query checkUser = FirebaseDatabase.getInstance().getReference("User").orderByChild("mobileNumber").equalTo(mobileNumber44);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            mobileNumber4.setError(null);
                            mobileNumber4.setEnabled(false);
                            Intent intent = new Intent(PhoneNo_Use.this, OTP_Code.class);
                            intent.putExtra("mobileNumber", mobileNumber44);
                            intent.putExtra("whatToDo", "updatePassword");
                            startActivity(intent);

                        } else {
                            Toast.makeText(PhoneNo_Use.this, "no such user exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(PhoneNo_Use.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


            BackPress=findViewById(R.id.buttonBack);
            BackPress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code2="1";
                    String code1=getIntent().getStringExtra("keyCode");
                    Intent intent;
                    if(Objects.equals(code1, code2))
                    {
                        intent = new Intent(PhoneNo_Use.this, Settings.class);
                    }
                    else
                    {
                        intent = new Intent(PhoneNo_Use.this, SignIn.class);
                    }
                    startActivity(intent);
                }
            });


    }

    private boolean validateMobileNumber() {
        String val = mobileNumber4.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            mobileNumber4.setError("Enter valid phone number");
            return false;
        } else {
            mobileNumber4.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }
}