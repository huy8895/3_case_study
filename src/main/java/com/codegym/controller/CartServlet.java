package com.codegym.controller;

import com.codegym.dao.cart.CartDAO;
import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.order.OrderDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.model.Cart;
import com.codegym.model.Customer;
import com.codegym.model.Order;
import com.codegym.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    public void init() {
        cartDAO = new CartDAO();
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
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
                case "pay":
                    pay(request, response);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (!request.getParameter("cusNumber").equals("")) {
            int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
            Customer customer = customerDAO.selectCustomer(cusNumber);
            List<Cart> cartList = cartDAO.selectAllCart(customer);
            request.setAttribute("cartList", cartList);
            request.setAttribute("customer", customer);
            request.setAttribute("productDAO", productDAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/cart.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Product> productList = productDAO.selectAllProduct();
            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteCart(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updatePCart(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
        int productCode = Integer.parseInt(request.getParameter("productCode"));
        Customer customer = customerDAO.selectCustomer(cusNumber);
        Product product = productDAO.selectProduct(productCode);
        System.out.println("add to cart of : " + customer.getCusName());
        System.out.println("product selected : " + product.getProductCode());
        String ads = request.getParameter("cusNumber");
        String asdf = request.getParameter("productCode");


        cartDAO.insertCart(customer, product);
        List<Product> productList = productDAO.selectAllProduct();
        request.setAttribute("customer", customer);
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
        dispatcher.forward(request, response);

    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        System.out.println("cusNumber :" + request.getParameter("cusNumber"));
        System.out.println("dang thanh toan");
        int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));

        Customer customer = customerDAO.selectCustomer(cusNumber);
        List<Cart> cartList = cartDAO.selectAllCart(customer);
        int orderNumber = orderDAO.getOrderNumber() + 1;

        for (Cart cart:cartList){
            int productCode = cart.getProductCode();
            int quantity = cart.getQuantity();
            String status = "done";
            String currentDate = java.time.LocalDate.now().toString();
            orderDAO.insertOrder(new Order(orderNumber,productCode,cusNumber,quantity,status,currentDate));
        }
        cartDAO.clearCart(customer);
        System.out.println("thanh toan thanh cong");
    }
}
