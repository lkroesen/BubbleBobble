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
{

    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point p;

    /**
     * Set the location of the unit.
     */
    @Before
    public void setUp()
    {
        p = new Point(1.0, 1.0);
    }

    /**
     * Tears down the dependencies for the tests.
     */
    @After
    public void tearDown()
    {
        p = null;
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
