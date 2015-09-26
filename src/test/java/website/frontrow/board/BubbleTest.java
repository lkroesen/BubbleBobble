package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Tests for testing bubble test.
 */
public class BubbleTest
        extends MoverTest
{
    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorLocationTest()
    {
        Bubble b = new Bubble(super.p, super.m, null);
        assertEquals(super.p, b.getLocation());
    }

    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorMotionTest()
    {
        Bubble b = new Bubble(super.p, super.m, null);
        assertEquals(super.m, b.getMotion());
    }

    /**
     * Capture test.
     */
    @Test
    public void captureTest()
    {
        Enemy enemy = mock(Enemy.class);
        Bubble bubble = new Bubble(new Point(0, 0), new Point(0, 0), null);
        bubble.capture(enemy);

        verify(enemy, times(1)).kill();
        assertSame(enemy, bubble.getContains());
    }

    /**
     * Testing whether the bubble properly changes state after hitting a wall.
     */
    @Test
    public void onHitTest()
    {
        Bubble bubble = new Bubble(new Point(0, 0), new Point(0, 0), null);
        bubble.onWallCollision();

        assertTrue(bubble.isHit());
    }

    /**
     * Test whether escaping from a bubble works.
     */
    @Test
    public void testEscapeFromBubble()
    {
        Bubble bubble = new Bubble(new Point(0, 0), new Point(0, 0), null);
        Enemy enemy = mock(Enemy.class);

        when(enemy.getCaughtTime()).thenReturn(0L);

        bubble.capture(enemy);
        bubble.tick(emptyLevel);

        verify(enemy, times(1)).revive();
    }

    @Override
    public Mover getTestMover(boolean alive, Point start, Point end)
    {
        Bubble bubble = new Bubble(start, end, null);
        if(!alive)
        {
            bubble.kill();
        }

        return bubble;
    }
}
