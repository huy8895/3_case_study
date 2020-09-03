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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
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
        try {
            switch (action) {
                case "login":
                    doLogin(request, response);
                    break;
                case "changepassword":
                    System.out.println("changing pass");
                    changePass(request,response);
                    break;

            }
        } catch (SQLException e) {
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
                case "changepassword":
                    showChangePasswordForm(request,response);
                    break;
                default:
                    showLogin(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void changePass(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newpassword");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);

        if (userDAO.changePassword(user,newPassword)){
            System.out.println("doi mat khau thanh cong");
        } else {
            System.out.println("doi mat khau khong thanh cong");
        }
    }



    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
        dispatcher.forward(request, response);

    }

    private void showChangePasswordForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/changepassword.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/create.jsp");
        dispatcher.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);

        if (userDAO.checkUser(user)){
            System.out.println("dang nhap thanh cong");
        } else {
            System.out.println("sai ten dang nhap hoac mat khau");
        }

    }


}
