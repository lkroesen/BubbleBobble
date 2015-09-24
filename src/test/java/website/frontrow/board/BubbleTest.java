package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
     * Test contains.
     */
    @Test
    public void containsTest()
    {
        Bubble b = new Bubble(super.p, super.m, null);
        Enemy e = new Enemy(super.p, null);
        b.setContains(e);
        assertEquals(b.getContains(), e);
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
