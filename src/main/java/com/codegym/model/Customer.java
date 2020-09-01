package com.codegym.model;

public class Customer {
    protected int cusNumber;
    protected String cusName;
    protected String cusPhoneNumber;
    protected String cusAddress;
    protected String cusEmail;
    protected String userName;

    public Customer() {
    }

    public Customer(String cusName, String cusPhoneNumber, String cusAddress, String cusEmail,String userName) {
        this.cusName = cusName;
        this.cusPhoneNumber = cusPhoneNumber;
        this.cusAddress = cusAddress;
        this.cusEmail = cusEmail;
        this.userName = userName;
    }
    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhoneNumber() {
        return cusPhoneNumber;
    }

    public void setCusPhoneNumber(String cusPhoneNumber) {
        this.cusPhoneNumber = cusPhoneNumber;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}