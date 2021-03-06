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

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
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
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "search":
                    search(request, response);
                    break;
                default:
                    listProducts(request, response);
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
                case "search":
                    System.out.println("search click button");
                    search(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        checkCustomer(request);
        String productName = request.getParameter("SearchBox_productName");
        String minPrice = request.getParameter("SearchBox_minPrice");
        String maxPrice = request.getParameter("SearchBox_maxPrice");
        String productBrand = request.getParameter("SearchBox_productBrand");
        String productLine = request.getParameter("SearchBox_productLine");

        if (productBrand.trim().equalsIgnoreCase("Brand")){
            productBrand = "";
        }

        if (productLine.trim().equalsIgnoreCase("Gender")){
            productLine = "";
        }

        System.out.println("SearchBox_productName = " + productName);
        System.out.println("SearchBox_minPrice = " + minPrice);
        System.out.println("SearchBox_maxPrice = " + maxPrice);
        System.out.println("SearchBox_productBrand = " + productBrand);
        System.out.println("SearchBox_productLine = " + productLine);

        List<Product> productList = daoManger.productDAO.getProductsBySearch(productName,minPrice,maxPrice,productBrand,productLine);
        request.setAttribute("productList", productList);
        int results_count = productList.size();
        request.setAttribute("results_count", results_count);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void checkCustomer(HttpServletRequest request) throws SQLException {
        Customer customer;
        if (request.getParameter("cusNumber") != null && !request.getParameter("cusNumber").equals("")){
            int cusNumber = Integer.parseInt(request.getParameter("cusNumber"));
            customer = daoManger.customerDAO.selectCustomer(cusNumber);
            request.setAttribute("customer", customer);
        } else {
            System.out.println("customer =  null" );
            customer = null;
        }
        request.setAttribute("customer", customer);
    }

    private void showSearchResult(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name= request.getParameter("productDesc");
        String brand= request.getParameter("productBrand");
        double price= Double.parseDouble(request.getParameter("price"));
        String image= request.getParameter("imgLink");
        String line= request.getParameter("line");
        daoManger.productDAO.insertProduct(new Product(name,brand,price,image,line));
        showEditForm(request,response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id= Integer.parseInt(request.getParameter("id"));
        String name= request.getParameter("name");
        String brand= request.getParameter("brand");
        double price= Double.parseDouble(request.getParameter("price"));
        String image= request.getParameter("image");
        String line= request.getParameter("line");
        daoManger.productDAO.updateProduct(new Product(id,name,brand,price,image,line));
        showEditForm(request,response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        daoManger.productDAO.deleteProduct(id);
        showEditForm(request,response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList=daoManger.productDAO.selectAllProduct();
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/views/login/adminEdit.jsp");
        request.setAttribute("productList",productList);
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/views/login/adminAdd.jsp");
        dispatcher.forward(request,response);
    }

}
