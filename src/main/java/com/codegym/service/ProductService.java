package com.codegym.service;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    void insertProduct(Product product) throws SQLException;

     Product selectProduct(int id);

     List<Product> selectAllProduct();

     boolean deleteProduct(int id) throws SQLException;

     boolean updateProduct(Product product) throws SQLException;

     Product getProductById(int id);

     void insertProductStore(Product product) throws SQLException;

     List<Product> getProductByName(String name);



}
