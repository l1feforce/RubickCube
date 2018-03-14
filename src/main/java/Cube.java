import com.sun.istack.internal.Nullable;

import java.util.*;

public final class Cube {

    public static Map<Position, Facet> facet = new HashMap<>();

    private final int height;

    Cube(int height, Map<Position, Facet> facet) {
        this.height = height;
        Cube.facet = facet;
    }

    public final Cube generate() {
        Map<Point, Colour> chips = null;
        facet.put(Position.UPPER, new Facet(height, Position.UPPER).generate(Colour.BLUE));
        facet.put(Position.LOWER, new Facet(height, Position.LOWER).generate(Colour.GREEN));
        facet.put(Position.LEFT, new Facet(height, Position.LEFT).generate(Colour.ORANGE));
        facet.put(Position.RIGHT, new Facet(height, Position.RIGHT).generate(Colour.RED));
        facet.put(Position.FRONT, new Facet(height, Position.FRONT).generate(Colour.WHITE));
        facet.put(Position.BACK, new Facet(height, Position.BACK).generate(Colour.YELLOW));
        return new Cube(3, facet);
    }

    private Map<Point, Colour> copy(Position pos, int depth) {
        Map<Point, Colour> chipsLineCopy = null;
        for (int x = 3; x >= depth; x--) {
            for (int y = 1; y <= 3; y++) {
                chipsLineCopy.put(new Point(x, y),
                        facet.get(pos).chips.get(new Point(x, y)));
            }
        }
        return chipsLineCopy;
    }

    private static Cube turnLinesLeft(List<Position> set, int depth) {
        @Nullable
        Map<Point, Colour> chipsLineCopy = null;
        for (int i = 0; i < 2; i++) {
            for (int x = 3; x >= depth; x--) {
                for (int y = 1; y <= 3; y++) {
                    //do copy of cell
                    chipsLineCopy.put(new Point(x, y),
                            facet.get(set.get(i)).chips.get(new Point(x, y)));
                    //remap old facet's cell
                    facet.get(set.get(i)).chips.put(new Point(x, y),
                            facet.get(set.get(4 - i)).chips.get(new Point(x, y))); //get Colour
                    //remap next facet to previous
                    facet.get(set.get(i + 1)).chips.put(new Point(x, y),
                            chipsLineCopy.get(new Point(x, y)));
                }
            }
        }
        return new Cube(3, facet);
    }

    public final Cube turnLeft(Position pos, int depth) {
        switch (pos) {
            case BACK:
                Map<Point, Colour> chipsCopy = facet.get(Position.BACK).chips;
                List<Position> set = Arrays.asList(Position.RIGHT, Position.LOWER,
                        Position.LEFT, Position.UPPER);
                for (int i = 1; i <= 3; i++) {
                    for (int k = 1; k <= 3; k++) {
                        facet.get(Position.BACK).chips.put(new Point(i, k),
                                chipsCopy.get(new Point(i, 4 - k)));
                    }
                }
                Cube.turnLinesLeft(set, depth);
        }
        return new Cube(3, facet);
    }

    public final Cube turnRight(Position pos, int height) {
        return new Cube(3, facet);
    }

}
