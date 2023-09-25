package com.example.ctsalarm.Sign_Up_In;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Settings.Profile;
import com.example.ctsalarm.Side_Navigation.Settings;
import com.example.ctsalarm.loadingDialog;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    Window window;
    Button signUp2,BackPress;
    TextView signIn2;
    TextInputEditText userName,mobileNumber,pinCode;


    String[] items={"Male","Female","Other"};
    AutoCompleteTextView LayoutGender;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        LayoutGender=findViewById(R.id.Gender);
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.gender_list,items);
        LayoutGender.setAdapter(arrayAdapter);
        userName = findViewById(R.id.UserName);
        mobileNumber = findViewById(R.id.mobileNumber1);
        pinCode = findViewById(R.id.PinCode22);


        final loadingDialog loadingDialog = new loadingDialog(SignUp.this);
        signIn2 = findViewById(R.id.textSignIn2);
        signIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        signUp2 = findViewById(R.id.buttonSignUp2);
        signUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation of text fields
                if (!validateUserName() | !validateMobileNumber() | !validatePinCode())
                {
                    return;
                }

                //Loading Dialog box
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        loadingDialog.dismissDialog();
                    }
                },5000);

                String code2="1";
                String LayoutGender1=LayoutGender.getText().toString().trim();
                String userName1= userName.getText().toString().trim();
                String pinCode1=pinCode.getText().toString().trim();
                String mobileNumber1=mobileNumber.getText().toString().trim();

                Intent intent=new Intent(SignUp.this, OTP_Code.class);
                intent.putExtra("SignUp", code2);
                intent.putExtra("userName", userName1);
                intent.putExtra("mobileNumber", mobileNumber1);
                intent.putExtra("pinCode", pinCode1);
                intent.putExtra("LayoutGender", LayoutGender1);
                startActivity(intent);
            }
        });
        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this, IntroductionScreen.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateUserName()
    {
        String val = userName.getText().toString().trim();
        if (val.isEmpty()) {
            userName.setError("Field can not be empty");
            return false;
        } else {
            userName.setError(null);
            //UserName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateMobileNumber() {
        String val = mobileNumber.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            mobileNumber.setError("Enter valid phone number");
            return false;
        }else {
            mobileNumber.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePinCode() {
        String val = pinCode.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            pinCode.setError("Enter valid pin code number");
            return false;
        }
        else if(val.length()>6){
            pinCode.setError("Pin Code must be 6 characters");
            return false;
        }
        else if(val.length()<6){
            pinCode.setError("Pin Code must be 6 characters");
            return false;
        }
        else {
            pinCode.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }

}
