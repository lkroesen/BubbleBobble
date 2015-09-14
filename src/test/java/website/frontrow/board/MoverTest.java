package website.frontrow.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.level.Level;
import website.frontrow.util.GameConstants;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * Tests the mover class.
 *
 */
public abstract class MoverTest
    extends UnitTest
{
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point m;

    /**
     * Create the mover for the tests.
     * @param alive Whether the mover is alive.
     * @param location The starting point.
     * @param motion The ending poing.
     * @return A unit to test.
     */
    public abstract Mover getTestMover(boolean alive, Point location, Point motion);

    /**
     * Provides a test unit for testing.
     * @param alive Whether the unit should be alive.
     * @param location The location of the unit.
     * @return The Unit
     */
    public Unit getTestUnit(boolean alive, Point location)
    {
        return getTestMover(alive, location, new Point(0, 0));
    }

    /**
     * Execute before all tests.
     */
    @Before
    public void setUp()
    {
        m = new Point(0.0, 0.0);
    }

    /**
     * Teardown after the tests.
     */
    @After
    public void tearDown()
    {
        m = null;
    }

    /**
     * Test if we use get that we get the inputted value back.
     */
    @Test
    public void getDirectionTest()
    {
        Mover u = getTestMover(true, p, m);
        assertEquals(u.getLocation(), p);
    }

    /**
     * Test if we use getMotion that we get the inputted motion.
     */
    @Test
    public void getMotionTest()
    {
        Mover u = getTestMover(true, p, m);
        assertEquals(u.getMotion(), m);
    }

    /**
     * Test if setlocation works.
     */
    @Test
    public void setLocationTest()
    {
        Mover u = getTestMover(true, null, null);
        u.setLocation(m);
        assertEquals(u.getLocation(), m);
    }

    /**
     * Test if setMotion works.
     */
    @Test
    public void setMotionTest()
    {
        Mover u = getTestMover(true, null, null);
        u.setMotion(p);
        assertEquals(u.getMotion(), p);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionLeft()
    {
        Mover u = getTestMover(true, null, null);
        u.setDirection(Direction.LEFT);
        assertEquals(u.getDirection(), Direction.LEFT);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionRight()
    {
        Mover u = getTestMover(true, null, null);
        u.setDirection(Direction.RIGHT);
        assertEquals(u.getDirection(), Direction.RIGHT);
    }

    /**
     * Test setDirection.
     */
    @Test
    public void setDirectionDownTest()
    {
        Mover u = getTestMover(true, null, null);
        u.setDirection(Direction.DOWN);
        assertEquals(u.getDirection(), Direction.DOWN);
    }

    /**
     * Test the tick method.
     */
    @Test
    public void testTick()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(GameConstants.TICKS_PER_SEC, 0));

        u.tick(mock(Level.class));
        // TODO When collisions are implemented, some calls to those checks need to be checked here.

        Point expected = new Point(0, 0);
        assertEquals(expected, u.getLocation());
    }

    /**
     * Test whether the unit moves left.
     */
    @Test
    public void testGoLeft()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(0, 0));

        u.goLeft();
        u.tick(mock(Level.class));

        assertFalse(u.getLocation().getX() < 0);
    }

    /**
     * Test whether the unit moves right.
     */
    @Test
    public void testGoRight()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(0, 0));

        u.goRight();
        u.tick(mock(Level.class));

        assertFalse(u.getLocation().getX() > 0);
    }

    /**
     * Tests whether the unit does not move when not speed is given.
     */
    @Test
    public void testDontMove() // There's a wasp in your hair.
    {
        Point start = new Point(0, 0);
        Mover u = getTestMover(true, start, new Point(0, 0));

        u.tick(mock(Level.class));

        assertEquals(start, u.getLocation());
    }

    /**
     * Tests whether the player really goes left after going right first.
     */
    @Test
    public void testReallyGoLeft()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(0, 0));

        u.goRight();
        u.goLeft();
        u.tick(mock(Level.class));

        assertFalse(u.getLocation().getX() < 0);
    }

}
