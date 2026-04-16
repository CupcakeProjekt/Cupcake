package app.persistence;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=test";
    private static final String DB = "Cupcake";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    @Test
    void getInstance() {
        // Arrange
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/%s?currentSchema=test";
        String db = "Cupcake";

        // Act
        ConnectionPool pool = ConnectionPool.getTestInstance(user, password, url, db);

        // Assert
        assertNotNull(pool);;

        // den skal også fejler så:
        //assertNotNull(pool);

    }

    @Test
    void getConnection() {
        assertNotNull(connectionPool);

        // tjek for hvis testen fejler
        //assertNull(connectionPool);

    }

}