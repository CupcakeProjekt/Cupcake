package app.persistence;

import app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserMapper {

    public static User login(String username, String password, ConnectionPool connectionPool){
    String sql = "SELECT * FROM bruger WHERE navn=? AND password=?";

    try(
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ){

    }
}}
