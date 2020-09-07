package com.codegym.dao.product;

import com.codegym.dao.database.Jdbc;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product" +
            " (productName, productBrand,productPrice,productImage,productLine) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM Product where productCode = ?;";
    private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM Product";
    private static final String DELETE_PRODUCT_BY_ID_SQL = "DELETE  FROM Product where productCode = ?;";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET " +
            "productName = ?, productBrand = ? , productPrice = ?, productImage = ?, productLine = ?" +
            "where productCode = ?;";

    private static final String SELECT_BY_SEARCH_FORM_BETWEEN = "SELECT * from Product where productName like ? and productPrice between ? and ? and productBrand like ? and productLine like  ?;";
    private static final String SELECT_BY_SEARCH_FORM_ONLY_MIN = "SELECT * from Product where productName like ? and productPrice > ?  and productBrand like ? and productLine like  ?;";
    private static final String SELECT_BY_SEARCH_FORM_ONLY_MAX = "SELECT * from Product where productName like ? and productPrice < ?  and productBrand like ? and productLine like  ?;";
    private static final String SELECT_BY_SEARCH_FORM_NOT_MIN_MAX = "SELECT * from Product where productName like ?  and productBrand like ? and productLine like  ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Jdbc.jdbcURL, Jdbc.jdbcUsername, Jdbc.jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        try
                (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductBrand());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setString(4, product.getProductImage());
            preparedStatement.setString(5, product.getProductLine());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product selectProduct(int id) throws SQLException {
        Product product = null;
        try
                (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("productName");
                String brand = resultSet.getString("productBrand");
                double price = resultSet.getDouble("productPrice");
                String image = resultSet.getString("productImage");
                String line = resultSet.getString("productLine");

                product = new Product(id, name, brand, price, image, line);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productCode = resultSet.getInt("productCode");
                String name = resultSet.getString("productName");
                String brand = resultSet.getString("productBrand");
                double price = resultSet.getDouble("productPrice");
                String image = resultSet.getString("productImage");
                String line = resultSet.getString("productLine");
                products.add(new Product(productCode, name, brand, price, image, line));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);

        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setString(2, product.getProductBrand());
        preparedStatement.setDouble(3, product.getProductPrice());
        preparedStatement.setString(4, product.getProductImage());
        preparedStatement.setString(5, product.getProductLine());
        preparedStatement.setInt(6, product.getProductCode());

        rowUpdated = preparedStatement.executeUpdate() > 0;

        return rowUpdated;
    }


    public List<Product> getProductsBySearch(String productName, String minPrice, String maxPrice, String productBrand, String productLine) throws SQLException {

        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement;

        if (minPrice.equals("") && maxPrice.equals("")) {
            System.out.println("SELECT_BY_SEARCH_FORM_NOT_MIN_MAX");
            preparedStatement = connection.prepareStatement(SELECT_BY_SEARCH_FORM_NOT_MIN_MAX);
            preparedStatement.setString(1, '%' + productName + '%');
            preparedStatement.setString(2, '%' + productBrand + '%');
            preparedStatement.setString(3, '%' + productLine + '%');
        } else if (minPrice.equals("")) {
            System.out.println("SELECT_BY_SEARCH_FORM_ONLY_MAX");
            preparedStatement = connection.prepareStatement(SELECT_BY_SEARCH_FORM_ONLY_MAX);
            preparedStatement.setString(1, '%' + productName + '%');
            preparedStatement.setDouble(2, Double.parseDouble(maxPrice));
            preparedStatement.setString(3, '%' + productLine + '%');
            preparedStatement.setString(4, '%' + productLine + '%');
        } else if (maxPrice.equals("")) {
            System.out.println("SELECT_BY_SEARCH_FORM_ONLY_MIN");
            preparedStatement = connection.prepareStatement(SELECT_BY_SEARCH_FORM_ONLY_MIN);
            preparedStatement.setString(1, '%' + productName + '%');
            preparedStatement.setDouble(2, Double.parseDouble(minPrice));
            preparedStatement.setString(3, '%' + productLine + '%');
            preparedStatement.setString(4, '%' + productLine + '%');
        } else {
            System.out.println("SELECT_BY_SEARCH_FORM_BETWEEN");
            preparedStatement = connection.prepareStatement(SELECT_BY_SEARCH_FORM_BETWEEN);
            preparedStatement.setString(1, '%' + productName + '%');
            preparedStatement.setDouble(2, Double.parseDouble(minPrice));
            preparedStatement.setDouble(3, Double.parseDouble(maxPrice));
            preparedStatement.setString(4, '%' + productBrand + '%');
            preparedStatement.setString(5, '%' + productLine + '%');
        }

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productCode = resultSet.getInt("productCode");
                String name = resultSet.getString("productName");
                String brand = resultSet.getString("productBrand");
                double price = resultSet.getDouble("productPrice");
                String image = resultSet.getString("productImage");
                String line = resultSet.getString("productLine");
                products.add(new Product(productCode, name, brand, price, image, line));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}

