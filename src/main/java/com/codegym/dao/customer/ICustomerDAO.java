package com.codegym.dao.customer;

import com.codegym.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public boolean insertCustomer(Customer customer) throws SQLException;

    public Customer selectCustomer(int id) throws SQLException;

    public Customer selectCustomer(String userName) throws SQLException;

    public List<Customer> selectAllCustomer() throws SQLException;

    public boolean deleteCustomer(int id) throws SQLException;

    public boolean updateUser(Customer customer) throws SQLException;





}
