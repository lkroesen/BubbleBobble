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
    protected static final Point FIRST_TEST_POINT = new Point(1.0, 1.0);

    /**
     * Test the duplicate method.
     */
    @Test
    public void duplicateTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        Unit duplicate = testUnit.duplicate();

        // Same object.
        assertEquals(testUnit, duplicate);
        // Memory location differs.
        assertFalse(testUnit == duplicate);
    }

    /**
     * Test hashcode.
     * Equal objects have an equal hashcode.
     */
    @Test
    public void hashCodeTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        Unit duplicate = testUnit.duplicate();

        assertEquals(testUnit.hashCode(), duplicate.hashCode());
    }

    /**
     * Test hashcode.
     * Equal objects have an equal hashcode.
     */
    @Test
    public void inequalHashCodeTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        Unit other = getTestUnit(false, FIRST_TEST_POINT);

        assertNotEquals(testUnit.hashCode(), other.hashCode());
    }

    /**
     * Test equals with the same objects.
     */
    @Test
    public void equalsTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        assertEquals(testUnit, testUnit);
    }

    /**
     * Test equals with different objects.
     */
    @Test
    public void notEqualsTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        Unit other = getTestUnit(true, new Point(15, 15));
        assertNotEquals(other, testUnit);
    }

    /**
     * Test equals with absolutely different objects.
     */
    @Test
    public void absolutelyNotEqualsTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        String other = ":3";
        assertNotEquals(testUnit, other);
    }

    /**
     * Test revive.
     */
    @Test
    public void reviveTest()
    {
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        testUnit.revive();
        assertTrue(testUnit.isAlive());
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
        Unit testUnit = getTestUnit(true, FIRST_TEST_POINT);
        assertTrue(testUnit.isAlive());
    }

    /**
     * Test if we use isAlive() that we get the correct value (false).
     */
    @Test
    public void isAliveNegativeTest()
    {
        Unit testUnit = getTestUnit(false, FIRST_TEST_POINT);
        testUnit.kill();
        assertFalse(testUnit.isAlive());
    }

    /**
     * Test if the speed modifier is returned properly.
     */
    @Test
    public void getSpeedModifierTest()
    {
        Unit testUnit = getTestUnit(false, FIRST_TEST_POINT);
        assertEquals(testUnit.getSpeedMultiplier(), 1.0d, 0.75);
    }
}
