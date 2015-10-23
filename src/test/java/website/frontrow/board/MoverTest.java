package website.frontrow.board;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the mover class.
 *
 */
public abstract class MoverTest
    extends UnitTest
{
	//subclasses need to be able to access this information.
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Point secondTestPoint;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Level emptyLevel = new Level(new ArrayList<>(), new ArrayList<>(), new Grid<>(0, 0));

    /**
     * Create the mover for the tests.
     * @param alive Whether the mover is alive.
     * @param location The starting FIRST_TEST_POINT.
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
        secondTestPoint = new Point(0.0, 0.0);
    }

    /**
     * Teardown after the tests.
     */
    @After
    public void tearDown()
    {
        secondTestPoint = null;
    }

    /**
     * Test if we use get that we get the inputted value back.
     */
    @Test
    public void getDirectionTest()
    {
        Mover testMover = getTestMover(true, FIRST_TEST_POINT, secondTestPoint);
        assertEquals(testMover.getLocation(), FIRST_TEST_POINT);
    }

    /**
     * Test if we use getMotion that we get the inputted motion.
     */
    @Test
    public void getMotionTest()
    {
        Mover testMover = getTestMover(true, FIRST_TEST_POINT, secondTestPoint);
        assertEquals(testMover.getMotion(), secondTestPoint);
    }

    /**
     * Test if setlocation works.
     */
    @Test
    public void setLocationTest()
    {
        Mover testMover = getTestMover(true, null, null);
        testMover.setLocation(secondTestPoint);
        assertEquals(testMover.getLocation(), secondTestPoint);
    }

    /**
     * Test if setMotion works.
     */
    @Test
    public void setMotionTest()
    {
        Mover testMover = getTestMover(true, null, null);
        testMover.setMotion(FIRST_TEST_POINT);
        assertEquals(testMover.getMotion(), FIRST_TEST_POINT);
    }

    /**
     * Test the tick method.
     */
    @Test
    public void testTick()
    {
        Mover testMover = getTestMover(true, new Point(0, 0),
                new Point(GameConstants.TICKS_PER_SEC, 0));

        testMover.tick(emptyLevel);
        // TODO When collisions are implemented, some calls to those checks need to be checked here.

        assertEquals(-1, testMover.getLocation().getX(), 0.0004);
    }

    /**
     * Tests whether the unit does not move when not speed is given.
     * Except there is gravity. So we need to keep that in mind.
     */
    @Test
    public void testDontMove() // There's a wasp in your hair.
    {
        Point start = new Point(0, 0);
        Mover testMover = getTestMover(true, start, new Point(0, 0));

        testMover.tick(emptyLevel);

        assertEquals(0, testMover.getLocation().getX(), 0.00001);
    }

    /**
     * Tests whether the player really goes left after going right first.
     */
    @Test
    public void testReallyGoLeft()
    {
        Mover testMover = getTestMover(true, new Point(0, 0), new Point(0, 0));

        testMover.goRight();
        testMover.goLeft();
        testMover.tick(emptyLevel);

        assertTrue(testMover.getLocation().getX() < 0);
    }

    /**
     * Test onCollision.
     */
    @Test
    public void testOnCollision()
    {
        Mover mover = getTestMover(true, FIRST_TEST_POINT, secondTestPoint);
        mover.onCollision(Cell.WALL);
        assertNotNull(mover);
    }
}
