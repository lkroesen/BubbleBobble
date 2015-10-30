package website.frontrow.board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Tests for testing bubble test.
 */
public class BubbleTest
        extends MoverTest
{
	private static final int TIME_FLOAT_UPWARDS = 50;
	private static final int TIME_ALMOST_KILL = 499;
	private static final Point FLOAT_UP_MOTION = new Point(0, -2);

    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorLocationTest()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.secondTestPoint, null);
        assertEquals(super.FIRST_TEST_POINT, bubble.getLocation());
    }

    /**
     * Test the constructor of Bubble.
     */
    @Test
    public void constructorMotionTest()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.secondTestPoint, null);
        assertEquals(super.secondTestPoint, bubble.getMotion());
    }

    /**
     * Capture test.
     */
    @Test
    public void captureTest()
    {
        Enemy enemy = mock(Enemy.class);
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);
        bubble.capture(enemy);

        verify(enemy, times(1)).kill();
        assertSame(enemy, bubble.getContains());
    }

    /**
     * Testing whether the bubble properly changes state after hitting a wall.
     */
    @Test
    public void onHitTest()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);
        bubble.onWallCollision();

        assertTrue(bubble.isHit());
    }

    /**
     * Test whether escaping from a bubble works.
     */
    @Test
    public void testEscapeFromBubble()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);
        Enemy enemy = mock(Enemy.class);

        when(enemy.getCaughtTime()).thenReturn(0L);

        bubble.capture(enemy);
        bubble.tick(emptyLevel);

        verify(enemy, times(1)).revive();
    }

    @Override
    public Mover getTestMover(boolean alive, Point start, Point end)
    {
        Bubble bubble = new Bubble(start, end, null);
        if(!alive)
        {
            bubble.kill();
        }

        return bubble;
    }
    
    /**
     * Tests that the bubble floats upwards at 50 ticks.
     */
    @Test
    public void testUpwardsFloating()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);
    	
        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Unit> unitList = new ArrayList<>();
        List<Cell> items = new ArrayList<>();
        items.add(Cell.EMPTY);
        Grid<Cell> cells = new Grid<>(items, 1, 1);
        Level level = new Level(playerList, unitList, cells);
    	
        for(int i = 0; i < TIME_FLOAT_UPWARDS; i++)
        {
            bubble.tick(level);
        }
    	
        assertTrue(bubble.isHit());
        assertEquals(bubble.getMotion(), FLOAT_UP_MOTION);
    }
    
    /**
     * Tests that the bubble pops at 500 ticks.
     */
	@Test
	public void testKillEmpty() 
	{
	    Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);

	    ArrayList<Player> playerList = new ArrayList<>();
	    ArrayList<Unit> unitList = new ArrayList<>();
	    List<Cell> items = new ArrayList<>();
	    items.add(Cell.EMPTY);
	    Grid<Cell> cells = new Grid<>(items, 1, 1);
	    Level level = new Level(playerList, unitList, cells);

	    assertTrue(bubble.isAlive());

	    for (int i = 0; i < TIME_ALMOST_KILL; i++) 
	    {
	        bubble.tick(level);
	    }

	    assertTrue(bubble.isHit());
	    assertTrue(bubble.isAlive());

	    bubble.tick(level);

	    assertFalse(bubble.isAlive());
	}
    
	/**
	 * Tests that an upwards floating bubble doesn't interact with an enemy when
	 * colliding.
	 */
    @Test
    public void testFloatingUpwardsEnemyCollision()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, super.FIRST_TEST_POINT, null);
    	
        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Unit> unitList = new ArrayList<>();
        List<Cell> items = new ArrayList<>();
        items.add(Cell.EMPTY);
        Grid<Cell> cells = new Grid<>(items, 1, 1);
        Level level = new Level(playerList, unitList, cells);
    	
        for(int i = 0; i < TIME_FLOAT_UPWARDS; i++)
        {
            bubble.tick(level);
        }
    	
        assertTrue(bubble.isHit());
        assertEquals(bubble.getMotion(), FLOAT_UP_MOTION);
        
        Enemy enemy = mock(Enemy.class);
        enemy.setLocation(bubble.getLocation());
    	
        assertEquals(bubble.getContains(), null);
    }

    /**
     * Test that setTimeEmpty works properly.
     */
    @Test
    public void testSetTimeEmpty()
    {
        Bubble bubble = new Bubble(super.FIRST_TEST_POINT, null, null);
        bubble.setTimeEmpty(TIME_FLOAT_UPWARDS);
        assertEquals(bubble.getTimeEmpty(), TIME_FLOAT_UPWARDS);
    }
}
