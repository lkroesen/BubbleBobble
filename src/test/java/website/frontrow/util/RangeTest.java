package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the Range class.
 */
public class RangeTest
{

    /**
     * Test whether a simple range is stored correctly.
     */
    @Test
    public void testConstruction1()
    {
        Range range = new Range(0, 1);

        assertEquals(0, range.getStart(), 0);
        assertEquals(1, range.getEnd(), 0);
    }

    /**
     * Test whether a swapped range is stored correctly.
     */
    @Test
    public void testConstruction2()
    {
        Range range = new Range(1, 0);

        assertEquals(0, range.getStart(), 0);
        assertEquals(1, range.getEnd(), 0);
    }

    /**
     * Test whether lengths are computed correctly.
     */
    @Test
    public void testLength()
    {
        Range range = new Range(2, 5);

        assertEquals(3, range.length(), 0);
    }

    /**
     * Test whether in case of two non-overlapping ranges,
     * the function returns false.
     */
    @Test
    public void noOverlap()
    {
        Range range = new Range(0, 1);
        Range range1 = new Range(2, 3);

        assertFalse(range.overlaps(range1));
        assertFalse(range1.overlaps(range));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void fullOverlap()
    {
        Range range = new Range(0, 4);
        Range range1 = new Range(2, 3);

        assertTrue(range.overlaps(range1));
        assertTrue(range1.overlaps(range));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void partialOverlap()
    {
        Range range = new Range(0, 3);
        Range range1 = new Range(2, 4);

        assertTrue(range.overlaps(range1));
        assertTrue(range1.overlaps(range));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void pointOverlap()
    {
        Range range = new Range(0, 2);
        Range range1 = new Range(2, 4);

        assertTrue(range.overlaps(range1));
        assertTrue(range1.overlaps(range));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void zeroLengthOverlap()
    {
        Range range = new Range(3, 3);
        Range range1 = new Range(2, 4);

        assertTrue(range.overlaps(range1));
        assertTrue(range1.overlaps(range));
    }

    /**
     * Test whether in case of one range being a subrange,
     * the function returns true.
     */
    @Test
    public void zeroLengthNoOverlap()
    {
        Range range = new Range(3, 3);
        Range range1 = new Range(1, 2);

        assertFalse(range.overlaps(range1));
        assertFalse(range1.overlaps(range));
    }


}
