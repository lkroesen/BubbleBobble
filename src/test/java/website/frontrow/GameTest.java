package website.frontrow;

import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.level.Level;
import website.frontrow.util.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for Game.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest
{
    @SuppressWarnings("visibilitymodifier")
    private ArrayList<Level> levels = new ArrayList<>();
    @Mock private Level l;
    @Mock private Point po;

    /**
     * Test whether starting the game changes the running state.
     */
    @Test
    public void testStart()
    {
        levels.add(l);

        Game game = new Game(levels, null);
        game.start();

        assertTrue(game.isRunning());
    }

    /**
     * Test whether the game starts stopped (or paused).
     */
    @Test
    public void testStartStopped()
    {
        levels.add(l);

        Game game = new Game(levels, null);
        assertFalse(game.isRunning());
    }

    /**
     * Test whether calling stop stops the game.
     */
    @Test
    public void testStop()
    {
        levels.add(l);

        Game game = new Game(levels, null);
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
        levels.add(l);

        Game game = new Game(levels, null);
        assertEquals(0, game.getScore());
    }

    /**
     * Test highscore setter.
     */
    @Test
    public void testSetScore()
    {
        levels.add(l);

        Game game = new Game(levels, null);
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
        levels.add(l);

        Game game = new Game(levels, null);
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
        levels.add(l);

        Game game = new Game(levels, null);
        game.start();
        assertTrue(game.isRunning());
        game.tick();
        verify(levels.get(0), times(1)).tick();
    }
    
    @Test
    public void testGameOverSetLives()
    {
    	levels.add(l);
    	Game game = new Game(levels, null);
    	game.start();
    	assertTrue(game.isRunning());
    	
    	Player p = new Player(po);
    	p.setLives(0);
    	ArrayList<Player> list = game.getPlayers();
    	if(list != null)
    	{
    		game.setPlayer(p, 0);
    	}
    	else
    	{
    		game.setPlayers(list);
    	}
    	game.tick();
    	assertFalse(game.isRunning());
    	
    }
}