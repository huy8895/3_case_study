package com.codegym.dao;

import com.codegym.dao.cart.CartDAO;
import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.order.OrderDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.dao.user.UserDAO;

public class DAOManger {
    public final CartDAO cartDAO = new CartDAO();
    public final CustomerDAO customerDAO = new CustomerDAO();
    public final OrderDAO orderDAO = new OrderDAO();
    public final ProductDAO productDAO = new ProductDAO();
    public final UserDAO userDAO = new UserDAO();
}
