package com.codegym.controller;

import com.codegym.dao.DAOManger;
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

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
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
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Customer customer;
        if (request.getParameter("cusNumber") != null ){
            int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
            customer = daoManger.customerDAO.selectCustomer(cusNumber);
            request.setAttribute("customer", customer);
        } else {
            System.out.println("customer =  null" );
            customer = null;
        }
        List<Product> productList = daoManger.productDAO.selectAllProduct();
        request.setAttribute("productList", productList);
        request.setAttribute("customer", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
        dispatcher.forward(request, response);
    }

}
