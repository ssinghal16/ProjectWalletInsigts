package com.achivers.ssinghal.walletinsights;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.achivers.ssinghal.walletinsights.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CreateBudgetActivity extends AppCompatActivity {

    FirebaseDatabase mdatabase;
    DatabaseReference ref;
    Category category;
    BudgetAdapter budgetAdapter;
    EditText categoryName, percentageText;

    RecyclerView recyclerView;
    ArrayList<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);


        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        //LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int orientation = LinearLayout.VERTICAL;
        // Use default linear layout item divider.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),orientation);
        recyclerView.addItemDecoration(dividerItemDecoration);

        category = new Category();
        mdatabase = FirebaseDatabase.getInstance();
        //  ref = mdatabase.getReference().child("Category");
        ref = mdatabase.getReference("Category");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list= new ArrayList<Category>();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //String name = dataSnapshot.getValue(String.class);
                    try {
                        Category c = ds.getValue(Category.class);
                        list.add(c);
                    }
                    catch (Exception e){
                        //
                    }
                }
                budgetAdapter= new BudgetAdapter(CreateBudgetActivity.this, list);
                recyclerView.setAdapter(budgetAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddC);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddCategory();
            }
        });
    }


     public void btnAddCategory(){

        final   Dialog d=  new Dialog(this);
        d.setContentView(R.layout.input_create_budget);

        categoryName = (EditText) d.findViewById(R.id.categoryName);
        percentageText= (EditText) d.findViewById(R.id.percentageName);


        Button   addBtn = (Button) d.findViewById(R.id.addBtn);
        final Button   cancelBtn = (Button) d.findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });

        //SAVE
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = categoryName.getText().toString();
                String percentage1=percentageText.getText().toString();

                final String key= ref.push().getKey();
                category.setCategoryName(name);
                category.setPercentage(percentage1);
                category.setCategoryId(key);
                if (name != null && name.length() > 0) {
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child(key).setValue(category);
                            Toast.makeText(CreateBudgetActivity.this, "Data added...", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    } );
                    d.dismiss();
                   /* budgetAdapter= new BudgetAdapter(CreateBudgetActivity.this, list);
                    recyclerView.setAdapter(budgetAdapter);*/
                }

                else {
                    Toast.makeText(CreateBudgetActivity.this, "Name must not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        d.show();
    }

}


