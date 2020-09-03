package com.codegym.service.product;

import com.codegym.model.product.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {

    void insertProduct(Product product) throws SQLException;

    Product selectProduct(int id) throws SQLException;

    List<Product> selectAllProduct() throws SQLException;

    boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

}
