package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the Range class.
 */
public class RangeTest {

    /**
     * Test whether a simple range is stored correctly.
     */
    @Test
    public void testConstruction1()
    {
        Range x = new Range(0, 1);

        assertEquals(0, x.getStart(), 0);
        assertEquals(1, x.getEnd(), 0);
    }

    /**
     * Test whether a swapped range is stored correctly.
     */
    @Test
    public void testConstruction2()
    {
        Range x = new Range(1, 0);

        assertEquals(0, x.getStart(), 0);
        assertEquals(1, x.getEnd(), 0);
    }

    /**
     * Test whether lengths are computed correctly.
     */
    @Test
    public void testLength()
    {
        Range x = new Range(2, 5);

        assertEquals(3, x.length(), 0);
    }

    /**
     * Test whether in case of two non-overlapping ranges,
     * the function returns false.
     */
    @Test
    public void noOverlap()
    {
        Range x = new Range(0, 1);
        Range y = new Range(2, 3);

        assertFalse(x.overlaps(y));
        assertFalse(y.overlaps(x));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void fullOverlap()
    {
        Range x = new Range(0, 4);
        Range y = new Range(2, 3);

        assertTrue(x.overlaps(y));
        assertTrue(y.overlaps(x));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void partialOverlap()
    {
        Range x = new Range(0, 3);
        Range y = new Range(2, 4);

        assertTrue(x.overlaps(y));
        assertTrue(y.overlaps(x));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void pointOverlap()
    {
        Range x = new Range(0, 2);
        Range y = new Range(2, 4);

        assertTrue(x.overlaps(y));
        assertTrue(y.overlaps(x));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void zeroLengthOverlap()
    {
        Range x = new Range(3, 3);
        Range y = new Range(2, 4);

        assertTrue(x.overlaps(y));
        assertTrue(y.overlaps(x));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void zeroLengthNoOverlap()
    {
        Range x = new Range(3, 3);
        Range y = new Range(1, 2);

        assertFalse(x.overlaps(y));
        assertFalse(y.overlaps(x));
    }


}
