package com.codegym.dao.product;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void insertProduct(Product customer) throws SQLException;

    public Product selectProduct(int id) throws SQLException;

    public List<Product> selectAllProduct() throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

    public boolean updateProduct(Product product) throws SQLException;

    public List<Product> getProductsBySearch(String productName,String minPrice,String maxPrice,String productBrand,String productLine) throws SQLException ;
}
