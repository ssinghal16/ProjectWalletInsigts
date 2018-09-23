package com.achivers.ssinghal.walletinsights.model;


public class Category {

    private String categoryName;
    private String bAmount;
    private String categoryId;

    public Category() {

    }

    public Category(String categoryId, String categoryName, String bAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.bAmount = bAmount;

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


    public String getPercentage() {
        return bAmount;
    }

    public void setPercentage(String bAmount) {
        this.bAmount = bAmount;
    }


}

