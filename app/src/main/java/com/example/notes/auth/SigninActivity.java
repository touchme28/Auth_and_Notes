package com.example.notes.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.MainActivity;
import com.example.notes.R;
import com.example.notes.services.AuthService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class SigninActivity extends AppCompatActivity {
    Button buttonGoToCreateUser;
    Button buttonSignIn;
    EditText passwordText;
    EditText emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initViews();

        buttonGoToCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if (email.isEmpty()){
                    emailText.setError("Email is empty");
                    showMessage("Email is empty!");
                    return;
                }
                if (!(email.contains("@")) || email.length() < 3){
                    emailText.setError("Email must be correct");
                    showMessage("Email must be correct");
                    return;
                }
                if (password.isEmpty()){
                    passwordText.setError("Password is empty");
                    showMessage("Password is empty!");
                    return;
                }

                AuthService.login(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Cant create user");
                        e.printStackTrace();
                    }
                });
            }
        });



    }



    void initViews(){
        buttonGoToCreateUser = findViewById(R.id.buttonGoToCreateUser);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
    }

    void showMessage(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

}