package website.frontrow.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import website.frontrow.level.Level;
import website.frontrow.game.GameConstants;
import website.frontrow.level.MapParser;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests the mover class.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class MoverTest
    extends UnitTest
{
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point m;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Level emptyLevel = new Level(new ArrayList<>(), new ArrayList<>(), new Grid<>(0, 0));

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
     * Test the tick method.
     */
    @Test
    public void testTick()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(GameConstants.TICKS_PER_SEC, 0));

        u.tick(emptyLevel);
        // TODO When collisions are implemented, some calls to those checks need to be checked here.

        assertEquals(-1, u.getLocation().getX(), 0.0004);
    }

    /**
     * Test whether the unit moves left.
     * @throws IOException The test file might not be found.
     */
    @Test
    public void testGoLeft() throws IOException
    {
        MapParser mp = new MapParser();
        InputStream map = getClass().getResourceAsStream("/testMove.txt");
        Level level = mp.parseMap(map);

        Player u = level.getPlayers().get(0);
        Point location = u.getLocation();

        u.goLeft();
        u.tick(level);
        assertTrue(u.getLocation().getX() < location.getX());
    }

    /**
     * Test whether the unit moves right.
     * @throws IOException The test file might not be found.
     */
    @Test
    public void testGoRight() throws IOException
    {
        MapParser mp = new MapParser();
        InputStream map = getClass().getResourceAsStream("/testMove.txt");
        Level level = mp.parseMap(map);

        Player u = level.getPlayers().get(0);
        Point location = u.getLocation();

        u.goRight();
        u.tick(level);
        assertTrue(u.getLocation().getX() > location.getX());
    }

    /**
     * Tests whether the unit does not move when not speed is given.
     * Except there is gravity. So we need to keep that in mind.
     * @throws IOException The test file might not be found.
     */
    @Test
    public void testDontMove() throws IOException
    {
        MapParser mp = new MapParser();
        InputStream map = getClass().getResourceAsStream("/testMove.txt");
        Level level = mp.parseMap(map);

        Player u = level.getPlayers().get(0);
        Point location = u.getLocation();

        u.tick(level);
        assertEquals(u.getLocation().getX(), location.getX(), 0.001);
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
        u.tick(emptyLevel);

        assertTrue(u.getLocation().getX() < 0);
    }

}
