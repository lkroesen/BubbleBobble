package website.frontrow.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test the RealCollisionHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class RealCollisionHandlerTest
{
        @Mock Player player;
        @Mock Enemy enemy;
        @Mock Bubble bubble;
        RealCollisionHandler realCollisionHandler = new RealCollisionHandler();
    
        /**
         * Test the collision between a player and a normal bubble.
         */
        @Test
        public void playerNormalBubbleTest()
        {
            realCollisionHandler.applyCollision(player, bubble);
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
            realCollisionHandler.applyCollision(player, bubble);
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
            realCollisionHandler.applyCollision(player, enemy);
            verify(player, times(1)).kill();
        }

        /**
         * Test if enemy and bubble die in this instance.
         */
        @Test
        public void bubbleWithEnemyTest()
        {
            realCollisionHandler.applyCollision(bubble, enemy);
            verify(bubble, times(1)).capture(enemy);
        }

        /**
         * Test if enemy and bubble still die when swapped around.
         */
        @Test
        public void bubbleWithEnemySwappedTest()
        {
            realCollisionHandler.applyCollision(enemy, bubble);
            verify(bubble, times(1)).capture(enemy);
        }

}