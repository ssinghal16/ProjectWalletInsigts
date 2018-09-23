package com.achivers.ssinghal.walletinsights.model;

public class ExpenseModel {

    private String amount;
    private String description;
    private String label;
    private String edate;
    private String paymentType;
    private String category;
    private String expenseid;
    private String timestamp;

    public ExpenseModel(){

    }

    public ExpenseModel(String expenseid, String amount, String description, String label, String edate, String paymentType, String category, String timestamp) {
        this.expenseid=expenseid;
        this.amount = amount;
        this.description = description;
        this.label = label;
        this.edate = edate;
        this.paymentType = paymentType;
        this.category = category;
        this.timestamp=timestamp;
    }

    public String getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(String expenseid) {
        this.expenseid = expenseid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
