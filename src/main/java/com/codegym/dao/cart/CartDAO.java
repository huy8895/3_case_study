package com.codegym.dao.cart;

import com.codegym.dao.database.Jdbc;
import com.codegym.model.Cart;
import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements ICartDAO {
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Cart where productCode = ?";
    private static final String UPDATE_ADD1_PRODUCT_SQL = "update Cart set quantity = ? where productCode = ? and cusNumber = ?; ";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Cart (cusNumber,productCode) values (?,?) ;";
    private static final String SELECT_CART_BY_CUS_NUMBER = "SELECT * FROM Cart where cusNumber = ?;";
    private static final String CLEAR_CART_BY_CUSNUMBER = "delete from Cart where cusNumber = ?;";
    private static final String DELETE_CART_BY_PRODUCT_CODE = "delete from Cart where productCode = ? and cusNumber = ?;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Jdbc.jdbcURL, Jdbc.jdbcUsername, Jdbc.jdbcPassword);
        } catch (SQLException e) {
            System.out.println("khong ket noi dc");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            System.out.println("khong ket noi dc 2");
            System.out.println(e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertCart(Customer customer, Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement updateStm = connection.prepareStatement(UPDATE_ADD1_PRODUCT_SQL);
        PreparedStatement insertStm = connection.prepareStatement(INSERT_PRODUCT_SQL);
        int quantity = getQuantity(product);
        try {
            if (quantity > 0) {
                updateStm.setInt(1, (quantity + 1));
                updateStm.setInt(2, product.getProductCode());
                updateStm.setInt(3, customer.getCusNumber());
                updateStm.executeUpdate();
            } else {
                insertStm.setInt(1, customer.getCusNumber());
                insertStm.setInt(2, product.getProductCode());
                insertStm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Integer getQuantity(Product product) throws SQLException {
        int quantity = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
        preparedStatement.setInt(1, product.getProductCode());
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            quantity = set.getInt("quantity");
        }
        return quantity;
    }

    @Override
    public List<Cart> selectAllCart(Customer customer) throws SQLException {
        List<Cart> carts = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_BY_CUS_NUMBER);
        preparedStatement.setInt(1, customer.getCusNumber());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int cusNumber = resultSet.getInt("cusNumber");
            int productCode = resultSet.getInt("productCode");
            int quantity = resultSet.getInt("quantity");
            carts.add(new Cart(productCode, cusNumber, quantity));
        }

        return carts;
    }

    @Override
    public void clearCart(Customer customer) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_CART_BY_CUSNUMBER);
        preparedStatement.setInt(1, customer.getCusNumber());
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean deleteCart(Customer customer,Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_BY_PRODUCT_CODE);
        preparedStatement.setInt(1,product.getProductCode());
        preparedStatement.setInt(2, customer.getCusNumber());
        return preparedStatement.executeUpdate() > 0;
    }

}
