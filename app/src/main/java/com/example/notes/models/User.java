package com.example.notes.models;

public class User {
    public String password;
    public String email;


    public User(){
        password = "pass";
        email = "email@email.email";
    }

    public User(String email,String password) {
        this();
        this.email = email;
        this.password = password;
    }

}
