package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
/**
 * Tests for testing bubble test.
 */
public class BubbleTest extends UnitTest
{
    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorLocationTest()
    {
        Bubble b = new Bubble(super.p, super.m);
        assertEquals(super.p, b.getLocation());
    }

    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorMotionTest()
    {
        Bubble b = new Bubble(super.p, super.m);
        assertEquals(super.m, b.getMotion());
    }

    /**
     * Test contains.
     */
    @Test
    public void containsTest()
    {
        Bubble b = new Bubble(super.p, super.m);
        Enemy e = new Enemy(super.p);
        b.setContains(e);
        assertEquals(b.getContains(), e);
    }

    @Override
    public Unit getTestUnit(boolean alive, Point start, Point end) {
        Bubble bubble = new Bubble(start, end);
        if(!alive) bubble.kill();
        return bubble;
    }
}
