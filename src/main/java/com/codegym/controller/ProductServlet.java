package com.codegym.controller;

import com.codegym.dao.product.ProductDAO;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    deleteProduct(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int productCode = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.selectProduct(productCode);
        productDAO.deleteProduct(productCode);
        listProducts(request,response);
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int productCode = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("productName");
        String brand = request.getParameter("productBrand");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        String image = request.getParameter("productImage");
        String line = request.getParameter("productLine");

        Product product = new Product(productCode,name,brand,price,image,line);
        productDAO.updateProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("productName");
        String brand = request.getParameter("productBrand");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        String image = request.getParameter("productImage");
        String line = request.getParameter("productLine");

        Product newProduct = new Product(name,brand,price,image,line);
        productDAO.insertProduct(newProduct);
        listProducts(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                case "searchPrice":
                    showSearchPrice(request,response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void showSearchPrice(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int productCode = Integer.parseInt(request.getParameter("id"));
       Product product = productDAO.selectProduct(productCode);
       request.setAttribute("product",product);
       RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/delete.jsp");
       dispatcher.forward(request,response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Product> productList = productDAO.selectAllProduct();
        request.setAttribute("productList",productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
        dispatcher.forward(request,response);

    }

    private void showSearchResult(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("SearchName");
        List<Product> product = productDAO.getProductByName(name);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("Error.jsp");
        } else {
            request.setAttribute("productList",product);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/product/search.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.selectProduct(id);
        request.setAttribute("product",product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/edit.jsp");
        dispatcher.forward(request,response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/product/create.jsp");
        dispatcher.forward(request,response);
    }

}
