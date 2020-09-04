package com.codegym.service.crawdata;

import com.codegym.model.Product;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        CustomerDAO customerDAO = new CustomerDAO();
//        Customer customer = new Customer("dungvan","012384821","haidung","dung123455@gmail","dung12354");
//        customerDAO.insertCustomer(customer);

        CrawlData crawlData = new CrawlData();
        File file = new File("resources/data/Men-Watches.html");
        String content = crawlData.getContent(file);

        List<Product> productList = new ArrayList<>();
        productList = crawlData.crawlPhoneData(content);
        for (Product product : productList) {
            System.out.println("call insert_product('"
                    + product.getProductName().replace("'","\\\'")
                    + "','" + product.getProductBrand()
                    + "'," + product.getProductPrice()
                    + ",'" + product.getProductImage()
                    + "','" + product.getProductLine().replace("'","\\\'")
                    + "');");

        }

    }
}
