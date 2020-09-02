package com.codegym.dao.product;

import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/DBmodule3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product" +
            " (productName, productBrand,productPrice,productImage,productLine) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM Product where cusNumber = ?;";

    private static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM Product";

    private static final String DELETE_PRODUCT_BY_ID_SQL = "DELETE FROM Product WHERE cusNumber = ?;";

    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET " +
            "cusName = ?, cusPhoneNumber = ? , cusAddress = ?, cusEmail = ?" +
            "where cusNumber = ?;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void insertProduct(Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        try {
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setString(2,product.getProductBrand());
            preparedStatement.setDouble(3,product.getProductPrice());
            preparedStatement.setString(4,product.getProductImage());
            preparedStatement.setString(5,product.getProductLine());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Product selectProduct(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int productCode = resultSet.getInt("productCode");
            String name = resultSet.getString("productName");
            String brand = resultSet.getString("productBrand");
            Double price = resultSet.getDouble("productPrice");
            String image = resultSet.getString("productImage");
            String line = resultSet.getString("productLine");

            products.add(new Product(name,brand,price,image,line));
        }
        return products;
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
