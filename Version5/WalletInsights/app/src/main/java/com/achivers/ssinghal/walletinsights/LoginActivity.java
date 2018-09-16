package com.achivers.ssinghal.walletinsights;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonsignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup,textForgetPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
           startActivity(new Intent(getApplicationContext(),Home.class));
        }


        progressDialog= new ProgressDialog(this);

        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonsignin= (Button) findViewById(R.id.buttonSignin);
        textViewSignup= (TextView) findViewById(R.id.textViewSignup);
        textForgetPass= (TextView) findViewById(R.id.textForgetPassword);

        buttonsignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textForgetPass.setOnClickListener(this);
    }

    private void UserLogin(){

        String email =editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Login user Please wait....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                   // checkIfEmailVerified();
                    finish();
                    //startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    startActivity(new Intent(getApplicationContext(),WelcomeScreen.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid username or password.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    @Override
    public void onClick(View view) {
        if(view == buttonsignin){
            UserLogin();
        }
        if(view ==textViewSignup){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        if(view ==textForgetPass){

            startActivity(new Intent(this, ForgotPasswordActivity.class));
        }
    }

}
