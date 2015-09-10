package website.frontrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.level.Level;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for Game
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock Level level;

    /**
     * Test whether starting the game changes the running state.
     */
    @Test
    public void testStart()
    {
        Game game = new Game(level);
        game.start();

        assertTrue(game.isRunning());
    }

    /**
     * Test whether the game starts stopped (or paused).
     */
    @Test
    public void testStartStopped()
    {
        Game game = new Game(level);
        assertFalse(game.isRunning());
    }

    /**
     * Test whether calling stop stops the game.
     */
    @Test
    public void testStop()
    {
        Game game = new Game(level);
        game.start();
        game.stop();
        assertFalse(game.isRunning());
    }

    /**
     * Test highscore getter.
     */
    @Test
    public void testGetHighscore()
    {
        Game game = new Game(level);
        assertEquals(0, game.getHighscore());
    }

    /**
     * Test highscore setter.
     */
    @Test
    public void testSetHighscore()
    {
        Game game = new Game(level);
        game.setHighscore(15);
        assertEquals(15, game.getHighscore());
        game.setHighscore(42);
        assertEquals(42, game.getHighscore());
    }

    /**
     * Test tick function while not running.
     */
    @Test
    public void testTick1()
    {
        Game game = new Game(level);
        assertFalse(game.isRunning());
        game.tick();
        verify(level, never()).tick();
    }

    /**
     * Test Tick function while running.
     */
    @Test
    public void testTick2()
    {
        Game game = new Game(level);
        game.start();
        assertTrue(game.isRunning());
        game.tick();
        verify(level, times(1)).tick();
    }
}