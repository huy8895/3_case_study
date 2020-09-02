package com.codegym.dao.customer;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;

    public Customer selectCustomer(int id) throws SQLException;

    public List<Customer> selectAllCustomer() throws SQLException;

    public boolean deleteCustomer(int id) throws SQLException;

    public boolean updateUser(Customer customer) throws SQLException;





}
