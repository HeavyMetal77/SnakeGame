package snakeGame;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoordTest {
    public static Coord coord;
    @BeforeClass
    public static void createNewCoord(){
        coord = new Coord(600, 600);
    }

    @Test
    public void CoordShouldReturnNewCoord(){
        Assert.assertEquals(coord, new Coord(600, 600));
    }
}
