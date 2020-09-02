package com.codegym.controller;

import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.service.crawdata.CrawlData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
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
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                case "Search":
                    showSearchResult(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList = productDAO.selectAllProduct();

        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showSearchResult(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        CrawlData crawlData = new CrawlData();
        File file = new File("resources/data/Men-Watches.html");
        String content = crawlData.getContent(file);

        System.out.println("lay content thanh cong");
        List<Product> productList = new ArrayList<>();
        productList = crawlData.crawlPhoneData(content);
        for (Product product:productList){
            System.out.println(product.getProductName());
        }

        System.out.println("lay dc list product tu file html");


    }

}
