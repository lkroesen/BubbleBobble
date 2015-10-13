package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;

/**
 * Player test.
 */
public class PlayerTest
        extends MoverTest
{
    /**
     * Test the constructor of Player.
     */
    @Test
    public void testConstructor()
    {
        Player pl = new Player(super.p, null);
        assertEquals(pl.getLocation(), super.p);
    }

    /**
     * Test the score adder of Player.
     */
    @Test
    public void testScore()
    {
        Player pl = new Player(super.p, null);
        pl.addScore(42);
        assertEquals(pl.getScore(), 42);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testAddLife()
    {
        Player p = new Player(super.p, null);

        assertEquals(p.getLives(), 3);
        p.addLife();
        assertEquals(p.getLives(), 4);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testSetLife()
    {
        Player p = new Player(super.p, null);

        assertEquals(p.getLives(), 3);
        p.setLives(1);
        assertEquals(p.getLives(), 1);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testLoseLife()
    {
        Player p = new Player(super.p, null);

        assertEquals(p.getLives(), 3);
        p.loseLife();
        assertEquals(p.getLives(), 2);
    }

    @Override
    public Mover getTestMover(boolean alive, Point start, Point motion)
    {
        Player player = new Player(start, null);
        if(!alive)
        {
            player.kill();
        }
        player.setMotion(motion);

        return player;
    }
    
    /**
     * Tests that addScore() doesn't affect the scores of
     * other players when called on a certain player
     */
    @Test
    public void testScore2P()
    {
        Player p1 = new Player(super.p, null);
        Player p2 = new Player(super.p, null);
        
        p1.addScore(42);
        assertEquals(p1.getScore(), 42);
        assertEquals(p2.getScore(), 0);
        
        p2.addScore(29);
        assertEquals(p2.getScore(), 29);
        assertEquals(p1.getScore(), 42);
    }

    /**
     * Tests that addLife() doesn't affect the scores of
     * other players when called on a certain player
     */
    @Test
    public void testAddLife2P()
    {
        Player p1 = new Player(super.p, null);
        Player p2 = new Player(super.p, null);

        assertEquals(p1.getLives(), 3);
        assertEquals(p2.getLives(), 3);
        
        p1.addLife();
        assertEquals(p1.getLives(), 4);
        assertEquals(p2.getLives(), 3);
        
        p2.addLife();
        assertEquals(p1.getLives(), 4);
        assertEquals(p2.getLives(), 4);
    }
    
    /**
     * Tests that setLives() doesn't affect the scores of
     * other players when called on a certain player
     */
    @Test
    public void testSetLife2P()
    {
        Player p1 = new Player(super.p, null);
        Player p2 = new Player(super.p, null);

        assertEquals(p1.getLives(), 3);
        assertEquals(p2.getLives(), 3);
        
        p1.setLives(1);
        assertEquals(p1.getLives(), 1);
        assertEquals(p2.getLives(), 3);
        
        p2.setLives(5);
        assertEquals(p1.getLives(), 1);
        assertEquals(p2.getLives(), 5);
    }
    
    /**
     * Tests that loseLife() doesn't affect the scores of
     * other players when called on a certain player
     */
    @Test
    public void testLoseLife2P()
    {
    	Player p1 = new Player(super.p, null);
    	Player p2 = new Player(super.p, null);

        assertEquals(p1.getLives(), 3);
        assertEquals(p2.getLives(), 3);
        
        p1.loseLife();
        assertEquals(p1.getLives(), 2);
        assertEquals(p2.getLives(), 3);
        
        p2.loseLife();
        assertEquals(p1.getLives(), 2);
        assertEquals(p2.getLives(), 2);
    }
}
