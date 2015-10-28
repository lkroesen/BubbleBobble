package website.frontrow.game;

import org.junit.Before;
import website.frontrow.level.Level;
import website.frontrow.util.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * Test class for Game.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest
{
    @SuppressWarnings("visibilitymodifier")
    private ArrayList<Level> levels = new ArrayList<>();
    @Mock private Level level;
    @Mock private Level level2;
    private Game game;

    /**
     * Set up for testing.
     */
    @Before
    public void setUp()
    {
        when(level.duplicate()).thenReturn(level);
        when(level2.duplicate()).thenReturn(level2);
        levels.add(level);
        levels.add(level2);

        game = new Game(levels);
    }
    @Mock private Point point;

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

    /**
     * Test if levelWon works as expected.
     */
    @Test
    public void testLevelWon()
    {
        game.levelWon();
        assertEquals(level2, game.getLevel());
    }

    /**
     * Test if levelLost works as expected.
     */
    @Test
    public void testLevelLost()
    {
        game.levelLost();
        assertFalse(game.isRunning());
        verify(level, times(1)).duplicate();
        verify(level2, never()).duplicate();
    }

    /**
     * Test if restart works as expected.
     */
    @Test
    public void testRestart()
    {
        game.nextLevel();
        game.getPlayers().get(0).increaseScoreWith(100);
        verify(level, times(1)).duplicate();
        verify(level2, times(1)).duplicate();

        game.restart();

        verify(level, times(2)).duplicate();
        assertFalse(game.isRunning());
        assertEquals(game.getPlayers().get(0).getScore(), 0);
    }
    
	/**
	 * Tests if nextLevel() only adds a life to a player when the right
	 * conditions are met.
	 */
    @Test
    public void testAddLifeAtNextLevel()
    {
    	assertEquals(game.getPlayers().get(0).getLives(), 3);
    	
    	game.getPlayers().get(0).setLives(2);
    	game.nextLevel();
    	assertEquals(game.getPlayers().get(0).getLives(), 3);
    	
    	game.getPlayers().get(0).setLives(1);
    	game.nextLevel();
    	assertEquals(game.getPlayers().get(0).getLives(), 2);
    	
    	game.getPlayers().get(0).setLives(0);
    	game.nextLevel();
    	assertEquals(game.getPlayers().get(0).getLives(), 0);
    }

}