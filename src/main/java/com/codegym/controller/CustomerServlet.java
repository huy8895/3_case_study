package com.codegym.controller;

import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet",urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private UserDAO userDAO;
    private CustomerDAO customerDAO;

    public void init() {
        userDAO = new UserDAO();
        customerDAO = new CustomerDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertUser(request, response);
                break;
            case "edit":
                updateUser(request, response);
                break;
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "Search":
                showSearchResult(request,response);
                break;
            default:
                listUser(request, response);
                break;
        }


    }

    private void showSearchResult(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<User> listUser = userDAO.selectAllUsers();
//        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("chay vao product/create.js");
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            dispatcher.forward(request, response);
    }
}
