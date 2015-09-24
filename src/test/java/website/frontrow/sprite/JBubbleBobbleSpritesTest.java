package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import website.frontrow.board.Direction;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test Sprite Store.
 */
public class JBubbleBobbleSpritesTest
{
    private Sprite s;
    private Map<Direction, Sprite> directionalSprite;
    private JBubbleBobbleSprites sp;

    /**
     * Setup the sprite store for the test.
     */
    @Before
    public void setUp()
    {
        sp = new JBubbleBobbleSprites();
    }

    /**
     * Teardown, run after every test.
     */
    @After
    public void teardown()
    {
        s = null;
        sp = null;
        directionalSprite = null;
    }

    /**
     * Test to see if WallSprite returns a sprite.
     */
    @Test
    public void testGetWallSprite()
    {
        s = sp.getWallSprite();
        assertNotNull(s);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.UP is used.
     */
    @Test
    public void testGetDirectionalPlayerSprite()
    {
        directionalSprite = sp.getPlayerSprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Test spritestore to see if an exception is thrown when Direction.UP is used.
     */
    @Test
    public void testGetEnemySpriteDirectionUP()
    {
        directionalSprite = sp.getEnemySprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Test getPlatformSprite to see if it returns a platform sprite.
     */
    @Test
    public void testGetPlatformSprite()
    {
        s = sp.getPlatformSprite();
        assertNotNull(s);
    }
}