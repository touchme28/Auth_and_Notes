package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes.auth.CreateUserActivity;
import com.example.notes.services.AuthService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AuthService.isSignin()) {
            startActivity(new Intent(this,MainActivity.class));
        } else startActivity(new Intent(this, CreateUserActivity.class));
                //TODO
    }
}