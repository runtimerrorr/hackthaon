package com.snehlata.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import snehlata.myapplication.R;

public class Login_form extends AppCompatActivity {
    EditText txtEmail, txtpassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");
        txtEmail=(EditText)findViewById(R.id.txt_Email);
        txtpassword=(EditText)findViewById(R.id.txt_password);
        btn_login=(Button)findViewById(R.id.buttonlogin);
        firebaseAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email  = txtEmail.getText().toString().trim();
                String password= txtpassword.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Login_form.this,"Please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login_form.this,"Please enter password",Toast.LENGTH_SHORT).show();

                }
                firebaseAuth.signInWithEmailAndPassword(Email,password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(), com.snehlata.myapplication.MainActivity.class));
                                }
                                else{
                                    Toast.makeText(Login_form.this, "login failed or user not exist",Toast.LENGTH_SHORT).show();
                                }
                                //...
                            }
                        });
            }

        });
    }

}