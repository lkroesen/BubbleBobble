package website.frontrow.board;

import org.junit.Test;

import website.frontrow.game.GameConstants;
import website.frontrow.util.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getLocation(), super.FIRST_TEST_POINT);
    }

    /**
     * Test that an enemy is worth 50 points.
     */
    @Test
    public void enemyWorthTest()
    {
        Enemy enemy = new Enemy(super.FIRST_TEST_POINT, null);
        assertEquals(enemy.getWorth(), 50);
    }

    /**
     * Test that we can set the last wall.
     */
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
        
        //An enemy might move
        assertEquals(0.25, testMover.getLocation().getX(), 2);
    }
}
