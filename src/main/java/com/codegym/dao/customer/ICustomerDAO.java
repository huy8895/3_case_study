package com.codegym.dao.customer;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.SQLException;

public interface ICustomerDAO {
    public void insertUser(Customer customer, User user) throws SQLException;

}
