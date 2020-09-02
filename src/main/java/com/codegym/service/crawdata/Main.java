package com.codegym.service.crawdata;

import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        CrawlData crawlData = new CrawlData();
        File file = new File("resources/data/Men-Watches.html");
        String content = crawlData.getContent(file);

        System.out.println("lay content thanh cong");
        List<Product> productList = new ArrayList<>();
        productList = crawlData.crawlPhoneData(content);

        System.out.println("lay dc list product tu file html");

//        ProductDAO productDAO = new ProductDAO();
//        Product productnew = new Product();
//        productnew.setProductName("iphone");
//        productDAO.insertProduct(productnew);
        for (Product product:productList){
            System.out.println(product.getProductName());
        }

    }
}
