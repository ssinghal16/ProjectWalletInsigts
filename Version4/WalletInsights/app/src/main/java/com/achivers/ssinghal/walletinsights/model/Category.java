package com.achivers.ssinghal.walletinsights.model;

import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Category {

    private String categoryName;
    private String percenatge;
    private String categoryId;

    public Category() {

    }

    public Category(String categoryId,String categoryName, String percenatge) {
        this.categoryId=categoryId;
        this.categoryName = categoryName;
        this.percenatge = percenatge;

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPercenatge() {
        return percenatge;
    }

    public void setPercenatge(String percenatge) {
        this.percenatge = percenatge;
    }



}

