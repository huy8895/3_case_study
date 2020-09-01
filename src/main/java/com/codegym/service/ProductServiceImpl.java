package com.codegym.service;

import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/case_study?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

   private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + "(id,name,price,description,image) VALUES "
           + "(?,?,?,?,?);";
   private static final String SELECT_PRODUCT_BY_ID = "select id,name,price,description,image from product where id = ?";
   private static final String SELECT_ALL_PRODUCTS = "select * from product";
   private static final String DELETE_PRODUCTS_SQL = "delete from product where id = ?";
   private static final String UPDATE_PRODUCTS_SQL = "update product set name = ?,price = ?,description = ?,image = ? where id = ?";

   public ProductServiceImpl(){
   }

   protected Connection getConnection(){
       Connection connection = null;
   }


    @Override
    public void insertProduct(Product product) throws SQLException {

    }

    @Override
    public Product selectProduct(int id) {
        return null;
    }

    @Override
    public List<Product> selectAllProduct() {
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

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public void insertProductStore(Product product) throws SQLException {

    }

    @Override
    public List<Product> getProductByName(String name) {
        return null;
    }
}
