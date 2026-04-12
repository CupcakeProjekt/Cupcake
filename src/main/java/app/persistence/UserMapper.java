package app.persistence;

import app.entities.Role;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("user_id");
                String roleString = resultSet.getString("role");
                Role role = Role.valueOf(roleString.toUpperCase());
                int balance = resultSet.getInt("balance");
                return new User(ID, email, password, role, balance);
            } else {
                throw new DatabaseException("Fejl i login, prøv igen.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i DB", e.getMessage());
        }
    }

    public static User createUser(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved oprettelse", e.getMessage());
        }
        return UserMapper.login(email, password, connectionPool);
    }
}
