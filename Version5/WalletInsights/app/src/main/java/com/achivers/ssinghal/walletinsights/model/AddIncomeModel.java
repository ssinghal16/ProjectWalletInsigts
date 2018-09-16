package com.achivers.ssinghal.walletinsights.model;

import android.text.Editable;

import com.achivers.ssinghal.walletinsights.AddIncome;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class AddIncomeModel {

    private String id;
    private String amount;
    private String source;
    private String label;
    private String dateCreation;
    //private String incomeid;

    public AddIncomeModel(){

    }

    public AddIncomeModel(String id, String amount, String source, String label, String dateCreation) {
        this.id=id;
        this.amount = amount;
        this.source = source;
        this.label = label;
        this.dateCreation = dateCreation;

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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }


}
