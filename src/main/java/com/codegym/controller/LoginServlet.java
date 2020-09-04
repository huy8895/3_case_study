package com.codegym.controller;

import com.codegym.dao.customer.CustomerDAO;
<<<<<<< HEAD
import com.codegym.dao.user.UserDAO;
=======
import com.codegym.dao.product.ProductDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.Customer;
import com.codegym.model.Product;
>>>>>>> huy
import com.codegym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> huy

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private CustomerDAO customerDAO;
<<<<<<< HEAD
=======
    private ProductDAO productDAO;
>>>>>>> huy

    public void init() {
        userDAO = new UserDAO();
        customerDAO = new CustomerDAO();
<<<<<<< HEAD
=======
        productDAO = new ProductDAO();
>>>>>>> huy
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
<<<<<<< HEAD
                    changePass(request,response);
=======
                    changePass(request, response);
>>>>>>> huy
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
<<<<<<< HEAD
                    showChangePasswordForm(request,response);
                    break;
                default:
                    showLogin(request,response);
=======
                    showChangePasswordForm(request, response);
                    break;
                default:
                    showLogin(request, response);
>>>>>>> huy
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
<<<<<<< HEAD
=======

>>>>>>> huy
    private void changePass(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newpassword");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);

<<<<<<< HEAD
        if (userDAO.changePassword(user,newPassword)){
=======
        if (userDAO.changePassword(user, newPassword)) {
>>>>>>> huy
            System.out.println("doi mat khau thanh cong");
        } else {
            System.out.println("doi mat khau khong thanh cong");
        }
    }


<<<<<<< HEAD

=======
>>>>>>> huy
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

<<<<<<< HEAD
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
=======
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
>>>>>>> huy
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(userName, password);
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);
<<<<<<< HEAD

        if (userDAO.checkUser(user)){
            System.out.println("dang nhap thanh cong");
            if (userDAO.checkAdmin(user)){
                System.out.println(userName + "la admin");
            } else {
                System.out.println(userName + " khong phai la admin");
            }
        } else {
            System.out.println("sai ten dang nhap hoac mat khau");
        }

=======
        String message = "";
        RequestDispatcher dispatcher = null;
        List<Product> productList = productDAO.selectAllProduct();
        Customer customer = customerDAO.selectCustomer(userName);

        if (userDAO.checkUser(user)) {
            request.setAttribute("productList", productList);
            request.setAttribute("customer", customer);

            dispatcher = request.getRequestDispatcher("WEB-INF/views/product/list.jsp");
            System.out.println("dang nhap thanh cong");
            message = "dang nhap thanh cong";
            if (userDAO.checkAdmin(user)) {
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
>>>>>>> huy

    }


}
