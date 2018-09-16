package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.achivers.ssinghal.walletinsights.model.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteBudget extends AppCompatActivity{

    private EditText editTextName, editTextPercentage;
    DatabaseReference ref;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_budget);

        editTextName = (EditText) findViewById(R.id.nameTxt1);
        editTextPercentage = (EditText) findViewById(R.id.editPercentage1);
        String key=getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference("Category").child(key);

        editTextName.setText(getIntent().getStringExtra("categoryName").toString());
        editTextPercentage.setText(getIntent().getStringExtra("percentage").toString());

    }

   public void updateBudget(View view){
          ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("categoryName").setValue(editTextName.getText().toString());


                    dataSnapshot.getRef().child("percentage").setValue(editTextPercentage.getText().toString());

                Toast.makeText(UpdateDeleteBudget.this, "Data updated Successfully", Toast.LENGTH_SHORT).show();

                UpdateDeleteBudget.this.finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

 public void deleteBudget(View view){
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UpdateDeleteBudget.this, "Record delete succesfuly...", Toast.LENGTH_SHORT).show();
                    UpdateDeleteBudget.this.finish();
                }
                else{
                    Toast.makeText(UpdateDeleteBudget.this, "Record not delete succesfuly...", Toast.LENGTH_SHORT).show();
                }
            }
        });
            }

  }
