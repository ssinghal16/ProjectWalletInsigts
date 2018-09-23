package com.achivers.ssinghal.walletinsights;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.achivers.ssinghal.walletinsights.model.Category;
import com.achivers.ssinghal.walletinsights.model.IncomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {
    FirebaseDatabase mdatabase;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    IncomeModel incomeModel;
    RecyclerView recyclerView;
    ArrayList<IncomeModel> list;
    IncomeAdapter incomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        incomeModel = new IncomeModel();
        firebaseAuth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        ref = mdatabase.getReference("Income");
        String uuid = firebaseAuth.getUid().toString();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIncome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Query query = ref.child(uuid).orderByChild("timestamp");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<IncomeModel>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //String name = dataSnapshot.getValue(String.class);
                    try {
                        IncomeModel im = ds.getValue(IncomeModel.class);
                        list.add(im);
                    } catch (Exception e) {
                        //
                    }
                }
                Collections.reverse(list);
                incomeAdapter = new IncomeAdapter(IncomeActivity.this, list);
                recyclerView.setAdapter(incomeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddIncome);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent incomeAct= new Intent(IncomeActivity.this,AddIncome.class);
                startActivity(incomeAct);
            }
        });
    }


}
