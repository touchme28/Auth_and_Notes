package com.example.notes.services;

import com.example.notes.auth.CreateUserActivity;
import com.example.notes.models.User;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;


public class UserService {
    public static User createUser(AuthResult authResult) {
        return new User(
                authResult.getUser().getEmail(),
                ""
        );
    }

    public static void storeUser(User user){
        //TODO
        FirebaseDatabase.getInstance("https://notes-89cd4-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("users")
                .push()
                .setValue(user);
    }
}
