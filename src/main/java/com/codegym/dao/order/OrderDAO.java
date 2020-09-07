package com.codegym.dao.order;

import com.codegym.dao.database.Jdbc;
import com.codegym.model.Customer;
import com.codegym.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO{
    private static final String GET_ORDER = "select max(orderNumber) from OrderDetail ;";

    private static final String INSERT_ORDER = "insert into OrderDetail (orderNumber,cusNumber,productCode, quantityOrdered,status,orderDate) values (?,?,?,?,?,?);";

    private static final String SELECT_ORDER_BY_CUSNUMBER = "SELECT * FROM OrderDetail WHERE cusNumber = ?;";



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
        if (resultSet.next()){
            count  = resultSet.getInt(1);
        }
        System.out.println("count = " + count);
        return count;

    }

    @Override
    public List<Order> selectOrder(Customer customer) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_CUSNUMBER);
        try{
            System.out.println("GETTING order");
            System.out.println("order number: " + customer.getCusNumber() );
            preparedStatement.setInt(1,customer.getCusNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
//                int orderNumber, int productCode, int cusNumber, int quantityOrdered, String status, String orderDate
                int orderNumber = resultSet.getInt("orderNumber");
                int productCode = resultSet.getInt("productCode");
                int cusNumber = resultSet.getInt("cusNumber");
                int quantityOrdered = resultSet.getInt("quantityOrdered");
                String status = resultSet.getString("status");
                String orderDate = resultSet.getString("orderDate");
                orderList.add(new Order(orderNumber,productCode,cusNumber,quantityOrdered,status,orderDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

}
