package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for testing the Unit class.
 */
public abstract class UnitTest
{

    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point p = new Point(1.0, 1.0);

    /**
     * Test the duplicate method.
     */
    @Test
    public void duplicateTest()
    {
        Unit u = getTestUnit(true, p);
        Unit duplicate = u.duplicate();

        // Same object.
        assertEquals(u, duplicate);
        // Memory location differs.
        assertFalse(u == duplicate);
    }

    /**
     * Test hashcode.
     * Equal objects have an equal hashcode.
     */
    @Test
    public void hashCodeTest()
    {
        Unit u = getTestUnit(true, p);
        Unit duplicate = u.duplicate();

        assertEquals(u.hashCode(), duplicate.hashCode());
    }

    /**
     * Test hashcode.
     * Equal objects have an equal hashcode.
     */
    @Test
    public void inequalHashCodeTest()
    {
        Unit u = getTestUnit(true, p);
        Unit other = getTestUnit(false, p);

        assertNotEquals(u.hashCode(), other.hashCode());
    }

    /**
     * Test equals with the same objects.
     */
    @Test
    public void equalsTest()
    {
        Unit u = getTestUnit(true, p);
        assertEquals(u, u);
    }

    /**
     * Test equals with different objects.
     */
    @Test
    public void notEqualsTest()
    {
        Unit u = getTestUnit(true, p);
        Unit other = getTestUnit(true, new Point(15, 15));
        assertNotEquals(other, u);
    }

    /**
     * Test equals with absolutely different objects.
     */
    @Test
    public void absolutelyNotEqualsTest()
    {
        Unit u = getTestUnit(true, p);
        String other = ":3";
        assertNotEquals(u, other);
    }

    /**
     * Test revive.
     */
    @Test
    public void reviveTest()
    {
        Unit u = getTestUnit(true, p);
        u.revive();
        assertTrue(u.isAlive());
    }

    /**
     * Provide a unit to test with.
     * @param alive Whether the unit should be alive.
     * @param start Where the unit should start.
     * @return The unit to test.
     */
    public abstract Unit getTestUnit(boolean alive, Point start);

    /**
     * Test if we use isAlive() that we get the correct value (true).
     */
    @Test
    public void isAlivePositiveTest()
    {
        Unit u = getTestUnit(true, p);
        assertTrue(u.isAlive());
    }

    /**
     * Test if we use isAlive() that we get the correct value (false).
     */
    @Test
    public void isAliveNegativeTest()
    {
        Unit u = getTestUnit(false, p);
        u.kill();
        assertFalse(u.isAlive());
    }
}
