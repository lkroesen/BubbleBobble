package website.frontrow.board;

import static org.junit.Assert.assertSame;

/**
 * Tests for the Enemy Class.
 */
public class EnemyTest extends UnitTest
{
    @Override
    public void testConstructor()
    {
        Enemy e = new Enemy( (byte) 25);
        assertSame( (byte) 25, e.getLives() );
    }
}
