package website.frontrow.sprite;

import org.junit.After;
import org.junit.Test;
import website.frontrow.board.Direction;

import static org.junit.Assert.assertNotNull;

/**
 * Test Sprite Store.
 */
public class SpriteStoreTest
{
    private Sprite s;

    /**
     * Teardown, run after every test.
     */
    @After
    public void teardown()
    {
        s = null;
    }

    /**
     * Test to see if WallSprite returns a sprite.
     */
    @Test
    public void testGetWallSprite()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getWallSprite();
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.UP is used.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPlayerSpriteDirectionUP()
    {
        SpriteStore sp = new SpriteStore();
        sp.getPlayerSprite(Direction.UP);
    }

    /**
     * Test spritestore to see if we get a sprite when Direction.LEFT is used.
     */
    @Test
    public void testGetPlayerSpriteDirectionLEFT()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getPlayerSprite(Direction.LEFT);
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if we get a sprite when Direction.RIGHT is used.
     */
    @Test
    public void testGetPlayerSpriteDirectionRIGHT()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getPlayerSprite(Direction.RIGHT);
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.DOWN is used.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPlayerSpriteDirectionDOWN()
    {
        SpriteStore sp = new SpriteStore();
        sp.getPlayerSprite(Direction.DOWN);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.UP is used.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetEnemySpriteDirectionUP()
    {
        SpriteStore sp = new SpriteStore();
        sp.getEnemySprite(Direction.UP);
    }

    /**
     * Test spritestore to see if we get a sprite when Direction.LEFT is used.
     */
    @Test
    public void testGetEnemySpriteDirectionLEFT()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getEnemySprite(Direction.LEFT);
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if we get a sprite when Direction.RIGHT is used.
     */
    @Test
    public void testGetEnemySpriteDirectionRIGHT()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getEnemySprite(Direction.RIGHT);
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.DOWN is used.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetEnemySpriteDirectionDOWN()
    {
        SpriteStore sp = new SpriteStore();
        sp.getEnemySprite(Direction.DOWN);
    }

    /**
     * Test getPlatformSprite to see if it returns a platform sprite.
     */
    @Test
    public void testGetPlatformSprite()
    {
        SpriteStore sp = new SpriteStore();
        s = sp.getPlatformSprite();
        assertNotNull(s);
    }
}