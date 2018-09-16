package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class BudgetActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnCreateBudget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

       textView= (TextView) findViewById(R.id.budgetTextDisplayId);
        btnCreateBudget= (Button) findViewById(R.id.createBudget);

        btnCreateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(BudgetActivity.this, CreateBudgetActivity.class);
                startActivity(b);
            }
        });
    }


}
