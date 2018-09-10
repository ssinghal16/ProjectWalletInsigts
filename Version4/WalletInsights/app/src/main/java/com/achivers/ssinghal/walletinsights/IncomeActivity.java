package com.achivers.ssinghal.walletinsights;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.achivers.ssinghal.walletinsights.model.AddIncomeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {
    ListView listViewAddIncome;
    List<AddIncomeModel> incomeModels;

    DatabaseReference databaseReferenceAddIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        listViewAddIncome= (ListView) findViewById(R.id.listViewAddIncome);
        incomeModels= new ArrayList<>();
        databaseReferenceAddIncome= FirebaseDatabase.getInstance().getReference("AddIncome");
    }

    @Override
    protected void onStart() {

        super.onStart();

        databaseReferenceAddIncome.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                incomeModels.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting addincomeModal

                    AddIncomeModel addIncomeModel=postSnapshot.getValue(AddIncomeModel.class);

                    //adding to the list
                    incomeModels.add(addIncomeModel);
                }

                //creating adapter
                AddIncomeList addIncomeListAdapter= new AddIncomeList(IncomeActivity.this, incomeModels);
                //attaching adapter to the listview
                listViewAddIncome.setAdapter(addIncomeListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
