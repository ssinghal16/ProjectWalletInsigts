package com.achivers.ssinghal.walletinsights;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.achivers.ssinghal.walletinsights.model.Category;
import com.achivers.ssinghal.walletinsights.model.ExpenseModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpense extends AppCompatActivity {

    private Button btnAdd;
    private EditText editTextAmount, editTextDescription, editTextLabel, editTextDate;
    private Spinner spinnerPayment, spinnerCategory;
    DatePickerDialog datePickerDialog;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferenceAddExpense, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceAddExpense = FirebaseDatabase.getInstance().getReference("Expense");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        editTextAmount = (EditText) findViewById(R.id.editamount);
        editTextDescription = (EditText) findViewById(R.id.textDescription);
        editTextLabel = (EditText) findViewById(R.id.textLabel);
        editTextDate = (EditText) findViewById(R.id.textDate);
        spinnerPayment = (Spinner) findViewById(R.id.spinnerPayment);
        spinnerPayment.setOnItemSelectedListener(new AddExpense.ItemSelectedListener());
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        /*spinnerCategory.setOnItemSelectedListener(new AddExpense.ItemSelectedListener());*/


        ref=FirebaseDatabase.getInstance().getReference("Category");
        //final List<Category> categoryList = new ArrayList<>();
        final List<String> categoryList = new ArrayList<>();
       ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uuid= firebaseAuth.getUid().toString();
                for (DataSnapshot catSnapshot : dataSnapshot.child(uuid).getChildren()) {
                    categoryList.add(catSnapshot.getValue(Category.class).getCategoryName());
                }
                ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(AddExpense.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                spinnerCategory.setAdapter(categoryArrayAdapter);
                spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String catName= (String) adapterView.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddExpense.this,
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
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExpenseMethod();
            }
        });
    }



    private void addExpenseMethod() {
        String uuid = firebaseAuth.getUid().toString();
        String amount = editTextAmount.getText().toString();
        String description = editTextDescription.getText().toString();
        String label = editTextLabel.getText().toString();
        String date1 = editTextDate.getText().toString();
        String paymentType = spinnerPayment.getSelectedItem().toString();
        String category = spinnerCategory.getSelectedItem().toString();

        if (!TextUtils.isEmpty(amount)) {
            String expenseid = databaseReferenceAddExpense.push().getKey();

            ExpenseModel addExpenseModel = new ExpenseModel(expenseid, amount, description, label, date1, paymentType, category);

            //Saving

            databaseReferenceAddExpense.child(uuid).child(expenseid).setValue(addExpenseModel);
            editTextAmount.setText("");
            editTextDescription.setText("");
            editTextLabel.setText("");
            editTextDate.setText("");

            Toast.makeText(this, "Income added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_LONG).show();
        }

    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String firstItemc = String.valueOf(adapterView.getItemAtPosition(i));
            String firstItemp = String.valueOf(adapterView.getItemAtPosition(i));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}
