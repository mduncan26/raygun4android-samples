package com.raygun.raygun4android.sample.java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.raygun.raygun4android.CrashReportingOnBeforeSend;
import com.raygun.raygun4android.RaygunClient;
import com.raygun.raygun4android.messages.crashreporting.RaygunMessage;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RaygunClient.init(getApplication());
    RaygunClient.enableCrashReporting();

    RaygunClient.setOnBeforeSend(new CrashReportingOnBeforeSend() {
      @Override
      public RaygunMessage onBeforeSend(RaygunMessage message) {
        Log.d("Raygun", "Calling onBeforeSend method");
        return message;
      }
    });

    Log.d("Raygun", "Is enabled: " + RaygunClient.isCrashReportingEnabled());

    RaygunClient.send(new Exception("This is a test error!"));
  }
}


