package com.codegym.dao.customer;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    void insertCustomer(Customer customer) throws SQLException;

    Customer selectCustomer(int id) throws SQLException;

    List<Customer> selectAllCustomers() throws SQLException;

     boolean deleteCustomer(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;


    List<Customer> getCustomerByName(String cusName);



}
