package com.example.goundbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    TextView tground,tregister,loginnow;
    EditText name,mobileno,mail,pass;
    Button rbtn;
    FirebaseAuth nAuth;

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = nAuth.getCurrentUser();
//        if(currentUser != null){
//
//            Intent intent = new Intent(getApplicationContext(), Register.class);
//            startActivity(intent);
//            finish();
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nAuth = FirebaseAuth.getInstance();

        tground = findViewById(R.id.tground);
        tregister = findViewById(R.id.tregister);
        name = findViewById(R.id.name);
        mobileno = findViewById(R.id.mobileno);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        rbtn = findViewById(R.id.rbtn);
        loginnow = findViewById(R.id.loginnow);

        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Register.this,Login.class));

            }
        });

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email = String.valueOf(mail.getText());
                password = String.valueOf(pass.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                nAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });



    }
}