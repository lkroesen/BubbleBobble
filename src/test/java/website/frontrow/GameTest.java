package website.frontrow;

import org.junit.Before;
import website.frontrow.game.Game;
import website.frontrow.level.Level;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for Game.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest
{
    @SuppressWarnings("visibilitymodifier")
    private ArrayList<Level> levels = new ArrayList<>();
    @Mock private Level level;
    private Game game;

    @Before
    public void setUp()
    {
        when(level.clone()).thenReturn(level);
        levels.add(level);

        game = new Game(levels);
    }

    /**
     * Test whether starting the game changes the running state.
     */
    @Test
    public void testStart()
    {
        game.start();

        assertTrue(game.isRunning());
    }

    /**
     * Test whether the game starts stopped (or paused).
     */
    @Test
    public void testStartStopped()
    {
        assertFalse(game.isRunning());
    }

    /**
     * Test whether calling stop stops the game.
     */
    @Test
    public void testStop()
    {
        game.start();
        game.stop();
        assertFalse(game.isRunning());
    }

    /**
     * Test highscore getter.
     */
    @Test
    public void testGetScore()
    {
        assertEquals(0, game.getScore());
    }

    /**
     * Test highscore setter.
     */
    @Test
    public void testSetScore()
    {
        game.setScore(15);
        assertEquals(15, game.getScore());
        game.setScore(42);
        assertEquals(42, game.getScore());
    }

    /**
     * Test tick function while not running.
     */
    @Test
    public void testTick1()
    {
        assertFalse(game.isRunning());
        game.tick();
        verify(levels.get(0), never()).tick();
    }

    /**
     * Test Tick function while running.
     */
    @Test
    public void testTick2()
    {
        game.start();
        assertTrue(game.isRunning());
        game.tick();
        verify(levels.get(0), times(1)).tick();
    }
}