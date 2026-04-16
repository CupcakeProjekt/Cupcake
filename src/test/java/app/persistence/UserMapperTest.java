package app.persistence;

import app.entities.User;
import app.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest extends BaseTest {


    @Test
    void login() throws DatabaseException {
        // Act
        User user = UserMapper.login("test@test.dk", "1234", connectionPool);

        // Assert - tjek at brugeren findes
        assertNotNull(user);
        assertEquals("test@test.dk", user.getEmail());

    }

    @Test
    void createUser() throws DatabaseException {
        // Arrange
        String uniqueEmail = "ny_" + System.currentTimeMillis() + "@test.dk";

        // Act
        User user = UserMapper.createUser(uniqueEmail, "1234", connectionPool);

        // Assert
        assertNotNull(user);
        assertEquals(uniqueEmail, user.getEmail());
    }

    @Test
    void getAllUsers() throws DatabaseException {
        List<User> users = UserMapper.getAllUsers(connectionPool);

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void getUserByID() throws DatabaseException {
        User user = UserMapper.getUserByID(connectionPool, 1);

        assertNotNull(user);
        assertEquals(1, user.getUserID());
    }
}