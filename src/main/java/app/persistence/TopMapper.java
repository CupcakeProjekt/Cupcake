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
        String sql = "SELECT * FROM top";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int topID = rs.getInt("top_id");
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

    public static Topping getToppingByID(ConnectionPool connectionPool, int ID) throws DatabaseException {
        String sql = "SELECT * FROM top WHERE top_id=?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("top_name");
                String desc = rs.getString("top_description");
                int price = rs.getInt("price");
               return new Topping(ID, name, desc, price);
            } else {
                throw new DatabaseException("Ingen topping fundet med ID: " + ID);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke finde topping baseret på givne ID", e.getMessage());
        }
    }
}
