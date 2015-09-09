package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;

/**
 * Player test.
 */
public class PlayerTest extends UnitTest
{
    /**
     * Test the constructor of Player.
     */
    @Test
    public void testConstructor()
    {
        Player pl = new Player(super.p);
        assertEquals(pl.getLocation(), super.p);
    }

    /**
     * Test the score adder of Player.
     */
    @Test
    public void testScore()
    {
        Player pl = new Player(super.p);
        pl.addScore(42);
        assertEquals(pl.getScore(), 42);
    }

    @Override
    public Unit getTestUnit(boolean alive, Point start, Point motion) {
        Player player = new Player(start);
        player.setMotion(motion);
        if(!alive)
        {
            player.kill();
        }
        return player;
    }
}
