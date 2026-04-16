package app.persistence;

import app.entities.Bottom;
import app.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BottomMapperTest extends BaseTest {


    @BeforeAll
    // BaseTest klarer allerede reset af database. så behøve man ikke denne metode.
    static void setup() {

    }

    @Test
    void getAllBottoms() throws DatabaseException {
        List<Bottom> bottoms = BottomMapper.getAllBottoms(connectionPool);
        assertNotNull(bottoms);
    }


        @Test
        void getBottomByID() throws DatabaseException {
            // Act & Assert
            assertNotNull(BottomMapper.getBottomByID(connectionPool, 1));
        }

}