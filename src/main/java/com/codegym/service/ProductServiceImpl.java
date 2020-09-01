package com.codegym.service;

import com.codegym.model.Product;

import java.sql.*;
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
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    @Override
    public Product selectProduct(int id) {
       Product product = null;

       try {
           Connection connection = getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
           preparedStatement.setInt(1,id);
           System.out.println(preparedStatement);
           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               String name = resultSet.getString("name");
               Double price = resultSet.getDouble("price");
               String description = resultSet.getString("description");
               String image = resultSet.getString("image");
               product = new Product(id,name,price,description,image);
           }
       } catch (SQLException e) {
          printSQLException(e);
       }
        return product;
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

    private void printSQLException(SQLException e) {
    }
}
