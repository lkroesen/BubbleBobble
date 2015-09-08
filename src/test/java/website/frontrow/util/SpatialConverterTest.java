package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the Spatial converter.
 */
public class SpatialConverterTest
{
    /**
     * Test Identity.
     */
    @Test
    public void simpleIdentityTest()
    {
        SpatialConverter sC = new SpatialConverter(10, 10, 10, 10);

        Point test = new Point(1, 1);
        assertEquals(test, sC.convertGridToScreen(test));
    }

    /**
     * Test scale of 10 identity.
     */
    @Test
    public void scale10IdentityTest()
    {
        SpatialConverter sC = new SpatialConverter(10, 10, 100, 100);
        Point test = new Point(1, 1);
        Point expected = new Point(10, 10);
        assertEquals(expected, sC.convertGridToScreen(test));
    }
}
