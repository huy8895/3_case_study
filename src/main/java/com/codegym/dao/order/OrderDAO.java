package com.codegym.dao.order;

import com.codegym.dao.database.Jdbc;
import com.codegym.model.Customer;
import com.codegym.model.Order;

import java.sql.*;

public class OrderDAO implements IOrderDAO{
    private static final String GET_ORDER = "select orderNumber from OrderDetail group by orderNumber;";

    private static final String INSERT_ORDER = "insert into OrderDetail (orderNumber,cusNumber,productCode, quantityOrdered,status,orderDate) values (?,?,?,?,?,?);";

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
    public void insertOrder(Order order) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
        try{
            System.out.println("dang insert order");
            System.out.println("order number: " + order.getOrderNumber() );
            System.out.println("order number: " + order.getProductCode() );
            System.out.println("order number: " + order.getCusNumber() );
            preparedStatement.setInt(1, order.getOrderNumber());
            preparedStatement.setInt(2, order.getCusNumber());
            preparedStatement.setInt(3, order.getProductCode());
            preparedStatement.setInt(4, order.getQuantityOrdered());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getOrderDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getOrderNumber() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ORDER);
        int count = 0;
        while (resultSet.next()){
            count++;
        }
        System.out.println("count = " + count);
        return count;

    }

}
