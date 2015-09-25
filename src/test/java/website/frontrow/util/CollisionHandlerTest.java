package website.frontrow.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Test the CollisionHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class CollisionHandlerTest
{
    @Mock private Player player;
    @Mock private Enemy enemy;
    @Mock private Bubble bubble;
    private CollisionHandler collisionHandler = new CollisionHandler();

    /**
     * Test the collision between a player and a normal bubble.
     */
    @Test
    public void playerNormalBubbleTest()
    {
        collisionHandler.applyCollision(player, bubble);
        verify(bubble).isHit();
        verifyNoMoreInteractions(player, bubble);
    }

    /**
     * Test the collision between a player and an hit bubble.
     */
    @Test
    public void playerHitBubbleTest()
    {
        when(bubble.isHit()).thenReturn(true);
        collisionHandler.applyCollision(player, bubble);
        verify(bubble).isHit();
        verify(bubble).kill();
        verifyNoMoreInteractions(player, bubble);
    }


    /**
     * Test if a player hit by an enemy gets killed.
     */
    @Test
    public void playerHitEnemyTest()
    {
        collisionHandler.applyCollision(player, enemy);
        verify(player, times(1)).onEnemyCollision();
    }

    /**
     * Test if a player hit by an enemy gets killed.
     */
    @Test
    public void enemyHitPlayerTest()
    {
        collisionHandler.applyCollision(enemy, player);
        verify(player, times(1)).onEnemyCollision();
    }

    /**
     * Test if enemy and bubble die in this instance.
     */
    @Test
    public void bubbleWithEnemyTest()
    {
        collisionHandler.applyCollision(bubble, enemy);
        verify(bubble, times(1)).capture(enemy);
    }

    /**
     * Test if enemy and bubble still die when swapped around.
     */
    @Test
    public void bubbleWithEnemySwappedTest()
    {
        collisionHandler.applyCollision(enemy, bubble);
        verify(bubble, times(1)).capture(enemy);
    }
}
