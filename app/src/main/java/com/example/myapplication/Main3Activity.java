package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{
    ImageButton button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViewById(R.id.scanner).setOnClickListener(this);

        button = (ImageButton) findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();


            }
        });

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(Main3Activity.this, MainActivity.class));
                }
            }


        };
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        { case R.id.scanner:
            startActivity(new Intent(this,Scanner.class));


        }

    }
}