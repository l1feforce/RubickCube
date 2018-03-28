
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Facet {

    @NotNull
    private final int height;

    public Map<Point, Colour> getChips() {
        return chips;
    }

    public void setChips(Map<Point, Colour> chips) {
        this.chips = chips;
    }

    @NotNull
    private Map<Point, Colour> chips = new HashMap<>();

    private final Position pos;

    Facet(int height, Position pos) {
        this.height = height;
        this.pos = pos;
    }

    /**
     * @param colour set colour of cells
     * @return filed facet
     */
    public Facet generate(Colour colour) {
        Facet facet = new Facet(3, pos);
        for (int i = 1; i <= 3; i++) {
            for (int k = 1; k <= 3; k++) {
                facet.chips.put(new Point(i, k), colour);
            }
        }
        return facet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facet)) return false;
        Facet facet = (Facet) o;
        return height == facet.height &&
                Objects.equals(chips, facet.chips) &&
                pos == facet.pos;
    }

    @Override
    public int hashCode() {

        return Objects.hash(height, chips, pos);
    }
}

