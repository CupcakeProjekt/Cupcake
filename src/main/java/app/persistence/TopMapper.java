package app.persistence;

import app.entities.Topping;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopMapper {

    public static List<Topping> getAllTops(ConnectionPool connectionPool) throws DatabaseException {
        List<Topping> toppingList = new ArrayList<>();
        String sql = "SELECT * FROM public.top";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int topID = rs.getInt("int_id");
                String name = rs.getString("top_name");
                String desc = rs.getString("top_description");
                int price = rs.getInt("price");
                toppingList.add(new Topping(topID, name, desc, price));

            }
            return toppingList;

        } catch (SQLException e) {
            throw new DatabaseException("Problem ved hentning at toppings", e.getMessage());
        }
    }
}
