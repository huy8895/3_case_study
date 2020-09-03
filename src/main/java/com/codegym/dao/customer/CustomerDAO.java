package com.codegym.dao.customer;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/cs3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer" +
            " (cusName, cusPhoneNumber,cusAddress,cusEmail,userName) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID_SQL = "SELECT * FROM Customer where cusNumber = ?;";

    private static final String SELECT_ALL_CUSTOMERS_SQL = "SELECT * FROM Customer";

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

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
        try {
            preparedStatement.setString(1,customer.getCusName());
            preparedStatement.setString(2,customer.getCusPhoneNumber());
            preparedStatement.setString(3,customer.getCusAddress());
            preparedStatement.setString(4,customer.getCusEmail());
            preparedStatement.setString(5,customer.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomer(int id)  {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID_SQL);) {
             preparedStatement.setInt(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("cusName");
                String phone = resultSet.getString("cusPhoneNumber");
                String address = resultSet.getString("cusAddress");
                String email = resultSet.getString("cusEmail");
                String userName = resultSet.getString("userName");
                customer = new Customer(id, name, phone, address, email, userName);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        try
                (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS_SQL);) {
                 ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cusNumber = resultSet.getInt("cusNumber");
                String name = resultSet.getString("cusName");
                String phone = resultSet.getString("cusPhoneNumber");
                String address = resultSet.getString("cusAddress");
                String email = resultSet.getString("cusEmail");
                String userName = resultSet.getString("userName");
                customers.add(new Customer(cusNumber, name, phone, address, email, userName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted ;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_SQL);
        statement.setInt(1,id);
        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    @Override
    public boolean updateUser(Customer customer) throws SQLException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
        preparedStatement.setString(1,customer.getCusName());
        preparedStatement.setString(2,customer.getCusPhoneNumber());
        preparedStatement.setString(3,customer.getCusAddress());
        preparedStatement.setString(4,customer.getCusEmail());
        preparedStatement.setInt(5,customer.getCusNumber());
        rowUpdated = preparedStatement.executeUpdate() > 0;
        return rowUpdated;
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
