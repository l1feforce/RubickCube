import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class FacetTest {

    @Test
    public void generate() {
        Map<Point, Colour> chips = null;
        Facet test = new Facet(3, Position.BACK);
        assertEquals(1, test.generate(Colour.GREEN).chips);
    }
}