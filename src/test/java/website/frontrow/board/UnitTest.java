package website.frontrow.board;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests for the Unit Class.
 */
public class UnitTest
{
    @Test
    public void testConstructor()
    {
        Unit u = new Unit( (byte) 1 );
        assertSame( (byte) 1, u.getLives() );
    }

    @Test
    public void testSetter()
    {
        Unit u = new Unit( (byte) -1 );
        u.setLives( (byte) 2 );

        assertSame( (byte) 2, u.getLives() );
    }

    @Test
    public void testAliveGetterNegative()
    {
        Unit u = new Unit( (byte) 0 );
        assertFalse(u.getAlive());
    }

    @Test
    public void testAliveGetterPositive()
    {
        Unit u = new Unit( (byte) 1 );
        assertTrue(u.getAlive());
    }

    @Test
    public void testLoseLifeAtZero()
    {
        Unit u = new Unit( (byte) 0 );
        u.loseLife();
        assertSame( (byte) 0, u.getLives() );
    }

    @Test
    public void testLoseLifeAtOne()
    {
        Unit u = new Unit( (byte) 1 );
        u.loseLife();
        assertSame( (byte) 0, u.getLives() );
    }

    @Test
    public void testLoseLifeAtTwo()
    {
        Unit u = new Unit( (byte) 2 );
        u.loseLife();
        assertSame( (byte) 1, u.getLives() );
    }

    @Test
    public void testLoseLifeAtNegOne()
    {
        Unit u = new Unit( (byte) -1 );
        u.loseLife();
        assertSame( (byte) 0, u.getLives() );
    }

    @Test
    public void testGainLifeAtZero()
    {
        Unit u = new Unit( (byte) 0);
        u.gainLife();
        assertSame( (byte) 1, u.getLives() );
    }

    @Test
    public void testGainLifeAtOne()
    {
        Unit u = new Unit( (byte) 1);
        u.gainLife();
        assertSame( (byte) 2, u.getLives() );
    }

    @Test
    public void testGainLifeAtNegOne()
    {
        Unit u = new Unit( (byte) -1);
        u.gainLife();
        assertSame( (byte) 1, u.getLives() );
    }
}


