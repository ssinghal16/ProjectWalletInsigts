package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private Button btnDelete, btnchangepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        firebaseAuth= FirebaseAuth.getInstance();

        btnDelete= (Button) findViewById(R.id.buttonDelete);
        btnchangepass=(Button) findViewById(R.id.btnchnagepass);

        btnDelete.setOnClickListener(this);
        btnchangepass.setOnClickListener(this);

    }

    private void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Settings.this, "Successfully deleted the account", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Settings.this, LoginActivity.class));
                    } else {
                        Toast.makeText(Settings.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if(view==btnDelete){
            deleteUser();
        }
        if(view==btnchangepass){
//to be implemented
        }
    }
}
