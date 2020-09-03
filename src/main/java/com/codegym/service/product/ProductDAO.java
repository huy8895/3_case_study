package com.codegym.service.product;

import com.codegym.model.product.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/cs3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product" + "(productName, productBrand, productPrice, productImage, productLine) VALUES " + "(?,?,?,?,?)";

    @Override
    public void insertProduct(Product product) throws SQLException {

    }

    @Override
    public Product selectProduct(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        return false;
    }
}
