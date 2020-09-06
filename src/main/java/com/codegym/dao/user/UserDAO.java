package com.codegym.dao.user;

import com.codegym.dao.database.Jdbc;
import com.codegym.model.User;

import java.sql.*;


public class UserDAO implements IUserDAO {

    private static final String INSERT_USERS_SQL = "INSERT INTO User (userName, password) VALUES " +
            " (?, ?);";

    private static final String GET_USER_BY_USERNAME = "SELECT * FROM User where userName = ?;";

    private static final String SET_NEW_PASSWORD = "UPDATE User set password = ? where userName = ?;";

    private static final String GET_ROLE = "SELECT (roleID) from User  where userName = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Jdbc.jdbcURL, Jdbc.jdbcUsername, Jdbc.jdbcPassword);
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
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        try {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkUser(User user) throws SQLException {
        boolean checked = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME);
        preparedStatement.setString(1, user.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            if (userName.equalsIgnoreCase(user.getUserName()) && password.equals(user.getPassword())) {
                checked = true;
            } else {
                checked = false;
            }
        } else {
            checked = false;
        }
        return checked;
    }

    @Override
    public boolean changePassword(User user, String newPassword) throws SQLException {
        boolean changed = false;
        Connection connection = getConnection();
        if (checkUser(user)) {
            System.out.println("changing pass ");
            System.out.println("user = " + user.getUserName());
            System.out.println("newPassword = " + newPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(SET_NEW_PASSWORD);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.executeUpdate();
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean checkAdmin(User user) throws SQLException {
        boolean isAdmin = false;
        Connection connection = getConnection();
        if (checkUser(user)) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE);
            preparedStatement.setString(1,user.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isAdmin = resultSet.getBoolean(1);
            }
        }
        return isAdmin;
    }

}
