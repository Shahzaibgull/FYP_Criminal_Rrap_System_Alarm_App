package com.example.ctsalarm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.Sign_Up_In.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class IntroductionScreen extends AppCompatActivity {

    Window window;
    Button signIn1;
    TextView signUp1;
    //FirebaseUser currentUser;
    FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_screen);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));


        signIn1 = findViewById(R.id.buttonSignIn1);
        signUp1 = findViewById(R.id.textSignUp1);
        signIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroductionScreen.this, SignIn.class);
                startActivity(intent);
            }
        });
        signUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroductionScreen.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if(currentUser != null)
        {
            // creating a variable for our BiometricManager
            // and lets check if our user can use biometric sensor or not
            androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()) {

                // this means we can use biometric sensor
                case BiometricManager.BIOMETRIC_SUCCESS:
                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                    break;
                // this means that the device doesn't have fingerprint sensor
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Log.e("MY_APP_TAG", "No biometric features available on this device.");
                    break;
                // this means that biometric sensor is not available
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                    break;
                // this means that the device doesn't contain your fingerprint
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Log.e("MY_APP_TAG", "Your device doesn't have fingerprint saved,please check your security settings");
                    break;
            }
            // creating a variable for our Executor
            Executor executor = ContextCompat.getMainExecutor(this);
            // this will give us result of AUTHENTICATION
            final BiometricPrompt biometricPrompt = new BiometricPrompt(IntroductionScreen.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }
                // THIS METHOD IS CALLED WHEN AUTHENTICATION IS SUCCESS
                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Intent intent = new Intent(IntroductionScreen.this, HomeScreen.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }
            });
            // creating a variable for our promptInfo
            // BIOMETRIC DIALOG
            final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric login ")
                    .setDescription("Use your fingerprint to login ").setDeviceCredentialAllowed(true).build();

            biometricPrompt.authenticate(promptInfo);
        }
    }
}

