package com.example.ctsalarm.Side_Navigation;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.core.content.FileProvider;

import com.example.ctsalarm.BuildConfig;
import com.example.ctsalarm.R;
import com.example.ctsalarm.databinding.ActivityHomeScreenBinding;
import com.example.ctsalarm.databinding.ActivityShareBinding;

import java.io.File;

public class Share extends DrawerBaseActivity {

    Window window;
    ActivityShareBinding activityShareBinding;
    Button ShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShareBinding = ActivityShareBinding.inflate(getLayoutInflater());
        setContentView(activityShareBinding.getRoot());
        allocateActivityTitle("Share");
        window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));

        ShareButton=findViewById(R.id.shareButton);
        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                ApplicationInfo api=getApplicationContext().getApplicationInfo();
                String apkpath=api.sourceDir;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                startActivity(Intent.createChooser(intent,"Choose one"));
                 */

                Intent sendIntent=new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Download this fantastic app and share with your friends. \n\n https://play.google.com/store/apps/details?id="+getPackageName());
                startActivity(Intent.createChooser(sendIntent,"Choose one"));


                /*
                ApplicationInfo api = getApplicationContext().getApplicationInfo();
                String apkpath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "Share Via"));

                 */
                /*
                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareBody = "Here is the share content body";
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try subject");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share Via"));

                 */
            }
        });
    }
}