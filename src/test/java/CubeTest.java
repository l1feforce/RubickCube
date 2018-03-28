import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class CubeTest {
    @Test
    public void generate() {
        assertEquals(new Cube(3).generate(), new Cube(3).generate());
    }


    /**
     * turning left
     * 1 2 3       3 6 9
     * 4 5 6   ->  2 5 8
     * 7 8 9       1 4 7
     */
    @Test
    public void turnLeft() {
        Cube cube = new Cube(3).generate().turnLeft(Position.LEFT, 1).turnRight(Position.RIGHT, 1);

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(3,1)),
                cube.turnLeft(Position.BACK, 1).getFacet().get(Position.BACK).getChips());

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(1, 1)),
                cube.turnLeft(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(1, 3)));

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(1, 1)),
                cube.turnLeft(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(3, 1)));

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(2, 1)),
                cube.turnLeft(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(3, 2)));

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(3, 1)),
                cube.turnLeft(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(3, 3)));


    }


    /**
     * turning right
     * 1 2 3      7 4 1
     * 4 5 6  ->  8 5 2
     * 7 8 9      9 6 3
     */
    @Test
    public void turnRight() {
        Cube cube = new Cube(3).generate().turnLeft(Position.BACK, 1);

        assertEquals(new Cube(3).generate(), new Cube(3).generate().turnRight(Position.FRONT, 1).turnLeft(Position.FRONT, 1));

        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(1, 1)),
                cube.turnRight(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(1, 3)));
        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(2, 1)),
                cube.turnRight(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(1, 2)));
        assertEquals(cube.getFacet().get(Position.BACK).getChips().get(new Point(3, 1)),
                cube.turnRight(Position.BACK, 1).getFacet().get(Position.BACK).getChips().get(new Point(1, 1)));
    }


    @Test
    public void randomize() {
        assertEquals(new Cube(3).generate().getFacet(), new Cube(3).generate().randomize().getFacet());
    }

    @Test
    public void getCurrentFacet() {
    }
}
