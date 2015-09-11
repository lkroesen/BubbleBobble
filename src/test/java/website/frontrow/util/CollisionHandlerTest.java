package website.frontrow.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;

import java.util.ArrayList;

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
}
