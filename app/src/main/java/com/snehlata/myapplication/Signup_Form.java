package com.snehlata.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import snehlata.myapplication.R;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail,txtpassword,txtconfirmPassword,txtfullName;
    Button btn_register;
    RadioButton radioGenderMale,getRadioGenderFemale;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        //casting view
        txtfullName=(EditText)findViewById(R.id.txt_full_Name);
        txtEmail=(EditText)findViewById(R.id.txt_Email);
        txtpassword=(EditText)findViewById(R.id.txt_password);
        txtconfirmPassword=(EditText)findViewById(R.id.txt_confirm_password);
        btn_register=(Button)findViewById(R.id.btn_register);
        radioGenderMale=(RadioButton)findViewById(R.id.radio_male);
        getRadioGenderFemale=(RadioButton)findViewById(R.id.radio_female);

        firebaseAuth=FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new view.onClickListener(){

            @Override
            public void onClick(View v) {
                String fullName = txtfullName.getText().toString().trim();
                String Email  = txtEmail.getText().toString().trim();
                String password= txtpassword.getText().toString().trim();
                String confirmPassword= txtconfirmPassword.getText().toString().trim();
                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(Signup_Form.this,"Please enter full name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Signup_Form.this,"Please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form.this,"Please enter confirm password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup_Form.this, "password too short",Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(Email,password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(Signup_Form.this, "Registration complete",Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(Signup_Form.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                    //...
                                }
                            });

                }






            }
        });
    }
}