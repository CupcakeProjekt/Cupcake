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
        String sql = "SELECT * FROM public.bottom";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
                ) {
            while(rs.next()){
                int bottomID = rs.getInt("bottom_id");
                int price = rs.getInt("price");
                String name = rs.getString("bottom_name");
                String desc = rs.getString("bottom_description");
                bottomList.add(new Bottom(bottomID, name, desc, price));
            }
            return bottomList;
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved hentning af bottoms", e.getMessage());
        }
    }
}
