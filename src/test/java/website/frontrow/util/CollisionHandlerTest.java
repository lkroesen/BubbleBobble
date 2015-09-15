package website.frontrow.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test whether the collisionhandler handles preexisting collisions correctly.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("checkstyle:visibilitymodifier")
public class CollisionHandlerTest
{
    @Mock Player player;
    @Mock Enemy enemy;
    @Mock Bubble bubble;
    /**
     * Prepare all the things.
     */
    ArrayList<Player> emptyPlayer = new ArrayList<>();
    ArrayList<Unit> emptyUnit = new ArrayList<>();
    Grid<Cell> emptyGrid = new Grid<>(0, 0);
    Level simpleLevel = new Level(emptyPlayer, emptyUnit, emptyGrid);
    CollisionHandler simpleCollisionHandler = new CollisionHandler(simpleLevel);

    /**
     * Player with anything test.
     */
    @Test
    public void playerWithAnythingElseTest()
    {
        simpleCollisionHandler.applyCollision(player, enemy);
        simpleCollisionHandler.applyCollision(player, bubble);
        verifyNoMoreInteractions(player, enemy, bubble);
    }

    /**
     * Test whether this is still the case the other way around.
     */
    @Test
    public void playerWithAnythingElseSwappedTest()
    {
        simpleCollisionHandler.applyCollision(enemy, player);
        simpleCollisionHandler.applyCollision(bubble, player);
        verifyNoMoreInteractions(player, enemy, bubble);
    }

    /**
     * Test if enemy and bubble die in this instance.
     */
    @Test
    public void bubbleWithEnemyTest()
    {
        simpleCollisionHandler.applyCollision(bubble, enemy);
        verify(bubble, times(1)).capture(enemy);
    }

    /**
     * Test if enemy and bubble still die when swapped around.
     */
    @Test
    public void bubbleWithEnemySwappedTest()
    {
        simpleCollisionHandler.applyCollision(enemy, bubble);
        verify(bubble, times(1)).capture(enemy);
    }

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
        assertFalse(handler.checkLevelAABB(new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, 0)));
    }

    /**
     * Test AABB level check.
     */
    @Test
    public void dlightlyOccupiedAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionHandler handler = new CollisionHandler(check);
        assertTrue(handler.checkLevelAABB(new AABB(new Point(1.5, 1.5), new Point(2.5, 2.5)), new Point(0, 0)));
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
        assertFalse(handler.checkLevelAABB(new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, -1)));
    }

    /**
     * Test AABB level check without collision.
     */
    @Test
    public void platformMotionDownAABBTest()
    {
        Level check = new Level(emptyPlayer, emptyUnit, new Grid<>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.PLATFORM, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3));
        CollisionHandler handler = new CollisionHandler(check);
        assertTrue(handler.checkLevelAABB(new AABB(new Point(1, 1), new Point(2, 2)), new Point(0, 1)));
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
        assertEquals(new Point(1, 1), handler.findNextPosition(player));
    }

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
        assertEquals(new Point(1, 2), handler.findNextPosition(player));
    }
}
