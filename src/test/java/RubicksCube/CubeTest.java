package RubicksCube;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CubeTest {

    @Test
    public void generate() {
        Cube cube = new Cube().generate();
        List<Position> allPositions = Arrays.asList(Position.RIGHT, Position.LOWER,
                Position.LEFT, Position.UPPER, Position.BACK, Position.FRONT);
        for (Position pos: allPositions
                ) {
            assertArrayEquals(cube.getCube().get(pos), new Cube().generate().getCube().get(pos));
        }
    }

    @Test
    public void turnLeft() {
        Cube cube = new Cube().generate().randomize();
        assertArrayEquals(cube.turnRight(Position.BACK, 1).turnLeft(Position.BACK, 1).getCube().get(Position.BACK),
                cube.getCube().get(Position.BACK));
        assertArrayEquals(cube.turnRight(Position.UPPER, 1).turnLeft(Position.UPPER, 1).getCube().get(Position.UPPER),
                cube.getCube().get(Position.UPPER));
        assertArrayEquals(cube.turnRight(Position.FRONT, 1).turnLeft(Position.FRONT, 1).getCube().get(Position.FRONT),
                cube.getCube().get(Position.FRONT));
        assertArrayEquals(cube.turnRight(Position.LOWER, 1).turnLeft(Position.LOWER, 1).getCube().get(Position.LOWER),
                cube.getCube().get(Position.LOWER));
        assertArrayEquals(cube.turnRight(Position.LEFT, 1).turnLeft(Position.LEFT, 1).getCube().get(Position.LEFT),
                cube.getCube().get(Position.LEFT));
        assertArrayEquals(cube.turnRight(Position.RIGHT, 1).turnLeft(Position.RIGHT, 1).getCube().get(Position.RIGHT),
                cube.getCube().get(Position.RIGHT));
    }
}