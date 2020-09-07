package com.codegym.controller;

import com.codegym.dao.DAOManger;
import com.codegym.model.Customer;
import com.codegym.model.Product;
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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
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
                case "login":
                    doLogin(request, response);
                    break;

                case "changepassword":
                    changePass(request, response);
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
                case "changepassword":
                    System.out.println("changing pass");
                    showChangePasswordForm(request, response);
                    break;
                default:
                    showLogin(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void changePass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newpassword");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);
        System.out.println("newPassword = " + newPassword);
        String status = "";

        if (daoManger.userDAO.changePassword(user, newPassword)) {
            System.out.println("doi mat khau thanh cong");
            status = "doi mat khau thanh cong";
        } else {
            System.out.println("doi mat khau khong thanh cong");
            status = "doi mat khau khong thanh cong";
        }
        request.setAttribute("status",status);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/changepassword.jsp");
        dispatcher.forward(request, response);
    }


    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
        dispatcher.forward(request, response);

    }

    private void showChangePasswordForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/changepassword.jsp");
        dispatcher.forward(request, response);

    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);
        String message = "";
        RequestDispatcher dispatcher = null;
        List<Product> productList = daoManger.productDAO.selectAllProduct();
        Customer customer = daoManger.customerDAO.selectCustomer(userName);

        if (daoManger.userDAO.checkUser(user)) {
            request.setAttribute("productList", productList);
            request.setAttribute("customer", customer);

            dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
            System.out.println("dang nhap thanh cong");
            message = "dang nhap thanh cong";
            if (daoManger.userDAO.checkAdmin(user)) {
                System.out.println(userName + "la admin");
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                System.out.println(userName + " khong phai la admin");

            }
        } else {
            dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
            System.out.println("sai ten dang nhap hoac mat khau");
            message = "sai ten dang nhap hoac mat khau";
        }
        request.setAttribute("message", message);
        dispatcher.forward(request, response);

    }


}
