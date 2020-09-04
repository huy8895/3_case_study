package com.codegym.model;

public class Product {
    protected int productCode;
    protected String productName;
    protected String productBrand;
    protected double productPrice;
    protected String productImage;
    protected String productLine;

    public Product() {
    }

    public Product(String productName, String productBrand, double productPrice, String productImage, String productLine) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productLine = productLine;
    }

    public Product(int productCode, String productName, String manufacture, double productPrice, String productImage, String productLine) {
        this.productCode = productCode;
        this.productName = productName;
        this.productBrand = manufacture;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productLine = productLine;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }
}