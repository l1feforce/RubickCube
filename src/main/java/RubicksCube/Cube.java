package RubicksCube;

import java.util.*;

import static RubicksCube.Position.*;

public class Cube {

    private Map<Position, int[][]> cube = new HashMap<>();

    public final Map<Position, int[][]> getCube() {
        return cube;
    }

    public final void setCube(Map<Position, int[][]> cube) {
        this.cube = cube;
    }

    /**
     * @param k colour of facet
     * @return filled facet(array)
     */
    private int[][] fill(int k) {
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
    public final Cube generate() {
        Cube cubeNull = new Cube();
        List<Position> allPositions = Arrays.asList(RIGHT, LOWER,
                LEFT, UPPER, BACK, FRONT);
        for (Position pos : allPositions
                ) {
            cubeNull.cube.put(pos, fill(allPositions.indexOf(pos)));
        }
        return cubeNull;
    }

    /**
     * turns Cube random number of times
     * @return Cube with random state
     */
    public final Cube randomize() {
        Cube cubeNew = new Cube().generate();
        List<Position> set = Arrays.asList(RIGHT, LOWER,
                LEFT, UPPER, BACK, FRONT);
        for (Position pos : set) {
            for (int i = 0; i < 1 + (int) (Math.random() * 3); i++) {
                cubeNew.turnLeft(pos, 1 + (int) (Math.random() * 2));
            }
        }
        return cubeNew;
    }

    /**
     * function rotates cells on other facets
     * @param set list with Positions that need to be reassigned
     * @param depth how many layers of the cube need to turn
     */
    private void rotateLines(List<Position> set, int depth) {
        int[][] copyOfFacet = cube.get(set.get(0));
        for (int i = 0; i <= 2; i++) {
            for (int y = 2; y >= depth - 1; y--) {
                for (int x = 0; x <= 2; x++) {
                    cube.get(set.get(i))[x][y] = cube.get(set.get(i + 1))[x][y];
                    if (i == 2) cube.get(set.get(i))[x][y] = copyOfFacet[x][y];
                }
            }
        }
    }

    /**
     * function rotates facet to left with Position pos
     * @param pos Position of facet that need to turn
     */
    private void rotateFacetLeft(Position pos) {
        for (int k = 0; k < 1; k++) // border -> center
        {
            for (int j = k; j < 2 - k; j++) // left -> right
            {
                // меняем местами 4 угла
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
     * @param pos Position of facet that need to turn
     */
    private void rotateFacetRight(Position pos) {
        for (int i = 1; i <= 3; i++) {
            this.rotateFacetLeft(pos);
        }
    }

    /**
     * method turns the facet to left by using functions rotateFacetLeft and rotateLines
     * @param pos Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnLeft(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(RIGHT, LOWER,
                        LEFT, UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(UPPER, BACK,
                        LOWER, FRONT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(FRONT, LEFT,
                        BACK, RIGHT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(UPPER, FRONT,
                        LOWER, BACK);
                this.rotateFacetLeft(pos);
                this.rotateLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(BACK, LEFT,
                        FRONT, RIGHT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(LEFT, LOWER,
                        RIGHT, UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setFront, depth);
        }
        return this;
    }

    /**
     * method turns the facet to right by using functions rotateFacetRight and rotateLines
     * @param pos Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnRight(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(UPPER, LEFT,
                        LOWER, RIGHT);
                this.rotateFacetRight(pos);
                this.rotateLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(FRONT, LOWER,
                        BACK, UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(RIGHT, BACK,
                        LEFT, FRONT);
                this.rotateFacetRight(pos);
                this.rotateLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(BACK, LOWER,
                        FRONT, UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(RIGHT, FRONT,
                        LEFT, BACK);
                this.rotateFacetRight(pos);
                this.rotateLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(UPPER, RIGHT,
                        LOWER, LEFT);
                this.rotateFacetRight(pos);
                this.rotateLines(setFront, depth);
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
