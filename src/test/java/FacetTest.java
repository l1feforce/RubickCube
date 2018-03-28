import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class FacetTest {

    @Test
    public void generate() {
        Facet test = new Facet(3, Position.BACK).generate(Colour.GREEN);
        assertEquals(test, new Facet(3, Position.BACK).generate(Colour.GREEN));
    }
}