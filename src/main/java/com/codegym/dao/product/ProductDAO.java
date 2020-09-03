package com.codegym.dao.product;

import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cs3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product" +
            " (productName, productBrand,productPrice,productImage,productLine) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM Product where productCode = ?;";
    private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM Product";
    private static final String DELETE_PRODUCT_BY_ID_SQL = "DELETE * FROM Product where productCode = ?;";
    private static final String UPDATE_PRODUCT_SQL =    "UPDATE Product SET" + "productName = ?,productBrand = ?,productPrice = ?,productImage = ?,productLine = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        try {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductBrand());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setString(4, product.getProductImage());
            preparedStatement.setString(5, product.getProductLine());
        } catch (SQLException e) {
            printSQLException(e);
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
                Double price = resultSet.getDouble("productPrice");
                String image = resultSet.getString("productImage");
                String line = resultSet.getString("productLine");

                product = new Product(id, name, brand, price, image, line);
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        List<Product> product = new ArrayList<>();
        try
                (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productCode = resultSet.getInt("productCode");
                String name = resultSet.getString("productName");
                String brand = resultSet.getString("productBrand");
                Double price = resultSet.getDouble("productPrice");
                String image = resultSet.getString("productImage");
                String line = resultSet.getString("productLine");
                product.add(new Product(productCode, name, brand, price, image, line));
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID_SQL);
        statement.setInt(1,id);
        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
        preparedStatement.setString(1,product.getProductName());
        preparedStatement.setString(2,product.getProductBrand());
        preparedStatement.setDouble(3,product.getProductPrice());
        preparedStatement.setString(4,product.getProductImage());
        preparedStatement.setString(5,product.getProductLine());
        rowUpdated = preparedStatement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> products = new ArrayList<>();
        String query = "{CALL_GET_PRODUCT_BY_NAME(?)}";
        try
                (Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(query);){
                callableStatement.setString(1,productName);
                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    int productCode = resultSet.getInt("id");
                    String name = resultSet.getString("productName");
                    String brand = resultSet.getString("productBrand");
                    Double price = resultSet.getDouble("productPrice");
                    String image = resultSet.getString("productImage");
                    String line = resultSet.getString("productLine");

                    products.add(new Product(productCode,name,brand,price,image,line));
                }

            System.out.println(callableStatement);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
           printSQLException(e);
           }
           return null;
        }

        private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
