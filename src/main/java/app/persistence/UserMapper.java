package app.persistence;

import app.entities.Role;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("user_id");
                String email = rs.getString("email");
                String roleString = rs.getString("role");
                Role role = Role.valueOf(roleString.toUpperCase());
                int balance = rs.getInt("balance");
                userList.add(new User(ID, email, role, balance));
            }
            return userList;
        } catch (SQLException e) {
            throw new DatabaseException("msg", "fejl ved hetning af brugere");
        }
    }

    public static User getUserByID(ConnectionPool connectionPool, int userID) throws DatabaseException {
        String sql = "SELECT * FROM users WHERE user_id=?";

        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String roleString = rs.getString("role");
                Role role = Role.valueOf(roleString.toUpperCase());
                int balance = rs.getInt("balance");
                return new User(userID, email, role, balance);
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseException("Problem med at finde user med userID", e.getMessage());
        }
    }
}
