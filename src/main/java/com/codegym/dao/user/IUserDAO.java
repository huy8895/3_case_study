package com.codegym.dao.user;

import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

}
