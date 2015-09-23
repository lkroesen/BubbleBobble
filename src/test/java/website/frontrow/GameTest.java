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
    
    /**
     * Tests that a game over occurs when the player has no lives left.
     */
    @Test
    public void testGameOverSetLives()
    {

    	levels.add(l);
    	Player p = new Player(po);
    	ArrayList<Player> list = new ArrayList<Player>();
    	list.add(p);
    	Game game = new Game(levels, list);
    	game.start();
    	assertTrue(game.isRunning());
    	
    	p.setLives(0);
    	game.tickGO();
    	assertFalse(game.isRunning());
    	
    }
    
    /**
     * Tests that a game over occurs after the player has lost all three lives.
     */
    @Test
    public void testGameOverLoseLife()
    {
    	levels.add(l);
    	Player p = new Player(po);
    	ArrayList<Player> list = new ArrayList<Player>();
    	list.add(p);
    	Game game = new Game(levels, list);
    	game.start();
    	assertTrue(game.isRunning());
    	
    	// Lost a life, but not dead yet
    	p.loseLife();
    	game.tickGO();
    	assertTrue(game.isRunning());
    	
    	// Lost a life, but not dead yet
    	p.loseLife();
    	game.tickGO();
    	assertTrue(game.isRunning());
    	
    	// Lost the last life
    	p.loseLife();
    	game.tickGO();
    	assertFalse(game.isRunning());
    }
    
    @Test
    public void testAddLife()
    {
    	levels.add(l);
    	Player p = new Player(po);
    	ArrayList<Player> list = new ArrayList<Player>();
    	list.add(p);
    	Game game = new Game(levels, list);
    	game.start();
    	assertTrue(game.isRunning());
    	
    	p.addLife();
    	// The player now has four lives.
    	for(int i = 0; i < 3; i++)
    	{
    		p.loseLife();
    	}
    	game.tickGO();
    	// After losing three lives, the player should have one life left.
    	assertTrue(game.isRunning());
    	
    	// Now the player loses his last life.
    	p.loseLife();
    	game.tickGO();
    	assertFalse(game.isRunning());
    }
    
}