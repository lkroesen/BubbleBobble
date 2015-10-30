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
    @SuppressWarnings("static-access")
	@Test
    public void constructorTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getLocation(), super.FIRST_TEST_POINT);
    }

    /**
     * Test that an enemy is worth 50 points.
     */
    @SuppressWarnings("static-access")
	@Test
    public void enemyWorthTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getWorth(), 50);
    }

    /**
     * Test that we can set the last wall.
     */
    @SuppressWarnings("static-access")
	@Test
    public void enemySetLastWallTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getLastWall());

        enemy.setLastWall(true);
        assertTrue(enemy.getLastWall());
    }

    /**
     * Test if we can set WallCollision.
     */
    @SuppressWarnings("static-access")
	@Test
    public void enemySetWallCollisionTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getWallCollision());

        enemy.setWallCollision(true);
        assertTrue(enemy.getWallCollision());
    }

    /**
     * Test that onWallCollision works.
     */
    @SuppressWarnings("static-access")
	@Test
    public void enemyOnWallCollisionTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getWallCollision());

        enemy.onWallCollision();
        assertTrue(enemy.getWallCollision());
    }

    /**
     * Test whether the caught time is returned properly.
     */
    @SuppressWarnings("static-access")
	@Test
    public void getChaughtTimeTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);

        assertEquals(Enemy.TIME_TO_ESCPAPE, enemy.getCaughtTime());
    }

    @Override
    public Mover getTestMover(boolean alive, Point start, Point end)
    {
        Enemy enemy = new Enemy(start, null);
        enemy.setMotion(end);
        if(!alive)
        {
            enemy.kill();
        }

        return enemy;
    }
    
    /**
     * Test the tick method.
     */
    @Override
    public void testTick()
    {
        Mover testMover = getTestMover(true, new Point(0, 0),
                new Point(GameConstants.TICKS_PER_SEC, 0));

        testMover.tick(emptyLevel);
        
        assertEquals(0.25, testMover.getLocation().getX(), 2);
    }
    
    /**
     * Tests the setter for wallCollision.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testSetWallColision()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getWallCollision());

        enemy.setWallCollision(true);
        assertTrue(enemy.getWallCollision());
    }
    
    /**
     * Tests the function onWallCollision.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testOnWallColision()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getWallCollision());

        enemy.onWallCollision();
        assertTrue(enemy.getWallCollision());
    }
    
    /**
     * Tests the getter for tickCounter.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testGetTickCounter()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getTickCounter(), 0);
    }
    
    /**
     * Tests the setter for tickCounter.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testSetTickCounter()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getTickCounter(), 0);

        enemy.setTickCounter(10);
        assertEquals(enemy.getTickCounter(), 10);
    }
    
    /**
     * Tests the getter for random.
     */
	@SuppressWarnings("static-access")
	@Test
    public void testGetRandom()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertTrue(enemy.getRandom() == 1.00f);
    }
	
	/**
     * Tests the setter for random.
     */
	@SuppressWarnings("static-access")
	@Test
    public void testSetRandom()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertTrue(enemy.getRandom() == 1.00f);

        enemy.setRandom(2.00f);
        assertTrue(enemy.getRandom() == 2.00f);
    }
	
	/**
     * Tests the getter for lastWall.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testGetLastWall()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getLastWall());
    }
	
	/**
     * Tests the setter for lastWall.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testSetLastWall()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertFalse(enemy.getLastWall());

        enemy.setLastWall(true);
        assertTrue(enemy.getLastWall());
    }
    
    /**
     * Tests the function getWorth().
     */
    @SuppressWarnings("static-access")
	@Test
    public void testGetWorth()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        // DEFAULT_ENEMY_VALUE = 50.
        assertEquals(enemy.getWorth(), 50);
    }
    
    /**
     * Tests the getter for Mover player in enemy.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testGetPlayer()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertNull(enemy.getPlayer());
    }
    
    /**
     * Tests the setter for Mover player in enemy.
     */
    @SuppressWarnings("static-access")
	@Test
    public void testSetPlayer()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertNull(enemy.getPlayer());

        Player player = new Player(new Point(0, 0), null);
        enemy.setPlayer((Player) player);
        assertEquals(enemy.getPlayer(), (Mover) player);
    }
}
