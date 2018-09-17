package com.achivers.ssinghal.walletinsights.model;

import android.text.Editable;

import com.achivers.ssinghal.walletinsights.AddIncome;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class IncomeModel {

    private String id;
    private String amount;
    private String source;
    private String label;
    private String dateOfCreation;

    //private String incomeid;

    public IncomeModel() {

    }

    public IncomeModel(String id, String amount, String source, String label, String dateOfCreation) {
        this.id = id;
        this.amount = amount;
        this.source = source;
        this.label=label;
        this.dateOfCreation=dateOfCreation;

    }
    public IncomeModel(String id, String amount, String source) {
        this.id = id;
        this.amount = amount;
        this.source = source;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    List<IncomeModel> incomeModels;

    private void initializeData(){
        incomeModels=new ArrayList<>();
        incomeModels.add(new IncomeModel(id,amount,source));

    }
}
