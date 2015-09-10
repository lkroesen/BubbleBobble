package website.frontrow.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests for testing the Unit class.
 */
public abstract class UnitTest
{
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point p;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point m;

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
     * Test if we use get that we get the inputted value back.
     */
    @Test
    public void getDirectionTest()
    {
        Unit u = getTestUnit(false, p, m);
        assertEquals(u.getLocation(), p);
    }

    /**
     * Test if we use getMotion that we get the inputted motion.
     */
    @Test
    public void getMotionTest()
    {
        Unit u = getTestUnit(true, p, m);
        assertEquals(u.getMotion(), m);
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

    /**
     * Test if setlocation works.
     */
    @Test
    public void setLocationTest()
    {
        Unit u = getTestUnit(true, null, null);
        u.setLocation(m);
        assertEquals(u.getLocation(), m);
    }

    /**
     * Test if setMotion works.
     */
    @Test
    public void setMotionTest()
    {
        Unit u = getTestUnit(true, null, null);
        u.setMotion(p);
        assertEquals(u.getMotion(), p);
    }

    /**
     * Test if setFace works.
     */
    @Test
    public void setFaceTestLeft()
    {
        Unit u = getTestUnit(true, null, null);
        u.setFace(Direction.LEFT);
        assertEquals(u.getFace(), Direction.LEFT);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionLeft()
    {
        Unit u = getTestUnit(true, null, null);
        u.setDirection(Direction.LEFT);
        assertEquals(u.getDirection(), Direction.LEFT);
    }

    /**
     * Test if setFace works.
     */
    @Test
    public void setFaceTestRight()
    {
        Unit u = getTestUnit(true, null, null);
        u.setFace(Direction.RIGHT);
        assertEquals(u.getFace(), Direction.RIGHT);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionRight()
    {
        Unit u = getTestUnit(true, null, null);
        u.setDirection(Direction.RIGHT);
        assertEquals(u.getDirection(), Direction.RIGHT);
    }

    /**
     * Test setDirection.
     */
    @Test
    public void setDirectionDownTest()
    {
        Unit u = getTestUnit(true, null, null);
        u.setDirection(Direction.DOWN);
        assertEquals(u.getDirection(), Direction.DOWN);
    }

    /**
     * Test if setFace errorhandling works.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setFaceErrorTest()
    {
        Unit u = getTestUnit(true, null, null);
        u.setFace(Direction.UP);
    }

    /**
     * Method to be implemented in other test classes.
     * @param alive
     * Boolean : Alive true or false.
     * @param start
     * Point : Starting point.
     * @param end
     * Point : Destination point.
     * @return
     * Return a Unit.
     */
    public abstract Unit getTestUnit(boolean alive, Point start, Point end);
}
