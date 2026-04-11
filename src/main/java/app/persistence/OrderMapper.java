package app.persistence;

import app.entities.Orderline;

public class OrderMapper {

    public static void addOrderToDatabase(){

    }

    public static void addOrderlineToOrder(ConnectionPool connectionPool, Orderline orderline) {
    String sql = "INSERT INTO order_line";
    }
}
