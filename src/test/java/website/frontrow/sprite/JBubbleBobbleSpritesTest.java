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
        sp = JBubbleBobbleSprites.getInstance();
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
     * Test if the Instance is equal to the one created in the setup.
     */
    @Test
    public void testSingleTonGetter()
    {
        assertEquals(sp, JBubbleBobbleSprites.getInstance());
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
     * Test getting the player sprites.
     */
    @Test
    public void testGetDirectionalPlayerSprite()
    {
        directionalSprite = sp.getPlayerSprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Test getting the enemy sprites.
     */
    @Test
    public void testGetEnemySpriteDirection()
    {
        directionalSprite = sp.getEnemySprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Tests the bubble sprites.
     */
    @Test
    public void testGetBubbleSpriteDirection()
    {
        directionalSprite = sp.getBubbleSprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Tests that the program stops when a sprite can not be found.
     */
    @Test(expected = RuntimeException.class)
    public void testLoadSpriteFakefile()
    {
        s = sp.loadSprite("/youwillneverfindme!.txt");
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