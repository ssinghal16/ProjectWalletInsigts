package com.achivers.ssinghal.walletinsights.model;

public class ExpenseModel {

    private String amount;
    private String descrition;
    private String label;
    private String edate;
    private String paymentType;
    private String category;
    private String expenseid;

    public ExpenseModel(){

    }

    public ExpenseModel(String expenseid, String amount, String descrition, String label, String edate, String paymentType, String category) {
        this.expenseid=expenseid;
        this.amount = amount;
        this.descrition = descrition;
        this.label = label;
        this.edate = edate;
        this.paymentType = paymentType;
        this.category = category;
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

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
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
}
