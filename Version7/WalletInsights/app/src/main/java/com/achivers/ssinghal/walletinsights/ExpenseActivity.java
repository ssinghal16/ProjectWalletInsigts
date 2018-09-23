package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.achivers.ssinghal.walletinsights.model.ExpenseModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ExpenseActivity extends AppCompatActivity {
    FirebaseDatabase mdatabase;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    ExpenseModel expenseModel;
    RecyclerView recyclerView;
    ArrayList<ExpenseModel> list;
    ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        expenseModel= new ExpenseModel();
        firebaseAuth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        ref = mdatabase.getReference("Expense");
        String uuid = firebaseAuth.getUid().toString();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewExpense);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Query query = ref.child(uuid).orderByChild("timestamp");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<ExpenseModel>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //String name = dataSnapshot.getValue(String.class);
                    try {
                        ExpenseModel em=ds.getValue(ExpenseModel.class);
                        list.add(em);
                    } catch (Exception e) {
                        //
                    }
                }
                Collections.reverse(list);
                expenseAdapter=new ExpenseAdapter(ExpenseActivity.this,list);
                recyclerView.setAdapter(expenseAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddExpense);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent incomeExp= new Intent(ExpenseActivity.this,AddExpense.class);
                startActivity(incomeExp);
            }
        });
    }
}
