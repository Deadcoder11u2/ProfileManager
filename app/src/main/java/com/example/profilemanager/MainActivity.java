package com.example.profilemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import User.User;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(getApplicationContext(), Homepage.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int size = 50;

        // Resizing the drawable image for the username input field
        TextInputEditText username_field = findViewById(R.id.username);
        Drawable username = getResources().getDrawable(R.drawable.username);
        Bitmap bitmap = ((BitmapDrawable) username).getBitmap();
        Drawable username_image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, size, size, true));
        username_field.setCompoundDrawablesWithIntrinsicBounds(username_image, null, null, null);

        // Resizing the drawable image for the password output field
        TextInputEditText password_field = findViewById(R.id.password);
        Drawable password = getResources().getDrawable(R.drawable.password);
        bitmap = ((BitmapDrawable)password).getBitmap();
        Drawable password_image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, size, size, true));
        password_field.setCompoundDrawablesWithIntrinsicBounds(password_image, null, null, null);

        // Adding the listener for the login button
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_field.getText().toString();
                String password = password_field.getText().toString();
                if(!nonNull(username) || !nonNull(password)) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields to continue", Toast.LENGTH_LONG).show();
                    return;
                }
                if(createUser(username, password)) {
                    startActivity(new Intent(getApplicationContext(), Homepage.class));
                }
            }
        });
        try {
            connectToDatabase();
            Toast.makeText(getApplicationContext(), "Firebase Connected Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Failed to connect to firebase", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password) {
        boolean[] success = {false};
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    success[0] = true;
                }
                else {
                    System.out.println(task.getException());
                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        return success[0];
    }

    public void addUser(User newUser) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ref.setValue(newUser);
                Toast.makeText(getApplicationContext(), "Registration in progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Mobile not connected.....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void connectToDatabase() {
        database = FirebaseDatabase.getInstance("https://profilemanager-18026-default-rtdb.firebaseio.com");
        ref = database.getReference("UserData");
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean nonNull(String field) {
        return !field.equals("") && field != null;
    }
}