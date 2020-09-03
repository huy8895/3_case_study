package com.codegym.dao.user;

import com.codegym.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAO implements IUserDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/DBmodule3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO User (userName, password) VALUES " +
            " (?, ?);";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        try {
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
