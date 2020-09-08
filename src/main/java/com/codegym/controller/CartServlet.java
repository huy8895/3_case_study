package com.codegym.controller;

import com.codegym.dao.DAOManger;
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
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private DAOManger daoManger;

    public void init() {
        daoManger = new DAOManger();
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
                case "orderDetail":
                    orderDetail(request, response);
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
        if (!request.getParameter("cusNumber").equals("") && request.getParameter("cusNumber")!=null) {
            int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
            Customer customer = daoManger.customerDAO.selectCustomer(cusNumber);
            List<Cart> cartList = daoManger.cartDAO.selectAllCart(customer);
            request.setAttribute("cartList", cartList);
            request.setAttribute("customer", customer);
            request.setAttribute("productDAO", daoManger.productDAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/customerCart.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Product> productList = daoManger.productDAO.selectAllProduct();
            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/customerCart.jsp");
            dispatcher.forward(request, response);
        }
    }


    private void orderDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (!request.getParameter("cusNumber").equals("") && request.getParameter("cusNumber")!=null) {
            int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
            Customer customer = daoManger.customerDAO.selectCustomer(cusNumber);
            List<Order> orderList = daoManger.orderDAO.selectOrder(customer);
            request.setAttribute("orderList", orderList);
            request.setAttribute("customer", customer);
            request.setAttribute("productDAO", daoManger.productDAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/order/order.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Product> productList = daoManger.productDAO.selectAllProduct();
            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/order/order.jsp");
            dispatcher.forward(request, response);
        }
    }


    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
        int productCode = Integer.parseInt(request.getParameter("productCode"));
        Customer customer = daoManger.customerDAO.selectCustomer(cusNumber);
        Product product = daoManger.productDAO.selectProduct(productCode);

        daoManger.cartDAO.deleteCart(customer, product);
        List<Cart> cartList = daoManger.cartDAO.selectAllCart(customer);
        request.setAttribute("customer", customer);
        request.setAttribute("cartList", cartList);
        request.setAttribute("productDAO", daoManger.productDAO);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/customerCart.jsp");
        dispatcher.forward(request, response);

    }

    private void updatePCart(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
        int productCode = Integer.parseInt(request.getParameter("productCode"));
        Customer customer = daoManger.customerDAO.selectCustomer(cusNumber);
        Product product = daoManger.productDAO.selectProduct(productCode);
        System.out.println("add to cart of : " + customer.getCusName());
        System.out.println("product selected : " + product.getProductCode());


        daoManger.cartDAO.insertCart(customer, product);
        List<Product> productList = daoManger.productDAO.selectAllProduct();
        request.setAttribute("customer", customer);
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        System.out.println("cusNumber :" + request.getParameter("cusNumber"));
        System.out.println("dang thanh toan");
        int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));

        Customer customer = daoManger.customerDAO.selectCustomer(cusNumber);
        List<Cart> cartList = daoManger.cartDAO.selectAllCart(customer);
        int orderNumber = daoManger.orderDAO.getOrderNumber() + 1;

        for (Cart cart:cartList){
            int productCode = cart.getProductCode();
            int quantity = cart.getQuantity();
            String status = "done";
            String currentDate = java.time.LocalDate.now().toString();
            daoManger.orderDAO.insertOrder(new Order(orderNumber,productCode,cusNumber,quantity,status,currentDate));
        }
        daoManger.cartDAO.clearCart(customer);
        System.out.println("thanh toan thanh cong");
    }
}
