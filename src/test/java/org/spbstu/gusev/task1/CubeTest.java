package org.spbstu.gusev.task1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CubeTest {

    private Cube testingCube = new Cube();

    @Before
    public void initialize() {
        Map<Position, int[][]> testingMap = new HashMap<>();
        int[][] arrBack = {{0, 1, 0}, {3, 5, 1}, {3, 0, 1}};
        int[][] arrRight = {{3, 1, 1}, {3, 3, 0}, {0, 0, 0}};
        int[][] arrLower = {{3, 2, 3}, {3, 3, 3}, {2, 5, 2}};
        int[][] arrLeft = {{3, 3, 1}, {5, 1, 3}, {5, 5, 5}};
        int[][] arrUpper = {{3, 3, 2}, {3, 2, 2}, {3, 1, 2}};
        int[][] arrFront = {{5, 2, 5}, {2, 0, 5}, {3, 0, 1}};
        testingMap.put(Position.BACK, arrBack);
        testingMap.put(Position.LEFT, arrLeft);
        testingMap.put(Position.FRONT, arrFront);
        testingMap.put(Position.RIGHT, arrRight);
        testingMap.put(Position.UPPER, arrUpper);
        testingMap.put(Position.LOWER, arrLower);
        testingCube.setCube(testingMap);


    }

    @Test
    public void turnLeft() {
        Map<Position, int[][]> testingMap_2 = new HashMap<>();
        int[][] arrBack_2 = {{0, 1, 1}, {1, 5, 0}, {0, 3, 3}};
        int[][] arrRight_2 = {{3, 3, 2}, {3, 3, 0}, {0, 0, 0}};
        int[][] arrLower_2 = {{3, 1, 1}, {3, 3, 3}, {2, 5, 2}};
        int[][] arrLeft_2 = {{3, 2, 3}, {5, 1, 3}, {5, 5, 5}};
        int[][] arrUpper_2 = {{3, 3, 1}, {3, 2, 2}, {3, 1, 2}};
        int[][] arrFront_2 = {{5, 2, 5}, {2, 0, 5}, {3, 0, 1}};
        testingMap_2.put(Position.BACK, arrBack_2);
        testingMap_2.put(Position.LEFT, arrLeft_2);
        testingMap_2.put(Position.FRONT, arrFront_2);
        testingMap_2.put(Position.RIGHT, arrRight_2);
        testingMap_2.put(Position.UPPER, arrUpper_2);
        testingMap_2.put(Position.LOWER, arrLower_2);
        Cube testingCube_2 = new Cube();
        testingCube_2.setCube(testingMap_2);

        testingCube.turnLeft(Position.BACK, 1);
        List<Position> allPositions = Arrays.asList(Position.values());
        for (Position pos : allPositions
                ) {
            assertArrayEquals(testingCube_2.getFacet(pos),
                    testingCube.getFacet(pos));
        }
    }

    @Test
    public void rotateCube() {
        Map<Position, int[][]> testingMap_2 = new HashMap<>();
        int[][] arrBack_2 = {{0, 1, 1}, {1, 5, 0}, {0, 3, 3}};
        int[][] arrRight_2 = {{3, 3, 2}, {3, 2, 2}, {3, 1, 2}};
        int[][] arrLower_2 = {{3, 1, 1}, {3, 3, 0}, {0, 0, 0}};
        int[][] arrLeft_2 = {{3, 2, 3}, {3, 3, 3}, {2, 5, 2}};
        int[][] arrUpper_2 = {{3, 3, 1}, {5, 1, 3}, {5, 5, 5}};
        int[][] arrFront_2 = {{3, 2, 5}, {0, 0, 2}, {1, 5, 5}};
        testingMap_2.put(Position.BACK, arrBack_2);
        testingMap_2.put(Position.LEFT, arrLeft_2);
        testingMap_2.put(Position.FRONT, arrFront_2);
        testingMap_2.put(Position.RIGHT, arrRight_2);
        testingMap_2.put(Position.UPPER, arrUpper_2);
        testingMap_2.put(Position.LOWER, arrLower_2);
        Cube testingCube_2 = new Cube();
        testingCube_2.setCube(testingMap_2);

        testingCube.turnRight(Position.FRONT, 3);
        List<Position> allPositions = Arrays.asList(Position.values());
        for (Position pos : allPositions
                ) {
            assertArrayEquals(testingCube_2.getFacet(pos),
                    testingCube.getFacet(pos));
        }
    }

    @Test
    public void getFacet() {
        int[][] arr = {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};
        assertArrayEquals(arr, Cube.generate().getFacet(Position.BACK));
    }

    @Test
    public void turnRight() {
        Map<Position, int[][]> testingMap_2 = new HashMap<>();
        int[][] arrBack_2 = {{3, 3, 0}, {0, 5, 1}, {1, 1, 0}};
        int[][] arrRight_2 = {{3, 2, 3}, {3, 3, 0}, {0, 0, 0}};
        int[][] arrLower_2 = {{3, 3, 1}, {3, 3, 3}, {2, 5, 2}};
        int[][] arrLeft_2 = {{3, 3, 2}, {5, 1, 3}, {5, 5, 5}};
        int[][] arrUpper_2 = {{3, 1, 1}, {3, 2, 2}, {3, 1, 2}};
        int[][] arrFront_2 = {{5, 2, 5}, {2, 0, 5}, {3, 0, 1}};
        testingMap_2.put(Position.BACK, arrBack_2);
        testingMap_2.put(Position.LEFT, arrLeft_2);
        testingMap_2.put(Position.FRONT, arrFront_2);
        testingMap_2.put(Position.RIGHT, arrRight_2);
        testingMap_2.put(Position.UPPER, arrUpper_2);
        testingMap_2.put(Position.LOWER, arrLower_2);
        Cube testingCube_2 = new Cube();
        testingCube_2.setCube(testingMap_2);

        testingCube.turnRight(Position.BACK, 1);
        List<Position> allPositions = Arrays.asList(Position.values());
        for (Position pos : allPositions
                ) {
            assertArrayEquals(testingCube_2.getFacet(pos),
                    testingCube.getFacet(pos));
        }
    }
}