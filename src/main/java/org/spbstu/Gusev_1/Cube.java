package org.spbstu.Gusev_1;

import java.util.*;

import static org.spbstu.Gusev_1.Position.*;

public class Cube {

    private Map<Position, int[][]> cube = new HashMap<>();

    public final int[][] getFacet(Position pos) {
        return getCube().get(pos);
    }

    public final Map<Position, int[][]> getCube() {
        if (cube == null) throw new NullPointerException();
        return cube;
    }

    public final void setCube(Map<Position, int[][]> cube) {
        if (cube == null) throw new NullPointerException();
        this.cube = cube;
    }

    /**
     * @param k colour of facet
     * @return filled facet(array)
     */
    private static int[][] fill(int k) {
        int[][] arr = new int[3][3];
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                arr[x][y] = k;
            }
        }
        return arr;
    }


    /**
     * @return new Cube with filled facets
     */
    public final static Cube generate() {
        Cube cubeNull = new Cube();
        List<Position> allPositions = Arrays.asList(values());
        for (Position pos : allPositions
                ) {
            cubeNull.cube.put(pos, fill(allPositions.indexOf(pos)));
        }
        return cubeNull;
    }

    /**
     * turns Cube random number of times
     *
     * @return Cube with random state
     */
    public final static Cube randomize() {
        Cube cubeNew = Cube.generate();
        List<Position> set = Arrays.asList(values());
        for (Position pos : set) {
            for (int i = 0; i < 1 + (int) (Math.random() * 3); i++) {
                cubeNew.turnLeft(pos, 1 + (int) (Math.random() * 2));
            }
        }
        return cubeNew;
    }

    /**
     * @param input array which need to copy
     * @return copy of input array
     */
    private static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    /**
     * function rotates cells on other facets
     *
     * @param set   list with Positions that need to be reassigned
     * @param depth how many layers of the cube need to turn
     */
    private void rotateLines(List<Position> set, int depth) {
        int[][] copyOfFacet = Cube.deepCopyIntMatrix(cube.get(set.get(0)));
        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= depth - 1; y++) {
                for (int x = 0; x <= 2; x++) {
                    if (i == 3) cube.get(set.get(i))[y][x] = copyOfFacet[y][x];
                    else cube.get(set.get(i))[y][x] = cube.get(set.get(i + 1))[y][x];
                }
            }
        }
    }

    /**
     * function rotates facet to left with Position pos
     *
     * @param pos Position of facet that need to turn
     */
    private void rotateFacetLeft(Position pos) {
        for (int k = 0; k < 1; k++) {
            for (int j = k; j < 2 - k; j++) {
                int tmp = this.cube.get(pos)[k][j];
                this.cube.get(pos)[k][j] = this.cube.get(pos)[j][2 - k];
                this.cube.get(pos)[j][2 - k] = this.cube.get(pos)[2 - k][2 - j];
                this.cube.get(pos)[2 - k][2 - j] = this.cube.get(pos)[2 - j][k];
                this.cube.get(pos)[2 - j][k] = tmp;
            }
        }
    }

    /**
     * function rotates facet to right with Position pos
     *
     * @param pos Position of facet that need to turn
     */
    private void rotateFacetRight(Position pos) {
        for (int i = 1; i <= 3; i++) {
            this.rotateFacetLeft(pos);
        }
    }

    /**
     * method turns the facet to left by using functions rotateFacetLeft and rotateLines
     * when the depth is 3, you'll turn all cube
     *
     * @param pos   Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnLeft(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(UPPER, LEFT,
                        LOWER, RIGHT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setBack, depth);
                if (depth == 3) this.rotateFacetLeft(FRONT);
                break;
            case LEFT:
                List<Position> setLeft = Arrays.asList(FRONT, LOWER,
                        BACK, UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLeft, depth);
                if (depth == 3) this.rotateFacetLeft(RIGHT);
                break;
            case LOWER:
                List<Position> setLower = Arrays.asList(RIGHT, BACK,
                        LEFT, FRONT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLower, depth);
                if (depth == 3) this.rotateFacetLeft(UPPER);
                break;
            case RIGHT:
                List<Position> setRight = Arrays.asList(BACK, LOWER,
                        FRONT, UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setRight, depth);
                if (depth == 3) this.rotateFacetLeft(LEFT);
                break;
            case UPPER:
                List<Position> setUpper = Arrays.asList(RIGHT, FRONT,
                        LEFT, BACK);
                this.rotateFacetLeft(pos);
                this.rotateLines(setUpper, depth);
                if (depth == 3) this.rotateFacetLeft(LOWER);
                break;
            case FRONT:
                List<Position> setFront = Arrays.asList(UPPER, RIGHT,
                        LOWER, LEFT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setFront, depth);
                if (depth == 3) this.rotateFacetLeft(BACK);
                break;
        }
        return this;
    }

    /**
     * method turns the facet to right by using functions rotateFacetRight and rotateLines
     *
     * @param pos   Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnRight(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(RIGHT, LOWER,
                        LEFT, UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setBack, depth);
                if (depth == 3) this.rotateFacetLeft(FRONT);
                break;
            case LEFT:
                List<Position> setLeft = Arrays.asList(UPPER, BACK,
                        LOWER, FRONT);
                this.rotateFacetRight(pos);
                this.rotateLines(setLeft, depth);
                if (depth == 3) this.rotateFacetLeft(RIGHT);
                break;
            case LOWER:
                List<Position> setLower = Arrays.asList(FRONT, LEFT,
                        BACK, RIGHT);
                this.rotateFacetRight(pos);
                this.rotateLines(setLower, depth);
                if (depth == 3) this.rotateFacetLeft(UPPER);
                break;
            case RIGHT:
                List<Position> setRight = Arrays.asList(UPPER, FRONT,
                        LOWER, BACK);
                this.rotateFacetRight(pos);
                this.rotateLines(setRight, depth);
                if (depth == 3) this.rotateFacetLeft(LEFT);
                break;
            case UPPER:
                List<Position> setUpper = Arrays.asList(BACK, LEFT,
                        FRONT, RIGHT);
                this.rotateFacetRight(pos);
                this.rotateLines(setUpper, depth);
                if (depth == 3) this.rotateFacetLeft(LOWER);
                break;
            case FRONT:
                List<Position> setFront = Arrays.asList(LEFT, LOWER,
                        RIGHT, UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setFront, depth);
                if (depth == 3) this.rotateFacetLeft(BACK);
                break;
        }
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube Cube = (Cube) o;
        return Objects.equals(cube, Cube.cube);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cube);
    }

}
