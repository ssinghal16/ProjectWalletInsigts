package com.achivers.ssinghal.walletinsights.model;

public class CategoryMain {
    String categoryMainName;
    String percentage;

   public CategoryMain(){

    }

    public CategoryMain(String categoryMainName, String percentage) {
        this.categoryMainName = categoryMainName;
        this.percentage = percentage;
    }

    public String getCategoryMainName() {
        return categoryMainName;
    }

    public void setCategoryMainName(String categoryMainName) {
        this.categoryMainName = categoryMainName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
