package com.achivers.ssinghal.walletinsights;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddExpense extends AppCompatActivity {
    private String key, category;
    private Button btnAdd;
    private EditText editTextAmount, editTextDescription, editTextLabel, editTextDate;
    private Spinner spinnerPayment, spinnerCategory;
    DatePickerDialog datePickerDialog;
    private List<String> spinnerArray = new ArrayList<>();
    private List<String> spinnerArrayPayment= new ArrayList<>();
    private List<String> newList, payList;
    private List<String> categoryList;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceAddExpense, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        ref = FirebaseDatabase.getInstance().getReference("Category");
        btnAdd = (Button) findViewById(R.id.btnAdd);
        editTextAmount = (EditText) findViewById(R.id.editamount);
        editTextDescription = (EditText) findViewById(R.id.textDescription);
        editTextLabel = (EditText) findViewById(R.id.textLabel);
        editTextDate = (EditText) findViewById(R.id.textDate);
        spinnerArrayPayment=Arrays.asList(this.getResources().getStringArray(R.array.payment_Type));


       // spinnerArray= Arrays.categoryList(this.spinnerCategory);

        //spinnerArray=Arrays.asList(this.spinnerCategory.toString());
        try {
            key = getIntent().getExtras().get("key").toString();
        } catch (Exception e) {
            key = "";
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.l2);
        LinearLayout layoutPayment = (LinearLayout) findViewById(R.id.l3);
        spinnerCategory = new Spinner(this);
        spinnerPayment= new Spinner(this);

        payList= new LinkedList<>(spinnerArrayPayment);
        if (!key.isEmpty()) {
            String uuid = firebaseAuth.getUid().toString();

            databaseReferenceAddExpense = FirebaseDatabase.getInstance().getReference("Expense").child(uuid).child(key);
            databaseReferenceAddExpense.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    category= dataSnapshot.child("category").getValue(String.class);

                    String amount = dataSnapshot.child("amount").getValue(String.class);
                    editTextAmount.setText(amount);

                    String desc = dataSnapshot.child("description").getValue(String.class);
                    editTextDescription.setText(desc);

                    String label = dataSnapshot.child("label").getValue(String.class);
                    editTextLabel.setText(label);

                    String date = dataSnapshot.child("edate").getValue(String.class);
                    editTextDate.setText(date);

                    String payment= dataSnapshot.child("paymentType").getValue(String.class);
                    payList.remove(payment);
                    payList.set(0, payment);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            spinnerPayment.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, payList));
        }
          else {
            //spinnerCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray));
            spinnerPayment.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArrayPayment));
        }
        displayCategorySpinner();
        layout.addView(spinnerCategory);
        layoutPayment.addView(spinnerPayment);
        databaseReferenceAddExpense = firebaseDatabase.getReference("Expense");

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
                addExpenseMethod(key);
            }
        });
    }


    private void addExpenseMethod(String key) {
        String uuid = firebaseAuth.getUid().toString();
        String amount = editTextAmount.getText().toString();
        String description = editTextDescription.getText().toString();
        String label = editTextLabel.getText().toString();
        String date1 = editTextDate.getText().toString();
        String paymentType = spinnerPayment.getSelectedItem().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

        if (!TextUtils.isEmpty(amount)) {
            if (key.isEmpty()) {

                key = databaseReferenceAddExpense.push().getKey();
            }

            ExpenseModel addExpenseModel = new ExpenseModel(key, amount, description, label, date1, paymentType, category, timeStamp);

            databaseReferenceAddExpense.child(uuid).child(key).setValue(addExpenseModel);
            /*editTextAmount.setText("");
            editTextDescription.setText("");
            editTextLabel.setText("");
            editTextDate.setText("");
*/
            Toast.makeText(this, "Expense added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddExpense.this, ExpenseActivity.class);
            startActivity(i);
            finish();

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

    public void displayCategorySpinner(){
        categoryList = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uuid = firebaseAuth.getUid().toString();
                for (DataSnapshot catSnapshot : dataSnapshot.child(uuid).getChildren()) {
                    categoryList.add(catSnapshot.getValue(Category.class).getCategoryName());
                }
                if (!key.isEmpty()) {
                    newList = new LinkedList<>(categoryList);
                    newList.remove(category);
                    newList.set(0, category);
                    ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(AddExpense.this, android.R.layout.simple_spinner_dropdown_item, newList);
                    spinnerCategory.setAdapter(categoryArrayAdapter);
                }
                else{
                    ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(AddExpense.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                    spinnerCategory.setAdapter(categoryArrayAdapter);
                }

                spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String catName = (String) adapterView.getSelectedItem();
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
    }


}
