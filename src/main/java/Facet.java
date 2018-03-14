
import java.util.HashMap;
import java.util.Map;

/*
К сожалению я отсутствовал 2 недели и не смог заверишь работу вовремя, работоспособности на данный момент нет :c
*/

public class Facet {

    private final int height;

    public Map<Point, Colour> chips= new HashMap<>();

    private final Position pos;

    Facet(int height, Position pos) {
        this.height = height;
        this.pos = pos;
    }

    public Facet generate(Colour colour) {
        for (int i = 1; i <= 3; i++) {
            for (int k = 1; k <= 3; k++) {
                chips.put(new Point(i, k), colour);
            }
        }
        return new Facet(height, pos);
    }

}

