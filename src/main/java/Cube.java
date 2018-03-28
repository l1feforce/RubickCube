import com.sun.istack.internal.Nullable;
import java.util.*;
//FACETS TURNING DOES NOT WORK
//TODO IGNORE PLUGIN
public final class Cube {

    public final Map<Position, Facet> getFacet() {
        return facet;
    }

    public final Facet getCurrentFacet(Position pos) {
        return facet.get(pos);
    }

    private Map<Position, Facet> facet = new HashMap<>();

    private final int height;

    Cube(int height) {
        if (height != 3) throw new IllegalArgumentException("Only 'height = 3' now work");
        this.height = height;
    }

    /**
     * @return new Cube with filled facets
     */
    public final Cube generate() {
        Cube cube = new Cube(3);
        cube.facet.put(Position.UPPER, new Facet(height, Position.UPPER).generate(Colour.BLUE));
        cube.facet.put(Position.LOWER, new Facet(height, Position.LOWER).generate(Colour.GREEN));
        cube.facet.put(Position.LEFT, new Facet(height, Position.LEFT).generate(Colour.ORANGE));
        cube.facet.put(Position.RIGHT, new Facet(height, Position.RIGHT).generate(Colour.RED));
        cube.facet.put(Position.FRONT, new Facet(height, Position.FRONT).generate(Colour.WHITE));
        cube.facet.put(Position.BACK, new Facet(height, Position.BACK).generate(Colour.YELLOW));
        return cube;
    }

    /**
     *turns the cube random number of times
     * @return Cube with random state
     */
    public final Cube randomize() {
        List<Position> set = Arrays.asList(Position.RIGHT, Position.LOWER,
                Position.LEFT, Position.UPPER, Position.BACK, Position.FRONT);
        for (Position pos : set) {
            for (int i = 0; i < 1 + (int) (Math.random() * 3); i++) {
                this.turnLeft(pos, 1 + (int) (Math.random() * 2));
            }
        }
        return this;
    }

    /**
     * function rotates cells on other facets
     * @param set list with Positions that need to be reassigned
     * @param depth how many layers of the cube need to turn
     */
    private void turnLines(List<Position> set, int depth) {
        @Nullable
        Map<Point, Colour> chipsLineCopy = new HashMap<>();

        for (int i = 2; i >= 0; i--) {
            for (int x = 3; x > 3 - depth; x--) {
                for (int y = 1; y <= 3; y++) {
                    //makes a copy of the cell
                    chipsLineCopy.put(new Point(x, y),
                            this.facet.get(set.get(i + 1)).getChips().get(new Point(x, y)));
                    //remap old facet's cell
                    this.facet.get(set.get(i + 1)).getChips().put(new Point(x, y),
                            this.facet.get(set.get(i)).getChips().get(new Point(x, y))); //get Colour
                    if (i == 0) {
                        this.facet.get(set.get(i)).getChips().put(new Point(x, y),
                                chipsLineCopy.get(new Point(x, y)));
                    }
                }
            }
        }
    }

    /**
     * function rotates facet with Position pos
     * @param pos Position of facet that need to turn
     */
    private void turnFacetLeft(Position pos) {
        Map<Point, Colour> chips = new HashMap<>();
        Map<Point, Colour> chipsCopy = this.facet.get(pos).getChips();
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                chips.put(new Point(y, x),
                        chipsCopy.get(new Point(4 - x, y)));
            }
        }
        this.facet.get(pos).setChips(chips);
    }

    /**
     * method turns the facet left by using functions turnFacet and turnLines
     * @param pos Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnLeft(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(Position.RIGHT, Position.LOWER,
                        Position.LEFT, Position.UPPER);
                this.turnFacetLeft(pos);
                this.turnLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(Position.UPPER, Position.BACK,
                        Position.LOWER, Position.FRONT);
                this.turnFacetLeft(pos);
                this.turnLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(Position.FRONT, Position.LEFT,
                        Position.BACK, Position.RIGHT);
                this.turnFacetLeft(pos);
                this.turnLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(Position.UPPER, Position.FRONT,
                        Position.LOWER, Position.BACK);
                this.turnFacetLeft(pos);
                this.turnLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(Position.BACK, Position.LEFT,
                        Position.FRONT, Position.RIGHT);
                this.turnFacetLeft(pos);
                this.turnLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(Position.LEFT, Position.LOWER,
                        Position.RIGHT, Position.UPPER);
                this.turnFacetLeft(pos);
                this.turnLines(setFront, depth);
        }
        return this;
    }

    /**
     * function rotates facet with Position pos
     * @param pos Position of facet that need to turn
     */
    private void turnFacetRight(Position pos) {
        Map<Point, Colour> chips = new HashMap<>();
        Map<Point, Colour> chipsCopy = this.facet.get(pos).getChips();
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                chips.put(new Point(y, x),
                        chipsCopy.get(new Point(x, 4 - y)));
            }
        }
        this.facet.get(pos).setChips(chips);
    }

    /**
     * method turns the facet left by using functions turnFacet and turnLines
     * @param pos Position of facet that need to turn
     * @param depth how many layers of the cube need to turn
     * @return Cube with a turned facet
     */
    public final Cube turnRight(Position pos, int depth) {
        switch (pos) {
            case BACK:
                List<Position> setBack = Arrays.asList(Position.UPPER, Position.LEFT,
                        Position.LOWER, Position.RIGHT);
                this.turnFacetRight(pos);
                this.turnLines(setBack, depth);
            case LEFT:
                List<Position> setLeft = Arrays.asList(Position.FRONT, Position.LOWER,
                        Position.BACK, Position.UPPER);
                this.turnFacetRight(pos);
                this.turnLines(setLeft, depth);
            case LOWER:
                List<Position> setLower = Arrays.asList(Position.RIGHT, Position.BACK,
                        Position.LEFT, Position.FRONT);
                this.turnFacetRight(pos);
                this.turnLines(setLower, depth);
            case RIGHT:
                List<Position> setRight = Arrays.asList(Position.BACK, Position.LOWER,
                        Position.FRONT, Position.UPPER);
                this.turnFacetRight(pos);
                this.turnLines(setRight, depth);
            case UPPER:
                List<Position> setUpper = Arrays.asList(Position.RIGHT, Position.FRONT,
                        Position.LEFT, Position.BACK);
                this.turnFacetRight(pos);
                this.turnLines(setUpper, depth);
            case FRONT:
                List<Position> setFront = Arrays.asList(Position.UPPER, Position.RIGHT,
                        Position.LOWER, Position.LEFT);
                this.turnFacetRight(pos);
                this.turnLines(setFront, depth);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cube)) return false;
        Cube cube = (Cube) o;
        return height == cube.height &&
                Objects.equals(facet, cube.facet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(facet, height);
    }
}
