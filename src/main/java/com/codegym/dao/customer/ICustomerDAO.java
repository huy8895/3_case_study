package com.codegym.dao.customer;

import com.codegym.model.Customer;

import java.sql.SQLException;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;

}
