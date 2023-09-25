package com.example.ctsalarm.Forgot_Password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.ctsalarm.R;
import com.example.ctsalarm.Sign_Up_In.OTP_Code;
import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.loadingDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class New_Password extends AppCompatActivity {

    Window window;
    Button update;
    TextInputEditText pinCode3,rePinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        pinCode3 = findViewById(R.id.pinCode33);
        rePinCode = findViewById(R.id.confirmPinCode);
        final loadingDialog loadingDialog = new loadingDialog(New_Password.this);

        update = findViewById(R.id.buttonUpdate);
        update.setOnClickListener(new View.OnClickListener() {
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

                //check validation
                if (!validatePinCode()) {
                    return;
                }
                //get data from field
                String newPinCode = pinCode3.getText().toString().trim();
                String MobileNumber = getIntent().getStringExtra("mobileNumber"); //
                //Update password in firebase
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                reference.child(MobileNumber).child("pinCode").setValue(newPinCode);
                Intent intent=new Intent(New_Password.this, Status_Complete.class);
                startActivity(intent);
            }
        });
    }

    private boolean validatePinCode() {
        String pin = pinCode3.getText().toString().trim();
        String rePin = rePinCode.getText().toString().trim();

        if (pin.isEmpty() && rePin.isEmpty()) {
            pinCode3.setError("Enter valid pin code number");
            rePinCode.setError("Enter valid pin code number");
            return false;
        }
        else if(pin.length()>6 && rePin.length()>6){
            pinCode3.setError("Pin Code must be 6 characters");
            rePinCode.setError("Pin Code must be 6 characters");
            return false;
        }
        else if(pin.length()<6 && rePin.length()<6){
            pinCode3.setError("Pin Code must be 6 characters");
            rePinCode.setError("Pin Code must be 6 characters");
            return false;
        }
        else if(!rePin.equals(pin) | !pin.equals(rePin))
        {
            pinCode3.setError("Enter Pin Code is not same");
            rePinCode.setError("Enter Pin Code is not same");
            return false;
        }
        else {
            pinCode3.setError(null);
            rePinCode.setError(null);
            //mobileNumber.setErrorEnabled(false);
            return true;
        }
    }
}