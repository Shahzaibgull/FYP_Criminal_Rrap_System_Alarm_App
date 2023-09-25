package com.example.ctsalarm.Sign_Up_In;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.ctsalarm.Forgot_Password.New_Password;
import com.example.ctsalarm.HomeScreen;
import com.example.ctsalarm.IntroductionScreen;
import com.example.ctsalarm.R;
import com.example.ctsalarm.Real_Time_Database.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OTP_Code extends AppCompatActivity {

    Window window;
    Button verifyButton,BackPress;
    TextView userMobileNumber;
    String verificationCodeBySystem;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    PinView pinFromUser;
    String MobileNumber,UserName,PinCode,Gender,whatToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optp_code);
        mAuth = FirebaseAuth.getInstance();
        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        //get mobile number
        pinFromUser=findViewById(R.id.firstPinView);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        userMobileNumber=findViewById(R.id.getNumberUser);
        MobileNumber = getIntent().getStringExtra("mobileNumber");  //come from signup and PhoneNo_use(forgot password)
        userMobileNumber.setText(MobileNumber);
        sendVerificationCodeToUser(MobileNumber);

        UserName = getIntent().getStringExtra("userName");
        PinCode = getIntent().getStringExtra("pinCode");
        Gender = getIntent().getStringExtra("LayoutGender");
        whatToDo = getIntent().getStringExtra("whatToDo");

        //For manual way to enter OTP code
        verifyButton = findViewById(R.id.buttonVerifyCode);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String code = pinFromUser.getText().toString();
                    if (code.isEmpty() || code.length() < 6) {
                        pinFromUser.setError("Wrong OTP...");
                        pinFromUser.requestFocus();
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    verifyCode(code);
            }
        });
    }

    private void sendVerificationCodeToUser(String MobileNumber)
    {
        //[START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+92"+ MobileNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            //Get the code in global variable
            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTP_Code.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String codeByUser)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTP_Code.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String code2="1";
                            String code1 = getIntent().getStringExtra("SignUp");
                            if (Objects.equals(code1, code2))
                            {
                                Toast.makeText(OTP_Code.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                                StoreNewUserData();
                                //Perform Your required action here to either let the user sign In or do something required
                                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                if(whatToDo.equals("updatePassword"))
                                {
                                    updateOldPassword();
                                }
                            }
                            /*
                            if(whatToDo.equals(code))
                            {
                                updateOldPassword();
                            }
                            else {
                                Toast.makeText(OTP_Code.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                                StoreNewUserData();
                                //Perform Your required action here to either let the user sign In or do something required
                                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                             */
                        } else {
                            Toast.makeText(OTP_Code.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateOldPassword()
    {
        Intent intent = new Intent(getApplicationContext(), New_Password.class);
        intent.putExtra("mobileNumber", MobileNumber);
        startActivity(intent);
    }

    private void StoreNewUserData()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User");
        UserHelperClass addNewUser=new UserHelperClass(MobileNumber,UserName,PinCode,Gender);
        reference.child(MobileNumber).setValue(addNewUser);
    }

}

/*verifyButton = findViewById(R.id.buttonVerifyCode);
        verifyButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        String code2="1";
        String code1 = getIntent().getStringExtra("SignUp");
        //String code1=getIntent().getStringExtra("SignUp");
        Intent intent;
        if (Objects.equals(code1, code2))
        {
        intent = new Intent(OTP_Code.this, HomeScreen.class);
        }
        else
        {
        intent = new Intent(OTP_Code.this, New_Password.class);
        }
        startActivity(intent);
        }
        });

 */