package com.codegym.model;

import java.util.Date;

public class Order {
    private int orderNumber;
    private int productCode;
    private int cusNumber;
    private int quantityOrdered;
    private String status;
    private String orderDate;

    public Order() {
    }


    public Order(int orderNumber, int productCode, int cusNumber, int quantityOrdered, String status, String orderDate) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.cusNumber = cusNumber;
        this.quantityOrdered = quantityOrdered;
        this.status = status;
        this.orderDate = orderDate;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(int cusNumber) {
        this.cusNumber = cusNumber;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
