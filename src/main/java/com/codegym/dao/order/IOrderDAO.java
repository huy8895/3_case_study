package com.codegym.dao.order;

import com.codegym.model.Customer;
import com.codegym.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    public void insertOrder(Order order) throws SQLException;

    public Integer getOrderNumber() throws SQLException;


    public List<Order> selectOrder(Customer customer) throws SQLException;
}
