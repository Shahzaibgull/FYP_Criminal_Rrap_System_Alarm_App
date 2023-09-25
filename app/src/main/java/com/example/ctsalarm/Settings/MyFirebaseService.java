package com.example.ctsalarm.Settings;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ctsalarm.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFirebaseService extends Service {

    private static final String TAG = "FirebaseService";
    private static final String DATABASE_REFERENCE = "Door Sensor Value";
    private static final String DATABASE_REFERENCE1 = "DVD Sensor Value";
    private Ringtone mRingtone;

    @Override
    public void onCreate() {
        super.onCreate();

        // Listen for changes in the "Door Sensor Value" node
        FirebaseDatabase.getInstance().getReference().child(DATABASE_REFERENCE)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sensorValue = snapshot.getValue().toString();
                        Log.d(TAG, "Door Sensor value: " + sensorValue);
                        // Check if sensor value is "on"
                        if(Integer.parseInt(sensorValue) == 1) {
                            // Play the ringtone when there is a new child added and sensor value is "5"
                            playRingtone();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseDatabase.getInstance().getReference().child(DATABASE_REFERENCE1)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sensorValue = snapshot.getValue().toString();
                        Log.d(TAG, "DVD Sensor value: " + sensorValue);
                        // Check if sensor value is "on"
                        if(Integer.parseInt(sensorValue) == 1) {
                            // Play the ringtone when there is a new child added and sensor value is "5"
                            playRingtone();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRingtone();
        Log.d(TAG, "Service destroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void playRingtone() {
        try {
            Uri customRingtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cartone);
            mRingtone = RingtoneManager.getRingtone(getApplicationContext(), customRingtoneUri);
            mRingtone.play();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopRingtone();
                }
            }, 4000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRingtone() {
        if (mRingtone != null && mRingtone.isPlaying()) {
            mRingtone.stop();
        }
    }
}
