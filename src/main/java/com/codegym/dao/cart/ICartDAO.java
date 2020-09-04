package com.codegym.dao.cart;

import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ICartDAO {
    public void insertCart(Customer customer, Product product) throws SQLException;


    public boolean minus1Product(Product product);

    public Integer getQuantity(Product product) throws SQLException;

    public List<Product> selectAllCart(Customer customer) throws SQLException;
}
