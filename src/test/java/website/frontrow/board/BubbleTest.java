package website.frontrow.board;

import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Test for the Bubble Class.
 */
public class BubbleTest extends UnitTest
{
    @Test
    public void testConstructor()
    {
        Bubble b = new Bubble( (byte) 99);
        assertSame( (byte) 99, b.getLives() );
    }

    @Test
    public void testContains()
    {
        Bubble b = new Bubble( (byte) 1);
        Enemy e = new Enemy( (byte) 3);

        b.setContains(e);

        assertSame( (byte) 3, b.getContains().getLives());

    }
}
