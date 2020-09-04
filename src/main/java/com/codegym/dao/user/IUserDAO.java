package com.codegym.dao.user;

import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

    boolean checkAdmin(User user) throws SQLException;
    public boolean changePassword(User user, String newPassword) throws SQLException;
    public boolean checkUser(User user) throws SQLException;
}
