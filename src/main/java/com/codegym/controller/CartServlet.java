package com.codegym.controller;

import com.codegym.dao.cart.CartDAO;
import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.model.Customer;
import com.codegym.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;

    public void init() {
        cartDAO = new CartDAO();
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    insertCart(request, response);
                    break;
                case "edit":
                    updatePCart(request, response);
                    break;
                case "delete":
                    deleteCart(request, response);
                    break;
                case "showCart":
                    showCart(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {

                case "showCart":
                    showCart(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int cusNumber = Integer.parseInt(request.getParameter("id"));
        System.out.println(cusNumber);
        Customer customer = customerDAO.selectCustomer(cusNumber);
        List<Product> productList = cartDAO.selectAllCart(customer);
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/cart.jsp");
        dispatcher.forward(request, response);
    }
    private void deleteCart(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updatePCart(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Customer customer = customerDAO.selectCustomer(6);
        System.out.println("add to cart");
//        int id = Integer.parseInt(request.getParameter("id"));
        String id = request.getParameter("id");
        System.out.println(id);
//        Product product = productDAO.selectProduct(id);

//        cartDAO.insertCart(customer,product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/cart.jsp");
        dispatcher.forward(request, response);
    }


}
