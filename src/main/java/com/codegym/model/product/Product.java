package com.codegym.model.product;

public class Product {
    protected int productCode;
    protected String productName;
    protected String productBrand;
    protected Double productPrice;
    protected String productImage;
    protected String productLine;

    public Product() {
    }

    public Product(int productCode, String productName, String productBrand, Double productPrice, String productImage, String productLine) {
        this.productCode = productCode;
        this.productName = productName;
        this.productBrand = productBrand;
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

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
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
