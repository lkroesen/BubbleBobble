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
}
