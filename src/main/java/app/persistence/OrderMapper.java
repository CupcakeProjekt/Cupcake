package app.persistence;

import app.entities.*;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static int addOrderToDatabase(ConnectionPool cp, int userID) {
        String sql = "INSERT INTO orders (user_id) VALUES (?) RETURNING order_number";
        try(
                Connection c = cp.getConnection();
                PreparedStatement preparedStatement = c.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1, userID);
          ResultSet resultSet =  preparedStatement.executeQuery();
          int orderNumber = resultSet.getInt("order_number");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;
    }

    public static void addOrderlineToOrder(ConnectionPool connectionPool, int bottomID, int topID, int amount, int orderNumber) throws DatabaseException {
        String sql = "INSERT INTO order_line (bottom_id, top_id, quantity, order_number) VALUES (?, ?, ?, ?)";

        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);

        ) {

            ps.setInt(1, bottomID);
            ps.setInt(2, topID);
            ps.setInt(3, amount);
            ps.setInt(4, orderNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Problem med at indsætte ordrelinjerne", e.getMessage());
        }
    }

    public static List<Order> getAllOrders(ConnectionPool cp) throws DatabaseException {
        String sql = "SELECT * FROM orders";
        List<Order> orderList = new ArrayList<>();
        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderNumber = rs.getInt("order_number");
                int userID = rs.getInt("user_id");
                User user = UserMapper.getUserByID(cp, userID);
                orderList.add(new Order(orderNumber, user));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Problem med at hente orders", e.getMessage());
        }

        List<Orderline> orderlineList = getAllOrderlines(cp);

        for (Order order : orderList) {
            List<Orderline> ol = orderlineList.stream().
                    filter(oll -> oll.getOrderNumber() == order.getOrderNumber()).toList();
            order.setOrderlineList(ol);
        }
        return orderList;
    }

    public static List<Orderline> getAllOrderlines(ConnectionPool cp) throws DatabaseException {
        String sql = "SELECT * FROM order_line";
        List<Orderline> orderlineList = new ArrayList<>();
        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int lineID = rs.getInt("line_id");
                int orderNumber = rs.getInt("order_number");
                int topID = rs.getInt("top_id");
                int bottomID = rs.getInt("bottom_id");
                int quantity = rs.getInt("quantity");

                Bottom bottom = BottomMapper.getBottomByID(cp, bottomID);
                Topping topping = TopMapper.getToppingByID(cp, topID);
                Cupcake cupcake = new Cupcake(bottom, topping);
                int cost = cupcake.getCost() * quantity;
                orderlineList.add(new Orderline(cupcake, lineID, orderNumber, cost, quantity));
            }
            return orderlineList;
        } catch (SQLException e) {
            throw new DatabaseException("Problem med at hente orderlines", e.getMessage());
        }
    }
}
