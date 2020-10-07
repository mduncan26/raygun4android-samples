package com.raygun.raygun4android.sample.java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.raygun.raygun4android.CrashReportingOnBeforeSend;
import com.raygun.raygun4android.RaygunClient;
import com.raygun.raygun4android.messages.crashreporting.RaygunMessage;
import com.raygun.raygun4android.messages.shared.RaygunUserInfo;

import java.util.Arrays;

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

    RaygunUserInfo user = new RaygunUserInfo("ronald@raygun.com");
    user.setFirstName("Ronald");
    user.setFullName("Ronald Raygun");
    user.setEmail("ronald@raygun.com");

    RaygunClient.setUser(user);

    RaygunClient.send(new Exception("Congratulations, you have sent errors with Raygun4Android"), Arrays.asList("isVIP:yes", "release:alpha"));
  }
}


