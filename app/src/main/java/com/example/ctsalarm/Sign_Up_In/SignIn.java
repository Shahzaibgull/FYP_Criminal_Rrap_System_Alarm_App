package com.example.ctsalarm.Sign_Up_In;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctsalarm.Features_Alarm.DVD_Alert;
import com.example.ctsalarm.Features_Alarm.MainFeatures;
import com.example.ctsalarm.Forgot_Password.PhoneNo_Use;
import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.loadingDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class SignIn extends AppCompatActivity {

    Window window;
    Button signIn3,BackPress;
    TextView signUp3;
    TextView forgotPassword;
    TextInputEditText mobileNumber3, pinCode2;
    CountryCodePicker countryCodePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        mobileNumber3 = findViewById(R.id.mobileNumber33);
        pinCode2 = findViewById(R.id.pinCode22);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        final loadingDialog loadingDialog = new loadingDialog(SignIn.this);


        signIn3 = findViewById(R.id.buttonSignIn3);
        signIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Loading Dialog box
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        loadingDialog.dismissDialog();
                    }
                },3000);

                //Check input fields
                if (!validateMobileNumber() | !validatePinCode()) {
                    return;
                }
                //Get Values
                String mobileNumber33 = mobileNumber3.getText().toString().trim();
                String pinCode33 = pinCode2.getText().toString().trim();
                /*
                if (mobileNumber33.charAt(0) == '0') {  //if enter 0 at first we remove it from from index 1
                    mobileNumber33 = mobileNumber33.substring(1);
                }
                String completeMobileNumber = countryCodePicker.getSelectedCountryCodeWithPlus()+mobileNumber33;
                 */
                //Database
                Query checkUser = FirebaseDatabase.getInstance().getReference("User").orderByChild("mobileNumber").equalTo(mobileNumber33);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            mobileNumber3.setError(null);
                            mobileNumber3.setEnabled(false);
                            String dataBasePassword = snapshot.child(mobileNumber33).child("pinCode").getValue(String.class);
                            if (dataBasePassword.equals(pinCode33)) {
                                pinCode2.setError(null);
                                pinCode2.setEnabled(false);
                                Toast.makeText(SignIn.this, "You successfully sign in!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, HomeScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignIn.this, "password does not match", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignIn.this, "no such user exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SignIn.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signUp3 = findViewById(R.id.textSignUp3);
        signUp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword = findViewById(R.id.textForgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, PhoneNo_Use.class);
                startActivity(intent);
            }
        });

        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, IntroductionScreen.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateMobileNumber() {
        String val = mobileNumber3.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            mobileNumber3.setError("Enter valid phone number");
            return false;
        } else {
            mobileNumber3.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePinCode() {
        String val = pinCode2.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            pinCode2.setError("Enter valid pin code number");
            return false;
        }
        else if(val.length()>6){
            pinCode2.setError("Pin Code must be 6 characters");
            return false;
        }
        else if(val.length()<6){
            pinCode2.setError("Pin Code must be 6 characters");
            return false;
        }
        else {
            pinCode2.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }
}