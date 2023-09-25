package com.example.ctsalarm.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctsalarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class NodeMCU extends AppCompatActivity {


    Button ON, OFF;
    TextView sensorValue, sensor_Activate_Deactivate;
    DatabaseReference databaseReference1, databaseReference2;
    String sensorValueStatus, sensorStatus;

    Button ON1, OFF1;
    TextView sensorValueD, sensor_Activate_DeactivateD;
    DatabaseReference databaseReference1D, databaseReference2D;
    String sensorValueStatusD, sensorStatusD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_mcu);

        ON = findViewById(R.id.buttonON);
        OFF = findViewById(R.id.buttonOFF);
        sensorValue = findViewById(R.id.getSensorValue);
        //sensor_Activate_Deactivate = findViewById(R.id.Sensor_Activate_Deactivate);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Door Sensor Value");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorValueStatus = snapshot.getValue().toString();
                sensorValue.setText(sensorValueStatus);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sensor_Activate_Deactivate = findViewById(R.id.Sensor_Activate_Deactivate);
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Door Sensor Status");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorStatus = snapshot.getValue().toString();
                sensor_Activate_Deactivate.setText(sensorStatus);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Door Sensor Status");
                myRef.setValue(5);

            }
        });
        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Door Sensor Status");
                myRef.setValue(0);

            }
        });




        ON1 = findViewById(R.id.buttonON2);
        OFF1 = findViewById(R.id.buttonOFF2);
        sensorValueD = findViewById(R.id.getSensorValue2);
        //sensor_Activate_Deactivate = findViewById(R.id.Sensor_Activate_Deactivate);

        databaseReference1D = FirebaseDatabase.getInstance().getReference().child("DVD Sensor Value");
        databaseReference1D.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorValueStatusD = snapshot.getValue().toString();
                sensorValueD.setText(sensorValueStatusD);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sensor_Activate_DeactivateD = findViewById(R.id.sensor_Activate_Deactivate2);
        databaseReference2D = FirebaseDatabase.getInstance().getReference().child("DVD Sensor Status");
        databaseReference2D.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorStatusD = snapshot.getValue().toString();
                sensor_Activate_DeactivateD.setText(sensorStatusD);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ON1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DVD Sensor Status");
                myRef.setValue(5);

            }
        });
        OFF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DVD Sensor Status");
                myRef.setValue(0);

            }
        });

        startService(new Intent(this, MyFirebaseService.class));

    }

}


