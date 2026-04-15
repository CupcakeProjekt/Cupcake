package app.persistence;

import app.entities.Bottom;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BottomMapper {
    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        List<Bottom> bottomList = new ArrayList<>();
        String sql = "SELECT * FROM bottom";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
                ) {
            while(rs.next()){
                int bottomID = rs.getInt("bottom_id");
                int price = rs.getInt("price");
                String name = rs.getString("bottom_name");
                bottomList.add(new Bottom(bottomID, name, price));
            }
            return bottomList;
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved hentning af bottoms", e.getMessage());
        }
    }
    public static Bottom getBottomByID(ConnectionPool connectionPool, int ID) throws DatabaseException {
        String sql = "SELECT * FROM bottom WHERE bottom_id=?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("bottom_name");
                int price = rs.getInt("price");
                return new Bottom(ID, name, price);
            } else {
                throw new DatabaseException("Ingen bund fundet med ID: " + ID);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke finde bund baseret på givne ID", e.getMessage());
        }
    }
}
