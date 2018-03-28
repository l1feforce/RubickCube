import java.util.Arrays;
import java.util.List;
import java.util.*;

//почему то не работает+не работает equals
public class CubeTRUE {

    private Map<Position, int[][]> cube = new HashMap<>();

    public final Map<Position, int[][]> getCube() {
        return cube;
    }

    public final void setCube(Map<Position, int[][]> cube) {
        this.cube = cube;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CubeTRUE)) return false;
        CubeTRUE cubeTRUE = (CubeTRUE) o;
        return Objects.equals(cube, cubeTRUE.cube);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cube);
    }

    public final CubeTRUE generate() {
        List<Position> allPositions = Arrays.asList(Position.RIGHT, Position.LOWER,
                Position.LEFT, Position.UPPER, Position.BACK, Position.FRONT);
        for (Position pos : allPositions
                ) {
            int[][] cubeArr = new int[3][3];
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    cubeArr[x][y] = allPositions.indexOf(pos);
                }
            }
            cube.put(pos, cubeArr);
        }
        return this;
    }

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

    private void rotateFacetLeft(Position pos) {
        /*int[][] copyOfCube = this.getCube().get(pos);
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                this.cube.get(pos)[y][x] = copyOfCube[x][2 - y];
            }
        }
         */
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

    private void rotateFacetRight(Position pos) {
        /*int[][] copyOfCube = this.getCube().get(pos);
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                this.cube.get(pos)[y][x] = copyOfCube[2 - x][y];
            }
        }
       */
        for (int i=1;i<=3; i++) {
            this.rotateFacetLeft(pos);
        }
    }

    public final CubeTRUE turnLeft(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(Position.RIGHT, Position.LOWER,
                        Position.LEFT, Position.UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(Position.UPPER, Position.BACK,
                        Position.LOWER, Position.FRONT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(Position.FRONT, Position.LEFT,
                        Position.BACK, Position.RIGHT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(Position.UPPER, Position.FRONT,
                        Position.LOWER, Position.BACK);
                this.rotateFacetLeft(pos);
                this.rotateLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(Position.BACK, Position.LEFT,
                        Position.FRONT, Position.RIGHT);
                this.rotateFacetLeft(pos);
                this.rotateLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(Position.LEFT, Position.LOWER,
                        Position.RIGHT, Position.UPPER);
                this.rotateFacetLeft(pos);
                this.rotateLines(setFront, depth);
        }
        return this;
    }

    public final CubeTRUE turnRight(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(Position.UPPER, Position.LEFT,
                        Position.LOWER, Position.RIGHT);
                this.rotateFacetRight(pos);
                this.rotateLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(Position.FRONT, Position.LOWER,
                        Position.BACK, Position.UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(Position.RIGHT, Position.BACK,
                        Position.LEFT, Position.FRONT);
                this.rotateFacetRight(pos);
                this.rotateLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(Position.BACK, Position.LOWER,
                        Position.FRONT, Position.UPPER);
                this.rotateFacetRight(pos);
                this.rotateLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(Position.RIGHT, Position.FRONT,
                        Position.LEFT, Position.BACK);
                this.rotateFacetRight(pos);
                this.rotateLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(Position.UPPER, Position.RIGHT,
                        Position.LOWER, Position.LEFT);
                this.rotateFacetRight(pos);
                this.rotateLines(setFront, depth);
        }
        return this;
    }

}
