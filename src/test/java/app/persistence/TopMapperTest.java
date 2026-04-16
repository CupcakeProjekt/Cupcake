package app.persistence;

import app.entities.Topping;
import app.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TopMapperTest extends BaseTest {

    @Test
    void getAllTops() throws DatabaseException {
        // Act - hent alle toppings fra databasen
        List<Topping> tops = TopMapper.getAllTops(connectionPool);

        // Assert - tjek at der er top
        assertNotNull(tops);
        assertFalse(tops.isEmpty());
    }

    @Test
    void getToppingByID() throws DatabaseException {
        // Act & Assert - hent topping med ID = 1
        assertNotNull(TopMapper.getToppingByID(connectionPool, 1));
    }
}