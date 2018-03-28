import org.junit.Test;

import static org.junit.Assert.*;

public class CubeTRUETest {

    @Test
    public void generate() {
        CubeTRUE cube = new CubeTRUE().generate();
        assertEquals(cube.getCube(), new CubeTRUE().generate().getCube());
    }

    @Test
    public void turnLeft() {
        CubeTRUE cube = new CubeTRUE().generate().turnLeft(Position.BACK, 1);
        assertArrayEquals(cube.turnRight(Position.BACK, 1).getCube().get(Position.BACK), new CubeTRUE().generate().getCube().get(Position.BACK));
    }
}