package com.achivers.ssinghal.walletinsights.model;

public class UserModal {

    private String userId;
    private String uName;
    private String uEmail;
    private String familyId;


    // Default constructor required for calls to
    // DataSnapshot.getValue(UserModal.class)
    public UserModal(){

    }

    public UserModal(String userId, String uName, String uEmail, String familyId) {
        this.userId = userId;
        this.uName = uName;
        this.uEmail = uEmail;
        this.familyId = familyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
