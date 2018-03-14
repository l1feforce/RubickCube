import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;


public class CubeTest {
    @Test
    public void generate() {
        Map<Position, Facet> test = new HashMap<>();
        Map<Position, Facet> test1 = new HashMap<>();
        Map<Point, Colour> chips = null;
        test.put(Position.UPPER, new Facet(3, Position.UPPER).generate(Colour.BLUE));
        test.put(Position.LOWER, new Facet(3, Position.LOWER).generate(Colour.GREEN));
        test.put(Position.LEFT, new Facet(3, Position.LEFT).generate(Colour.ORANGE));
        test.put(Position.RIGHT, new Facet(3, Position.RIGHT).generate(Colour.RED));
        test.put(Position.FRONT, new Facet(3, Position.FRONT).generate(Colour.WHITE));
        test.put(Position.BACK, new Facet(3, Position.BACK).generate(Colour.YELLOW));
        assertEquals(new Cube(3, test), new Cube(3, test1).generate());
    }

    @Test
    public void turnLeft() {
    }

    @Test
    public void turnRight() {
    }
}
