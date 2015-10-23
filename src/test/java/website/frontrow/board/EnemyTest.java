package website.frontrow.board;

import org.junit.Test;

import website.frontrow.game.GameConstants;
import website.frontrow.util.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test the enemy class.
 */
public class EnemyTest
        extends MoverTest
{
    /**
     * Test the constructor of the Enemy class.
     */
    @Test
    public void constructorTest()
    {
        Enemy e = new Enemy(super.p, null);
        assertEquals(e.getLocation(), super.p);
    }

    /**
     * Test whether the caught time is returned properly.
     */
    @Test
    public void getChaughtTimeTest()
    {
        Enemy e = new Enemy(super.p, null);

        assertEquals(Enemy.TIME_TO_ESCPAPE, e.getCaughtTime());
    }

    @Override
    public Mover getTestMover(boolean alive, Point start, Point end)
    {
        Enemy e = new Enemy(start, null);
        e.setMotion(end);
        if(!alive)
        {
            e.kill();
        }

        return e;
    }
    
    /**
     * Test the tick method.
     */
    @Override
    public void testTick()
    {
        Mover u = getTestMover(true, new Point(0, 0), new Point(GameConstants.TICKS_PER_SEC, 0));

        u.tick(emptyLevel);
        
        //An enemy might move
        assertEquals(0.25, u.getLocation().getX(), 2);
    }
    
    /**
     * Tests the setter for wallCollision.
     */
    @Test
    public void testSetWallColision()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertFalse(enemy.getWallCollision());

        enemy.setWallCollision(true);
        assertTrue(enemy.getWallCollision());
    }
    
    /**
     * Tests the function onWallCollision.
     */
    @Test
    public void testOnWallColision()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertFalse(enemy.getWallCollision());

        enemy.onWallCollision();
        assertTrue(enemy.getWallCollision());
    }
    
    /**
     * Tests the getter for tickCounter.
     */
    @Test
    public void testGetTickCounter()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertEquals(enemy.getTickCounter(), 0);
    }
    
    /**
     * Tests the setter for tickCounter.
     */
    @Test
    public void testSetTickCounter()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertEquals(enemy.getTickCounter(), 0);

        enemy.setTickCounter(10);
        assertEquals(enemy.getTickCounter(), 10);
    }
    
    /**
     * Tests the getter for random.
     */
	@Test
    public void testGetRandom()
    {
        Enemy enemy = new Enemy(super.p, null);
        // Not assertEquals, because that apparently doesn't 
        // accept two floats.
        assertTrue(enemy.getRandom() == 1.00f);
    }
	
	/**
     * Tests the setter for random.
     */
	@Test
    public void testSetRandom()
    {
        Enemy enemy = new Enemy(super.p, null);
        // Not assertEquals, because that apparently doesn't 
        // accept two floats.
        assertTrue(enemy.getRandom() == 1.00f);

        enemy.setRandom(2.00f);
        assertTrue(enemy.getRandom() == 2.00f);
    }
	
	/**
     * Tests the getter for lastWall.
     */
    @Test
    public void testGetLastWall()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertFalse(enemy.getLastWall());
    }
	
	/**
     * Tests the setter for lastWall.
     */
    @Test
    public void testSetLastWall()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertFalse(enemy.getLastWall());

        enemy.setLastWall(true);
        assertTrue(enemy.getLastWall());
    }
    
    /**
     * Tests the function getWorth().
     */
    @Test
    public void testGetWorth()
    {
        Enemy enemy = new Enemy(super.p, null);
        // DEFAULT_ENEMY_VALUE = 50.
        assertEquals(enemy.getWorth(), 50);
    }
    
    /**
     * Tests the getter for Mover player in enemy.
     */
    @Test
    public void testGetPlayer()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertNull(enemy.getPlayer());
    }
    
    /**
     * Tests the setter for Mover player in enemy.
     */
    @Test
    public void testSetPlayer()
    {
        Enemy enemy = new Enemy(super.p, null);
        assertNull(enemy.getPlayer());

        Player player = new Player(new Point(0, 0), null);
        enemy.setPlayer((Player) player);
        assertEquals(enemy.getPlayer(), (Mover) player);
    }
}
