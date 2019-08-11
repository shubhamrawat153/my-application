package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener {
EditText edittextemail,edittextpassword;
ProgressBar progressBar;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
edittextemail=(EditText)findViewById(R.id.editTextemail1);
edittextpassword=(EditText)findViewById(R.id.editTextpassword1);

mAuth=FirebaseAuth.getInstance();
findViewById(R.id.button2).setOnClickListener(this);
findViewById(R.id.button3).setOnClickListener(this);

    }
private void registeruser()
{
    String email = edittextemail.getText().toString().trim();
    String password = edittextpassword.getText().toString().trim();
    if(email.isEmpty())
    {
        edittextemail.setError("email is req");
        edittextemail.requestFocus();
        return;

    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        edittextemail.setError("please enter valid email");
        edittextemail.requestFocus();
        return;

    }
    if(password.isEmpty())
    {edittextpassword.setError("email is req");
        edittextpassword.requestFocus();
        return;

    }
    if(password.length()<6)
    {
        edittextpassword.setError("minimum length of password should be 6");
        edittextpassword.requestFocus();
        return;

    }

 mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
     @Override
     public void onComplete(@NonNull Task<AuthResult> task){

        if(task.isSuccessful()){
            Toast.makeText(getApplicationContext(),"User registered successful",Toast.LENGTH_LONG).show();


        }
        else
        {
            Toast.makeText(getApplicationContext(),"some error occured",Toast.LENGTH_SHORT).show();
        }

     }
 });
}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                registeruser();
                startActivity(new Intent(this,Main3Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this,MainActivity.class));
                break;



        }    }
}
