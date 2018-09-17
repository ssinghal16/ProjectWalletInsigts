package com.achivers.ssinghal.walletinsights.model;


public class Category {

    private String categoryName;
    private String percentage;
    private String categoryId;

    public Category() {

    }

    public Category(String categoryId, String categoryName, String percentage) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.percentage = percentage;

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
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }


}

