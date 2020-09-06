package com.codegym.model;

public class Cart {
    protected int productCode;
    protected int cusNumber;
    protected int quantity;

    public Cart() {
    }

    public Cart(int product,int  customer, int quantity) {
        this.productCode = product;
        this.cusNumber = customer;
        this.quantity = quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int  getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(int cusNumber) {
        this.cusNumber = cusNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
