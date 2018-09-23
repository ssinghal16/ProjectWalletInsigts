package com.achivers.ssinghal.walletinsights;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.achivers.ssinghal.walletinsights.model.IncomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddIncome extends AppCompatActivity implements View.OnClickListener {
    private String key;
    private Button btnNext;
    private Spinner spinner;
    private EditText editTextLabel, editTextDate, editTextAmount;
    DatePickerDialog datePickerDialog;
    //  ListView listView;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferenceAddIncome;

    private List<String> spinnerArray = new ArrayList<>();
    private List<String> newList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        btnNext = (Button) findViewById(R.id.btnNext);
        editTextLabel = (EditText) findViewById(R.id.textLabel);
        editTextDate = (EditText) findViewById(R.id.textDate);
        editTextAmount = (EditText) findViewById(R.id.editamount);
        spinnerArray = Arrays.asList(this.getResources().getStringArray(R.array.android_dropdownincome));
        //spinn= (Spinner) findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(new ItemSelectedListener());


        newList= new LinkedList<>(spinnerArray);

        btnNext.setOnClickListener(this);
        editTextDate.setOnClickListener(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        try {
            key = getIntent().getExtras().get("key").toString();
        } catch (Exception e) {
            key = "";
        }
        LinearLayout layout=(LinearLayout)findViewById(R.id.l1) ;
        spinner=new Spinner(this);


        if (!key.isEmpty()) {

            String uuid = firebaseAuth.getUid().toString();
            databaseReferenceAddIncome = FirebaseDatabase.getInstance().getReference("Income").child(uuid).child(key);
            databaseReferenceAddIncome.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String source = dataSnapshot.child("source").getValue(String.class);
               //     newList = new ArrayList<>(spinnerArray);
                    //int index = spinnerArray.indexOf(source);
                    newList.remove(source);
                    newList.set(0,source);

                    String amount = dataSnapshot.child("amount").getValue(String.class);
                    editTextAmount.setText(amount);
                    String label = dataSnapshot.child("label").getValue(String.class);
                    editTextLabel.setText(label);
                    String date = dataSnapshot.child("dateOfCreation").getValue(String.class);
                    editTextDate.setText(date);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, newList));
        }
        else {
            spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray));
        }
        layout.addView(spinner);

        databaseReferenceAddIncome = firebaseDatabase.getInstance().getReference("Income");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeMethod(key);
               }

        });

    }

    private void addIncomeMethod(String key) {

        String uuid = firebaseAuth.getUid().toString();
        String amount = editTextAmount.getText().toString();
        String source = spinner.getSelectedItem().toString();
        String label = editTextLabel.getText().toString();
        String date1 = editTextDate.getText().toString();
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

        if (!TextUtils.isEmpty(amount)) {
            if (key.isEmpty()) {

                key = databaseReferenceAddIncome.push().getKey();
            }
            IncomeModel addIncomeModel = new IncomeModel(key, amount, source, label, date1, timeStamp);

            //Saving

            databaseReferenceAddIncome.child(uuid).child(key).setValue(addIncomeModel);
            /*editTextAmount.setText("");
            editTextLabel.setText("");
            editTextDate.setText("");*/

            Toast.makeText(this, "Income added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddIncome.this, IncomeActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        if (view == editTextDate) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(AddIncome.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }


    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            firstItem.equals(String.valueOf(spinner.getSelectedItem()));

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }
    }
}