package website.frontrow.board;

import static org.junit.Assert.assertSame;

/**
 * Tests for the Player Class.
 */
public class PlayerTest extends UnitTest
{
    @Override
    public void testConstructor()
    {
        Player p = new Player( (byte) 42);
        assertSame( (byte) 42, p.getLives() );
    }
}
