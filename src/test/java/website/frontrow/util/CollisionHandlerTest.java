package website.frontrow.util;

import org.junit.Test;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test whether the collisionhandler handles preexisting collisions correctly.
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public class CollisionHandlerTest
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
        CollisionHandler handler = new CollisionHandler(check);
        assertFalse(handler.checkLevelAABB(
                new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, 0)
        ));
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
        CollisionHandler handler = new CollisionHandler(check);
        assertTrue(handler.checkLevelAABB(
                new AABB(new Point(1.5, 1.5), new Point(2.5, 2.5)), new Point(0, 0)
        ));
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
        CollisionHandler handler = new CollisionHandler(check);
        assertFalse(handler.checkLevelAABB(
                new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, -1)
        ));
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
        CollisionHandler handler = new CollisionHandler(check);
        assertTrue(handler.checkLevelAABB(
                new AABB(new Point(1, 1.1), new Point(2, 2.1)), new Point(0, 1)
        ));
    }

    /**
     * Test getNextPosition in a terrible box of terribleness.
     */
    @Test
    public void getNextPositionTestRetricted()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionHandler handler = new CollisionHandler(check);
        Player player = new Player(new Point(1, 1));
        player.setMotion(new Point(1, GameConstants.TICKS_PER_SEC));
        Collision c = handler.findNextPosition(player);
        assertEquals(new Point(1, 1), c.getPoint());
        assertTrue(c.isCollided());
    }

    /**
     * Some special box of terribleness.
     */
    @Test
    public void getNextPositionTestSlightlyRetricted()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 4));
        CollisionHandler handler = new CollisionHandler(check);
        Player player = new Player(new Point(1, 1));
        player.setMotion(new Point(0, 3 * GameConstants.TICKS_PER_SEC));
        Collision c = handler.findNextPosition(player);
        assertEquals(new Point(1, 2), c.getPoint());
        assertTrue(c.isCollided());
    }
}
