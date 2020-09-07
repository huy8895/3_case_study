package com.codegym.dao.order;

import com.codegym.model.Customer;
import com.codegym.model.Order;

import java.sql.SQLException;

public interface IOrderDAO {
    public void insertOrder(Order order) throws SQLException;

    public Integer getOrderNumber() throws SQLException;


}
