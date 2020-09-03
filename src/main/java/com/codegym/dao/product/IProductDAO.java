package com.codegym.dao.product;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
     void insertProduct(Product product) throws SQLException;

     Product selectProduct(int id) throws SQLException;

    List<Product> selectAllProduct() throws SQLException;

     boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    List<Product> getProductByName(String productName);

    void insertProductStore(Product product) throws SQLException;

}
