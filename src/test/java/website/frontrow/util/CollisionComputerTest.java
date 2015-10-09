package website.frontrow.util;

import org.junit.Test;
import website.frontrow.board.Mover;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

/**
 * Test whether the collisionhandler handles preexisting collisions correctly.
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public class CollisionComputerTest
{
    /**
     * Prepare all the things.
     */
    ArrayList<Player> emptyPlayer = new ArrayList<>();
    ArrayList<Unit> emptyUnit = new ArrayList<>();

    /**
     * Test AABB level check without collision.
     */
    @Test
    public void freeAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionComputer handler = new CollisionComputer(check);
        assertEquals(Cell.EMPTY, handler.checkLevelAABB(
                new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, 0)
        ).getType());
    }

    /**
     * Test AABB level check.
     */
    @Test
    public void slightlyOccupiedAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionComputer handler = new CollisionComputer(check);
        assertNotEquals(Cell.EMPTY, handler.checkLevelAABB(
                new AABB(new Point(1.5, 1.5), new Point(2.5, 2.5)), new Point(0, 0)
        ).getType());
    }

    /**
     * Test AABB level check without collision.
     */
    @Test
    public void platformMotionUpAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.PLATFORM, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionComputer handler = new CollisionComputer(check);
        assertEquals(Cell.EMPTY, handler.checkLevelAABB(
                new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, -1)
        ).getType());
    }

    /**
     * Test AABB level check without collision.
     */
    @Test
    public void platformMotionDownAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.PLATFORM, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 4));
        CollisionComputer handler = new CollisionComputer(check);
        assertNotEquals(Cell.EMPTY, handler.checkLevelAABB(
                new AABB(new Point(1, 1.1), new Point(2, 2.1)), new Point(0, 1)
        ).getType());
    }

    /**
     * Test getNextPosition in a terrible box of terribleness.
     */
    @Test
    public void testGetNextPositionTestRestricted()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionComputer handler = new CollisionComputer(check);
        Player player = new Player(new Point(1, 1), null);
        player.setMotion(new Point(1, GameConstants.TICKS_PER_SEC));
        assertEquals(new Point(1, 1), handler.findNextPosition(player).getLocation());
    }

    /**
     * Some special box of terribleness.
     */
    @Test
    public void testGetNextPositionTestSlightlyRestricted()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 4));
        CollisionComputer handler = new CollisionComputer(check);
        Player player = new Player(new Point(1, 1), null);
        player.setMotion(new Point(0, 3 * GameConstants.TICKS_PER_SEC));
        Point c = handler.findNextPosition(player).getLocation();
        assertEquals(new Point(1, 2), c);
    }

    /**
     * Test the level bounds when the mover is not near it.
     */
    @Test
    public void testCheckLevelBoundsWithinBounds()
    {
        Mover mover = mock(Mover.class, RETURNS_DEEP_STUBS);
        when(mover.getAABB().getXRange().length()).thenReturn(1.0);
        when(mover.getAABB().getYRange().length()).thenReturn(1.0);

        Level level = mock(Level.class, RETURNS_DEEP_STUBS);
        when(level.getCells().getWidth()).thenReturn(10);
        when(level.getCells().getHeight()).thenReturn(10);

        CollisionComputer ch = new CollisionComputer(level);

        Point targetLocation = new Point(5, 2);
        Point finalLocation = ch.checkLevelBounds(mover, targetLocation);

        assertEquals(targetLocation, finalLocation);
    }

    /**
     * Test the level bounds when the mover is about to go over the x bounds.
     */
    @Test
    public void testCheckLevelBoundsNearRightBounds()
    {
        Mover mover = mock(Mover.class, RETURNS_DEEP_STUBS);
        when(mover.getAABB().getXRange().length()).thenReturn(1.0);
        when(mover.getAABB().getYRange().length()).thenReturn(1.0);

        Level level = mock(Level.class, RETURNS_DEEP_STUBS);
        when(level.getCells().getWidth()).thenReturn(10);
        when(level.getCells().getHeight()).thenReturn(10);

        CollisionComputer ch = new CollisionComputer(level);

        Point targetLocation = new Point(10, 1);
        Point finalLocation = ch.checkLevelBounds(mover, targetLocation);

        assertTrue(targetLocation.getX() > finalLocation.getX());
    }

    /**
     * Test the level bounds when the mover is about to go under the x bounds.
     */
    @Test
    public void testCheckLevelBoundsNearLeftBounds()
    {
        Mover mover = mock(Mover.class, RETURNS_DEEP_STUBS);
        when(mover.getAABB().getXRange().length()).thenReturn(1.0);
        when(mover.getAABB().getYRange().length()).thenReturn(1.0);

        Level level = mock(Level.class, RETURNS_DEEP_STUBS);
        when(level.getCells().getWidth()).thenReturn(10);
        when(level.getCells().getHeight()).thenReturn(10);

        CollisionComputer ch = new CollisionComputer(level);

        Point targetLocation = new Point(-1, 1);
        Point finalLocation = ch.checkLevelBounds(mover, targetLocation);

        assertTrue(targetLocation.getX() < finalLocation.getX());
    }

    /**
     * Test the level bounds when the mover is about to go over the y bounds.
     */
    @Test
    public void testCheckLevelBoundsOverLowerBounds()
    {
        Mover mover = mock(Mover.class, RETURNS_DEEP_STUBS);
        when(mover.getAABB().getXRange().length()).thenReturn(1.0);
        when(mover.getAABB().getYRange().length()).thenReturn(1.0);

        Level level = mock(Level.class, RETURNS_DEEP_STUBS);
        when(level.getCells().getWidth()).thenReturn(10);
        when(level.getCells().getHeight()).thenReturn(10);

        CollisionComputer ch = new CollisionComputer(level);

        Point targetLocation = new Point(2, 11);
        Point finalLocation = ch.checkLevelBounds(mover, targetLocation);

        assertTrue(targetLocation.getY() > finalLocation.getY());
    }

    /**
     * Test the level bounds when the mover is about to go under the y bounds.
     */
    @Test
    public void testCheckLevelBoundsOverUpperBounds()
    {
        Mover mover = mock(Mover.class, RETURNS_DEEP_STUBS);
        when(mover.getAABB().getXRange().length()).thenReturn(1.0);
        when(mover.getAABB().getYRange().length()).thenReturn(1.0);

        Level level = mock(Level.class, RETURNS_DEEP_STUBS);
        when(level.getCells().getWidth()).thenReturn(10);
        when(level.getCells().getHeight()).thenReturn(10);

        CollisionComputer ch = new CollisionComputer(level);

        Point targetLocation = new Point(2, -1);
        Point finalLocation = ch.checkLevelBounds(mover, targetLocation);

        assertTrue(targetLocation.getY() < finalLocation.getY());
    }
}
