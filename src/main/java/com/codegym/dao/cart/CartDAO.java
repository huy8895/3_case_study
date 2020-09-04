package com.codegym.dao.cart;

import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements ICartDAO {
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Cart where productCode = ?";
    private static final String UPDATE_ADD1_PRODUCT_SQL = "update Cart set quantity = ? where productCode = ? and cusNumber = ?; ";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Cart (cusNumber,productCode) values (?,?) ;";
    private String jdbcURL = "jdbc:mysql://localhost:3306/DBmodule3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer" +
            " (cusName, cusPhoneNumber,cusAddress,cusEmail,userName) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM Cart where productCode = ?;";

    private static final String SELECT_ALL_PRODUCTS_SQL = "select * from Product join Cart on Cart.productCode = Product.productCode where cusNumber = ?;";

    private static final String DELETE_CUSTOMER_BY_ID_SQL = "DELETE FROM Customer WHERE cusNumber = ?;";

    private static final String UPDATE_CUSTOMER_SQL = "UPDATE Customer SET " +
            "cusName = ?, cusPhoneNumber = ? , cusAddress = ?, cusEmail = ?" +
            "where cusNumber = ?;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("khong ket noi dc");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            System.out.println("khong ket noi dc 2");
            System.out.println(e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertCart(Customer customer, Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement updateStm = connection.prepareStatement(UPDATE_ADD1_PRODUCT_SQL);
        PreparedStatement insertStm = connection.prepareStatement(INSERT_PRODUCT_SQL);
        int quantity = getQuantity(product) ;
        try {
            if (quantity > 0){
                updateStm.setInt(1,(quantity+1));
                updateStm.setInt(1,product.getProductCode());
                updateStm.setInt(1,customer.getCusNumber());
                updateStm.executeUpdate();
            } else {
                insertStm.setInt(1, customer.getCusNumber());
                insertStm.setInt(2, product.getProductCode());
                insertStm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public boolean minus1Product(Product product) {
        return false;
    }

    @Override
    public Integer getQuantity(Product product) throws SQLException {
        int quantity = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
        preparedStatement.setInt(1,product.getProductCode());
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()){
            quantity = set.getInt(quantity);
        }
        return quantity;
    }

    @Override
    public List<Product> selectAllCart(Customer customer) throws SQLException {
        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_SQL);
        preparedStatement.setInt(1,customer.getCusNumber());
        System.out.println(customer.getCusNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int productCode = resultSet.getInt(1);
            String name = resultSet.getString("productName");
            String brand = resultSet.getString("productBrand");
            double price = resultSet.getDouble("productPrice");
            String image = resultSet.getString("productImage");
            String line = resultSet.getString("productLine");

            products.add(new Product(productCode,name,brand,price,image,line));
        }
        return products;
    }

}
