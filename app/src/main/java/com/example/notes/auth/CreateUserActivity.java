package com.example.notes.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.CreateNoteActivity;
import com.example.notes.MainActivity;
import com.example.notes.R;
import com.example.notes.services.AuthService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class CreateUserActivity extends AppCompatActivity {
    Button buttonGoToSingin;
    Button buttonCreateUser;
    EditText emailEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        initViews();

        buttonGoToSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateUserActivity.this,SigninActivity.class));
            }
        });





        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    if (email.isEmpty()){
                        emailEditText.setError("Email is empty");
                        showMessage("Email is empty!");
                        return;
                    }
                    if (!(email.contains("@")) || email.length() < 3){
                        emailEditText.setError("Email must be correct");
                        showMessage("Email must be correct");
                        return;
                    }
                    if (password.isEmpty()){
                        passwordEditText.setError("Password is empty");
                        showMessage("Password is empty!");
                        return;
                    }

                AuthService.createUser(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(CreateUserActivity.this, MainActivity.class));
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
        buttonGoToSingin = findViewById(R.id.buttonGoToSignin);
        buttonCreateUser = findViewById(R.id.buttonCreateUser);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    void showMessage(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}