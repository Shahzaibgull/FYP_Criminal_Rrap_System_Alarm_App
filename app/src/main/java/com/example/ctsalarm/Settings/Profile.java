package com.example.ctsalarm.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.ctsalarm.R;
import com.example.ctsalarm.Side_Navigation.Settings;
import com.example.ctsalarm.Sign_Up_In.SignIn;
import com.example.ctsalarm.Sign_Up_In.SignUp;
import com.example.ctsalarm.loadingDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Window window;
    Button signOut,DeleteAccount,BackPress;
    FirebaseAuth firebaseAuth;
    DatabaseReference user;
    TextView name,gender,number,pinCode3;
    AlertDialog.Builder show_alert;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        show_alert=new AlertDialog.Builder(this);
        firebaseAuth = FirebaseAuth.getInstance();

        user= FirebaseDatabase.getInstance().getReference().child("User");
        name=findViewById(R.id.firstName11);
        gender=findViewById(R.id.gender11);
        pinCode3=findViewById(R.id.pincode);
        number=findViewById(R.id.personalPhoneNumberr);
        final loadingDialog loadingDialog = new loadingDialog(Profile.this);

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dss:snapshot.getChildren())
                    {
                        String name1=dss.child("userName").getValue().toString();
                        name.setText(name1);
                        String gender1=dss.child("gender").getValue().toString();
                        gender.setText(gender1);
                        String email1=dss.child("pinCode").getValue().toString();
                        pinCode3.setText(email1);
                        String number1=dss.child("mobileNumber").getValue().toString();
                        number.setText(number1);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        signOut=findViewById(R.id.buttonSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
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
                firebaseAuth.signOut();
                Intent intent = new Intent(Profile.this, SignIn.class);
                startActivity(intent);
            }
        });

        DeleteAccount=findViewById(R.id.buttonDeleteAccount);
        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_alert.setTitle("Delete Account Permanently ?")
                        .setMessage("Are you sure ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseDatabase .getInstance().getReference().child("User").child("mobileNumber").setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Profile.this,"Account Deleted",Toast.LENGTH_SHORT).show();
                                                firebaseAuth.signOut();
                                                Intent intent = new Intent(Profile.this, SignUp.class);
                                                startActivity(intent);
                                            }
                                        });



                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Profile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton("Cancel",null)
                        .create().show();
            }
        });

        BackPress=findViewById(R.id.buttonBack);
        BackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}