package website.frontrow.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Square;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

/**
 * Created by lkroesen on 02/09/2015.
 */
public class CollisionHandlertest
{
    private Grid<Square> grid;
    private CollisionHandler ch;

    @Before
    public void init()
    {
        grid = new Grid<Square>(2, 2);
        ch = new CollisionHandler(grid);
    }

    @After
    public void teardown()
    {
        grid = null;
        ch = null;
    }

    ///////////////////////////////////////////
    //        COLLISION_APPLYER TESTS        //
    ///////////////////////////////////////////
    /**
     * Test when the player collides with a Bubble, from a higher Y
     * that the enemy in the bubble loses a life.
     */
    @Test
    public void test_Contained_Bubble_From_Higher_Y()
    {
        Player  p = new Player  ((byte)3);
        Bubble  b = new Bubble  ((byte)1);
        Enemy   e = new Enemy   ((byte)1);

        b.setContains(e);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(b);

        grid.set(1, 1, s1);
        grid.set(1, 0, s2);

        ch.checkCollision(1, 1, 1, 0);

        assertFalse(e.getAlive());
    }

    /**
     * Test that when a bubble floats up to the player and hits
     * the player from a Y that is lower, that the enemy inside
     * the bubble dies (loses 1 life).
     */
    @Test
    public void test_Bubble_Hits_Player_From_Lower_Y()
    {
        Player  p = new Player  ((byte)3);
        Bubble  b = new Bubble  ((byte)1);
        Enemy   e = new Enemy   ((byte)1);

        b.setContains(e);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(b);
        s2.setOccupied(p);

        grid.set(1, 0, s1);
        grid.set(1, 1, s2);

        ch.checkCollision(1, 0, 1, 1);

        assertFalse(e.getAlive());
    }

    /**
     * Test that the player loses a life when hit by an enemy.
     */
    @Test
    public void test_Player_Collides_With_Enemy()
    {
        Player  p = new Player  ((byte)3);
        Enemy   e = new Enemy   ((byte)1);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(e);

        grid.set(1, 1, s1);
        grid.set(1, 0, s2);

        byte oldLives = p.getLives();

        ch.checkCollision(1, 1, 1, 0);

        assertNotSame(oldLives, p.getLives());
    }

    /**
     * Test that if an enemy collides with a player,
     * results in the player losing a life.
     */
    @Test
    public void test_Enemy_Collides_With_Player()
    {
        Enemy   e = new Enemy   ((byte)1);
        Player  p = new Player  ((byte)3);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(e);
        s2.setOccupied(p);

        grid.set(1, 1, s1);
        grid.set(1, 0, s2);

        byte oldLives = p.getLives();

        ch.checkCollision(1, 1, 1, 0);

        assertNotSame(oldLives, p.getLives());
    }

    ///////////////////////////////////////////
    //          NULL COLLISION TESTS         //
    ///////////////////////////////////////////

