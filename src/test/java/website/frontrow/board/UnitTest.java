package website.frontrow.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests for testing the Unit class.
 */
public abstract class UnitTest
    extends MoverTest
{

    /**
     * Provide a mover to test with.
     * @param start The starting point.
     * @param motion The motion.
     * @return The unit to test with.
     */
    public Mover getTestMover(Point start, Point motion)
    {
        return getTestUnit(true, start, motion);
    }

    /**
     * Provide a unit to test with.
     * @param alive Whether the unit should be alive.
     * @param start Where the unit should start.
     * @param direction The direction.
     * @return The unit to test.
     */
    public abstract Unit getTestUnit(boolean alive, Point start, Point direction);

    /**
     * Execute before all tests.
     */
    @Before
    public void setup()
    {
        p = new Point(1.0, 1.0);
        m = new Point(0.0, 0.0);
    }

    /**
     * Teardown after the tests.
     */
    @After
    public void teardown()
    {
        p = null;
        m = null;
    }

    /**
     * Test if we use isAlive() that we get the correct value (true).
     */
    @Test
    public void isAlivePositiveTest()
    {
        Unit u = getTestUnit(true, p, m);
        assertTrue(u.isAlive());
    }

    /**
     * Test if we use isAlive() that we get the correct value (false).
     */
    @Test
    public void isAliveNegativeTest()
    {
        Unit u = getTestUnit(false, p, m);
        assertFalse(u.isAlive());
    }

}
