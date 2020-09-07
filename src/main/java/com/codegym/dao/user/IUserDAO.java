package com.codegym.dao.user;

import com.codegym.model.Customer;
import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserDAO {
    public boolean insertUser(User user) throws SQLException;

    public boolean checkUser(User user) throws SQLException;

    public boolean changePassword(User user, String newPassword) throws SQLException;

    public boolean checkAdmin(User user) throws SQLException;

    public boolean removeUser(Customer customero) throws SQLException;

    public User selectUser(Customer customer) throws SQLException;
}
