package app.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;


public class BaseTest {


    protected static final String USER = "postgres";
    protected static final String PASSWORD = "postgres";
    protected static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=test";
    protected static final String DB = "Cupcake";
    protected static ConnectionPool connectionPool;

    @BeforeAll
    static void initConnectionPool() {
        // Opret connectionPool FØR tests kører
        connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
    }


    @BeforeEach
   public void resetDatabase() throws SQLException {
        try (Connection c = connectionPool.getConnection()) {
            // Deletes all data in tables
            c.createStatement().execute("DELETE FROM test.order_line");
            c.createStatement().execute("DELETE FROM test.orders");
            c.createStatement().execute("DELETE FROM test.users");
            // Restarts sequences
            c.createStatement().execute("ALTER SEQUENCE test.users_user_id_seq RESTART WITH 1");
            c.createStatement().execute("ALTER SEQUENCE test.orders_order_number_seq RESTART WITH 1");
            c.createStatement().execute("ALTER SEQUENCE test.order_line_line_id_seq RESTART WITH 1");
            // Adds testuser
            c.createStatement().execute("INSERT INTO test.users (email, password, role, balance) VALUES ('test@test.dk', '1234', 'USER', 100)");
            c.createStatement().execute("INSERT INTO test.orders (user_id) VALUES (1)");
            c.createStatement().execute("INSERT INTO test.order_line (order_number, top_id, bottom_id, quantity) VALUES (1, 1, 1, 2)");
        }
    }
}
