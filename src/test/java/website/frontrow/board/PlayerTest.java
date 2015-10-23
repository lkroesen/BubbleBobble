package website.frontrow.board;

import org.junit.Test;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Player test.
 */
public class PlayerTest
        extends MoverTest
{
    private static final int DEFAULT_ENEMY_WORTH = 50;

    /**
     * Test the constructor of Player.
     */
    @Test
    public void testConstructor()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);
        assertEquals(player.getLocation(), super.FIRST_TEST_POINT);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testAddLife()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);

        assertEquals(player.getLives(), 3);
        player.addLife();
        assertEquals(player.getLives(), 4);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testSetLife()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);

        assertEquals(player.getLives(), 3);
        player.setLives(1);
        assertEquals(player.getLives(), 1);
    }

    /**
     * Tests the addLife function.
     */
    @Test
    public void testLoseLife()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);

        assertEquals(player.getLives(), 3);
        player.loseLife();
        assertEquals(player.getLives(), 2);
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
     * Test RemoveObserver.
     */
    @Test
    public void testRemoveObserver()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);
        player.addObserver(null);

        assertTrue(player.getObservers().contains(null));

        player.removeObserver(null);
        assertFalse(player.getObservers().contains(null));
    }

    /**
     * Test that increase score works.
     */
    @Test
    public void testIncreaseScore()
    {
        Player player = new Player(super.FIRST_TEST_POINT, null);
        player.increaseScoreWith(DEFAULT_ENEMY_WORTH);
        assertEquals(player.getScore(), DEFAULT_ENEMY_WORTH);
    }
}
