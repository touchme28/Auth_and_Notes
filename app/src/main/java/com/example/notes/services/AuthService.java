package com.example.notes.services;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.recyclerview.widget.ConcatAdapter;





public class AuthService {
    public static Task<AuthResult> login(String email, String password) {
        return FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email , password);

    }


    public static void logOut() {
        FirebaseAuth.getInstance().signOut();
    }


    public static Task<AuthResult> createUser(String email, String password) {
        return FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = UserService.createUser(authResult);
                        UserService.storeUser(user);
                    }
                });
    }


    public static boolean isSignin() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
//        FirebaseAuth.getInstance()
//                .getCurrentUser().get
    }

//    public static FirebaseRecyclerOptions<Note> getUserOptions() {
//        Query query = FirebaseDatabase.getInstance().getReference("new-notes");
//        ClassSnapshotParser<Note> parser = new ClassSnapshotParser<>(Note.class);
//
//        return new FirebaseRecyclerOptions.Builder<Note>()
//                .setQuery(query, parser)
//                .build();
//    }
}
