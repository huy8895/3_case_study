package com.codegym.controller;

import com.codegym.dao.customer.CustomerDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.Customer;
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

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
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
        try {
            switch (action) {
                case "create":
                    System.out.println("start create customer");
                    insertCustomer(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
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
                    showNewForm(request,response);
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
                    listUser(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int cusNumber = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.selectCustomer(cusNumber);
        request.setAttribute("customer",customer);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/customers/delete.jsp");
        requestDispatcher.forward(request, response);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int cusNumber = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("cusName");
        String phoneNumber = request.getParameter("cusPhoneNumber");
        String address = request.getParameter("cusAddress");
        String email = request.getParameter("cusEmail");
        String userName = request.getParameter("userName");
        Customer customer = new Customer(cusNumber,name, phoneNumber, address, email, userName);
        customerDAO.updateUser(customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("cusName");
        String phoneNumber = request.getParameter("cusPhoneNumber");
        String address = request.getParameter("cusAddress");
        String email = request.getParameter("cusEmail");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Customer newCustomer = new Customer(name, phoneNumber, address, email, userName);
        User newUser = new User(userName, password);
        String status;
        if (userDAO.insertUser(newUser) && customerDAO.insertCustomer(newCustomer)){
             status = "tao thanh cong";
        } else {
            status = "tao khong thanh cong";
        }
        request.setAttribute("status",status);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/create.jsp");
        dispatcher.forward(request, response);
    }


    private void showSearchResult(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Customer> customerList = customerDAO.selectAllCustomer();
        for (Customer customer : customerList) {
            System.out.println(customer.getCusNumber());
        }
        request.setAttribute("customerList", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int cusNumber = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.selectCustomer(cusNumber);
        String status;
        if (customerDAO.deleteCustomer(cusNumber)&& userDAO.removeUser(customer)){
            status = "xoa thanh cong";
        } else {
            status =" xoa that bai";
        }
        request.setAttribute("status",status);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/customers/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.selectCustomer(id);
        request.setAttribute("customer",customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/customers/create.jsp");
        dispatcher.forward(request, response);
    }

}
