package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the AABB class.
 */
public class AABBTest
{

    /**
     * Test the contructor and the getters.
     */
    @Test
    public void constructorTest1()
    {
        AABB a = new AABB(new Point(0, 0), new Point(1, 1));

        assertEquals(a.getStart(), new Point(0, 0));
        assertEquals(a.getEnd(), new Point(1, 1));
    }

    /**
     * Test the contructor with swapped values and the getters.
     */
    @Test
    public void constructorTest2()
    {
        AABB a = new AABB(new Point(1, 0), new Point(0, 1));

        assertEquals(a.getStart(), new Point(0, 0));
        assertEquals(a.getEnd(), new Point(1, 1));
    }

    /**
     * Test whether overlaps returns true on two overlapping AABBs.
     */
    @Test
    public void testOverlaps()
    {
        AABB aabb1 = new AABB(new Point(0, 0), new Point(3, 3));
        AABB aabb2 = new AABB(new Point(1, 1), new Point(2, 2));

        assertTrue(aabb1.overlaps(aabb2));
        assertTrue(aabb2.overlaps(aabb1));
    }

    /**
     * Test whether overlaps returns false on two non-overlapping AABBs.
     */
    @Test
    public void testNoOverlaps()
    {
        AABB aabb1 = new AABB(new Point(0, 0), new Point(1, 1));
        AABB aabb2 = new AABB(new Point(2, 2), new Point(3, 3));

        assertFalse(aabb1.overlaps(aabb2));
        assertFalse(aabb2.overlaps(aabb1));
    }

    /**
     * Test whether overlaps returns false on two special non-overlapping AABBs.
     */
    @Test
    public void testSingleAxisOverlaps()
    {
        AABB aabb1 = new AABB(new Point(0, 0), new Point(3, 1));
        AABB aabb2 = new AABB(new Point(2, 2), new Point(3, 3));

        assertFalse(aabb1.overlaps(aabb2));
        assertFalse(aabb2.overlaps(aabb1));
    }

}
