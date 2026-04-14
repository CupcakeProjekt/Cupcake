package app.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static app.persistence.BaseTest.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest extends BaseTest{

    @BeforeAll
    static void setup() {
        connectionPool = ConnectionPool.getTestInstance(USER, PASSWORD, URL, DB);
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
    void addOrderlineToOrder() {
        OrderMapper.addOrderlineToOrder();
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getAllOrderlines() {
    }

    @Test
    void removeOrderWithID() {
    }
}