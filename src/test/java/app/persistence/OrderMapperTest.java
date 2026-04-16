package app.persistence;

import app.entities.Order;
import app.entities.Orderline;
import app.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static app.persistence.BaseTest.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest extends BaseTest{

    @BeforeAll
    static void setup() {
        connectionPool = ConnectionPool.getTestInstance
                (USER, PASSWORD, URL, DB);
    }

    @BeforeEach
    void reset() throws SQLException {

        resetDatabase();
    }

    @Test
    void addOrderToDatabase() {
        System.out.println("Test starter");
        // Act
        int orderNumber = OrderMapper.addOrderToDatabase(connectionPool, 1);
        // Assert
        assertEquals(2, orderNumber);
    }

    @Test
    void addOrderlineToOrder() throws DatabaseException {

    }

    @Test
    void getAllOrders() throws DatabaseException {
        // Act - hent alle ordrer
        List<Order> orders = OrderMapper.getAllOrders(connectionPool);

        // Assert - tjek at der er ordrer
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void getAllOrderlines() throws DatabaseException {
        // Act
        List<Orderline> orderlines = OrderMapper.getAllOrderlines(connectionPool);

        // Assert
        assertNotNull(orderlines);
        assertFalse(orderlines.isEmpty());
    }


    @Test
    void removeOrderWithID() {

    }
}