    /**
     * Test if Null collides with Null that an exception is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullNullCollision()
    {
        Square s = new Square();

        s.setOccupied(null);

        grid.set(0,0,s);
        grid.set(1, 1, s);

        ch.checkCollision(0, 0, 1, 1);
    }

    /**
     * Test if Null collides with Player that an exception is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullPlayerCollision()
    {
        Player u = new Player((byte)3);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(null);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        ch.checkCollision(0, 0, 1, 1);
    }

    /**
     * Test if Null collides with Enemy that an exception is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullEnemyCollision()
    {
        Enemy u = new Enemy((byte)1);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(null);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        ch.checkCollision(0, 0, 1, 1);
    }

    /**
     * Test if Null collides with Enemy that an exception is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBubbleCollision()
    {
        Bubble u = new Bubble((byte)1);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(null);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        ch.checkCollision(0, 0, 1, 1);
    }

    /**
     * Test if Null collides with Unit that an exception is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullUnitCollision()
    {
        Unit u = new Unit((byte)1);

        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(null);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        ch.checkCollision(0, 0, 1, 1);
    }

    ///////////////////////////////////////////
    //        PLAYER COLLISION TESTS         //
    ///////////////////////////////////////////
    /**
     * Test if Player collides with Player that True is returned.
     */
    @Test
    public void testPlayerPlayerCollision()
    {
        Player p = new Player((byte)3);
        Square s = new Square();
        s.setOccupied(p);
        grid.set(0,0,s);
        grid.set(1,1,s);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if a Player collides with null that False is returned.
     */
    @Test
    public void testPlayerNullCollision()
    {
        Player p = new Player((byte)3);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(null);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertFalse(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Player collides with Unit that True is returned.
     */
    @Test
    public void testPlayerUnitCollision()
    {
        Player p = new Player((byte)3);
        Unit u = new Unit((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Player collides with Enemy that True is returned.
     */
    @Test
    public void testPlayerEnemyCollision()
    {
        Player p = new Player((byte)3);
        Enemy e = new Enemy((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(e);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Player collides with Bubble that True is returned.
     */
    @Test
    public void testPlayerBubbleCollision()
    {
        Player p = new Player((byte)3);
        Bubble b = new Bubble((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(b);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    ///////////////////////////////////////////
    //          UNIT COLLISION TESTS         //
    ///////////////////////////////////////////

    /**
     * Test if Unit collides with Unit that True is returned.
     */
    @Test
    public void testUnitUnitCollision()
    {
        Unit p = new Unit((byte)3);
        Square s = new Square();
        s.setOccupied(p);
        grid.set(0,0,s);
        grid.set(1,1,s);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if a Unit collides with null that False is returned.
     */
    @Test
    public void testUnitNullCollision()
    {
        Unit p = new Unit((byte)3);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(null);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertFalse(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Unit collides with Player that True is returned.
     */
    @Test
    public void testUnitPlayerCollision()
    {
        Player p = new Player((byte)3);
        Unit u = new Unit((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Unit collides with Enemy that True is returned.
     */
    @Test
    public void testUnitEnemyCollision()
    {
        Unit p = new Unit((byte)3);
        Enemy e = new Enemy((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(e);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Unit collides with Bubble that True is returned.
     */
    @Test
    public void testUnitBubbleCollision()
    {
        Unit p = new Unit((byte)3);
        Bubble b = new Bubble((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(b);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    ///////////////////////////////////////////
    //        BUBBLE COLLISION TESTS         //
    ///////////////////////////////////////////

    /**
     * Test if Bubble collides with Bubble that True is returned.
     */
    @Test
    public void testBubbleBubbleCollision()
    {
        Bubble p = new Bubble((byte)3);
        Square s = new Square();
        s.setOccupied(p);
        grid.set(0,0,s);
        grid.set(1,1,s);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if a Bubble collides with null that False is returned.
     */
    @Test
    public void testBubbleNullCollision()
    {
        Bubble p = new Bubble((byte)3);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(null);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertFalse(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Bubble collides with Unit that True is returned.
     */
    @Test
    public void testBubbleUnitCollision()
    {
        Bubble p = new Bubble((byte)3);
        Unit u = new Unit((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Bubble collides with Enemy that True is returned.
     */
    @Test
    public void testBubbleEnemyCollision()
    {
        Bubble p = new Bubble((byte)3);
        Enemy e = new Enemy((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(e);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Bubble collides with Player that True is returned.
     */
    @Test
    public void testBubblePlayerCollision()
    {
        Player p = new Player((byte)3);
        Bubble b = new Bubble((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(b);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    ///////////////////////////////////////////
    //        ENEMY COLLISION TESTS          //
    ///////////////////////////////////////////
    /**
     * Test if Enemy collides with Enemy that True is returned.
     */
    @Test
    public void testEnemyEnemyCollision()
    {
        Enemy p = new Enemy((byte)3);
        Square s = new Square();
        s.setOccupied(p);
        grid.set(0,0,s);
        grid.set(1,1,s);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if a Enemy collides with null that False is returned.
     */
    @Test
    public void testEnemyNullCollision()
    {
        Enemy p = new Enemy((byte)3);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(null);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertFalse(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Enemy collides with Unit that True is returned.
     */
    @Test
    public void testEnemyUnitCollision()
    {
        Enemy p = new Enemy((byte)3);
        Unit u = new Unit((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(u);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Enemy collides with Player that True is returned.
     */
    @Test
    public void testEnemyPlayerCollision()
    {
        Player p = new Player((byte)3);
        Enemy e = new Enemy((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(e);

        grid.set(0,0,s2);
        grid.set(1, 1, s1);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }

    /**
     * Test if Enemy collides with Bubble that True is returned.
     */
    @Test
    public void testEnemyBubbleCollision()
    {
        Enemy p = new Enemy((byte)3);
        Bubble b = new Bubble((byte)1);
        Square s1 = new Square();
        Square s2 = new Square();

        s1.setOccupied(p);
        s2.setOccupied(b);

        grid.set(0,0,s1);
        grid.set(1, 1, s2);

        assertTrue(ch.checkCollision(0, 0, 1, 1));
    }
}
