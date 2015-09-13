package website.frontrow.board;

import org.junit.Test;
import website.frontrow.Game;
import website.frontrow.level.Level;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * Created by larsstegman on 13-09-15.
 */
public abstract class MoverTest
{
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point p;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point m;

    /**
     * Create the mover for the tests.
     * @param start The starting point.
     * @param end The ending poing.
     * @return A unit to test.
     */
    public abstract Mover getTestMover(Point start, Point end);

    /**
     * Test if we use get that we get the inputted value back.
     */
    @Test
    public void getDirectionTest()
    {
        Mover u = getTestMover(p, m);
        assertEquals(u.getLocation(), p);
    }

    /**
     * Test if we use getMotion that we get the inputted motion.
     */
    @Test
    public void getMotionTest()
    {
        Mover u = getTestMover(p, m);
        assertEquals(u.getMotion(), m);
    }

    /**
     * Test if setlocation works.
     */
    @Test
    public void setLocationTest()
    {
        Mover u = getTestMover(null, null);
        u.setLocation(m);
        assertEquals(u.getLocation(), m);
    }

    /**
     * Test if setMotion works.
     */
    @Test
    public void setMotionTest()
    {
        Mover u = getTestMover(null, null);
        u.setMotion(p);
        assertEquals(u.getMotion(), p);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionLeft()
    {
        Mover u = getTestMover(null, null);
        u.setDirection(Direction.LEFT);
        assertEquals(u.getDirection(), Direction.LEFT);
    }

    /**
     * Test if setDirection works.
     */
    @Test
    public void setDirectionRight()
    {
        Mover u = getTestMover(null, null);
        u.setDirection(Direction.RIGHT);
        assertEquals(u.getDirection(), Direction.RIGHT);
    }

    /**
     * Test setDirection.
     */
    @Test
    public void setDirectionDownTest()
    {
        Mover u = getTestMover(null, null);
        u.setDirection(Direction.DOWN);
        assertEquals(u.getDirection(), Direction.DOWN);
    }

    /**
     * Test the tick method.
     */
    @Test
    public void testTick()
    {
        Mover u = getTestMover(new Point(0, 0), new Point(Game.TICKS_PER_SEC, 0));

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
        Mover u = getTestMover(new Point(0, 0), new Point(0, 0));

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
        Mover u = getTestMover(new Point(0, 0), new Point(0, 0));

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
        Mover u = getTestMover(start, new Point(0, 0));

        u.tick(mock(Level.class));

        assertEquals(start, u.getLocation());
    }

    /**
     * Tests whether the player really goes left after going right first.
     */
    @Test
    public void testReallyGoLeft()
    {
        Mover u = getTestMover(new Point(0, 0), new Point(0, 0));

        u.goRight();
        u.goLeft();
        u.tick(mock(Level.class));

        assertFalse(u.getLocation().getX() < 0);
    }

}
