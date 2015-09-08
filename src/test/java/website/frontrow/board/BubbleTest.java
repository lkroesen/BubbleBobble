package website.frontrow.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Tests for testing bubble test
 */
public class BubbleTest extends UnitTest
{
    /**
     * Test the constructor of Bubble
     */
    @Test
    public void ConstructorLocationTest()
    {
        Bubble b = new Bubble(super.p,super.m);
        assertEquals(super.p, b.getLocation());
    }

    /**
     * Test the constructor of Bubble
     */
    @Test
    public void ConstructorMotionTest()
    {
        Bubble b = new Bubble(super.p,super.m);
        assertEquals(super.m, b.getMotion());
    }

    /**
     * Test contains
     */
    @Test
    public void ContainsTest()
    {
        Bubble b = new Bubble(super.p,super.m);
        Enemy e = new Enemy(super.p);
        b.setContains(e);
        assertEquals(b.getContains(), e);
    }
}
