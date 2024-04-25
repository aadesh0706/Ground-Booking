package com.example.goundbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView groundlogin,login,registernow;
    EditText mail,pass;
    MaterialButton lbtn;

    FirebaseAuth nAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = nAuth.getCurrentUser();
        if(currentUser != null){

            Intent intent = new Intent(getApplicationContext(), calender.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nAuth = FirebaseAuth.getInstance();

        groundlogin = findViewById(R.id.groundlogin);
        login = findViewById(R.id.login);
        registernow = findViewById(R.id.registernow);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        lbtn = findViewById(R.id.lbtn);

        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email = String.valueOf(mail.getText());
                password = String.valueOf(pass.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                nAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Login Successfuly",Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Login.this, calender.class);
                                    startActivity(new Intent(Login.this, calender.class));
                                    finish();
                                } else {

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}