package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private EditText editTextName, editTextNumber;
    private Button buttonSave,btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        textViewUserEmail= (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome"+" "+user.getEmail());
        buttonLogout=(Button) findViewById(R.id.buttonLogout);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        editTextName= (EditText) findViewById(R.id.editTextName);
        editTextNumber= (EditText) findViewById(R.id.editTextphonenumber);
        buttonSave=(Button) findViewById(R.id.buttonAdd);
        btnDelete=(Button) findViewById(R.id.buttonDelete);
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    private void saveUserinfomation(){
        String name= editTextName.getText().toString().trim();
        String phoneNumber= editTextNumber.getText().toString().trim();
        UserInformation userInformation=new UserInformation(name,phoneNumber);

        FirebaseUser user= firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Information Saved",Toast.LENGTH_SHORT).show();
    }

    private void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Successfully deleted the account", Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                            finish();
                            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        } else {
                                Toast.makeText(ProfileActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
  if(view ==buttonLogout){
      firebaseAuth.signOut();
      finish();
      startActivity(new Intent(this, LoginActivity.class));
  }
  if (view==buttonSave){
      saveUserinfomation();
  }
  if (view==btnDelete){
      deleteUser();
  }
    }


}
