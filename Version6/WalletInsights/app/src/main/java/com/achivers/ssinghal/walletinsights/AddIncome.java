package com.achivers.ssinghal.walletinsights;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.achivers.ssinghal.walletinsights.model.IncomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class AddIncome extends AppCompatActivity implements View.OnClickListener{

    private Button btnNext;
    private Spinner spinner;
    private EditText editTextLabel, editTextDate, editTextAmount;
    DatePickerDialog datePickerDialog;
  //  ListView listView;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferenceAddIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        btnNext=(Button) findViewById(R.id.btnNext);
        editTextLabel= (EditText) findViewById(R.id.textLabel);
        editTextDate=(EditText) findViewById(R.id.textDate);
        editTextAmount=(EditText) findViewById(R.id.editamount);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new ItemSelectedListener());

        btnNext.setOnClickListener(this);
        editTextDate.setOnClickListener(this);

        //listView= (ListView) findViewById(R.id.listViewAddIncome);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReferenceAddIncome= firebaseDatabase.getInstance().getReference("Income");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeMethod();

            }
        });

    }

    private void addIncomeMethod() {

        String uuid= firebaseAuth.getUid().toString();
        String amount = editTextAmount.getText().toString();
        String source= spinner.getSelectedItem().toString();
        String label= editTextLabel.getText().toString();
        String date1= editTextDate.getText().toString();

        if(!TextUtils.isEmpty(amount)){
            String id = databaseReferenceAddIncome.push().getKey();

            IncomeModel addIncomeModel= new IncomeModel(id, amount,source,label,date1);

            //Saving

            databaseReferenceAddIncome.child(uuid).child(id).setValue(addIncomeModel);
            editTextAmount.setText("");
            editTextLabel.setText("");
            editTextDate.setText("");

            Toast.makeText(this, "Income added", Toast.LENGTH_LONG).show();
            Intent i= new Intent(AddIncome.this, IncomeActivity.class);
            startActivity(i);
            finish();
        }

        else{
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        if(view==editTextDate) {
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
                    editTextDate.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                }                     }, mYear, mMonth, mDay);
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