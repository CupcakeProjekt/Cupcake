package app.persistence;

import app.entities.Bottom;
import app.entities.Orderline;
import app.entities.Topping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    public static void addOrderToDatabase() {

    }

    public static void addOrderlineToOrder(ConnectionPool connectionPool, int bottomID, int topID, int amount) {
        String sql = "INSERT INTO order_line (bottom_id, top_id, quantity) VALUES (?, ?, ?)";

        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);

        ) {

            ps.setInt(1, bottomID);
            ps.setInt(2, topID);
            ps.setInt(3, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
