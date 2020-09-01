package com.codegym.dao.customer;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.*;
import java.util.Collection;

public class CustomerDAO implements ICustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/DBmodule3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO Customer" +
            " (cusName, cusPhoneNumber,cusAddress,cusEmail,userName) VALUES " +
            " (?, ?, ?, ?, ?);";

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
    public void insertUser(Customer customer, User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        try {
            preparedStatement.setString(1,customer.getCusName());
            preparedStatement.setString(2,customer.getCusName());
            preparedStatement.setString(3,customer.getCusName());
            preparedStatement.setString(4,customer.getCusName());
            preparedStatement.setString(5,user.getUserName());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
