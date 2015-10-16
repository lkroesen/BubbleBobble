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
    private Sprite sprite;
    private Map<Direction, Sprite> directionalSprite;
    private JBubbleBobbleSprites jBubbleBobbleSprites;

    /**
     * Setup the sprite store for the test.
     */
    @Before
    public void setUp()
    {
        jBubbleBobbleSprites = JBubbleBobbleSprites.getInstance();
    }

    /**
     * Teardown, run after every test.
     */
    @After
    public void teardown()
    {
        sprite = null;
        jBubbleBobbleSprites = null;
        directionalSprite = null;
    }

    /**
     * Test if the Instance is equal to the one created in the setup.
     */
    @Test
    public void testSingleTonGetter()
    {
        assertEquals(jBubbleBobbleSprites, JBubbleBobbleSprites.getInstance());
    }

    /**
     * Test to see if WallSprite returns a sprite.
     */
    @Test
    public void testGetWallSprite()
    {
        sprite = jBubbleBobbleSprites.getWallSprite();
        assertNotNull(sprite);
    }

    /**
     * Test getting the player sprites.
     */
    @Test
    public void testGetDirectionalPlayerSprite()
    {
        directionalSprite = jBubbleBobbleSprites.getPlayerSprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Test getting the enemy sprites.
     */
    @Test
    public void testGetEnemySpriteDirection()
    {
        directionalSprite = jBubbleBobbleSprites.getEnemySprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Tests the bubble sprites.
     */
    @Test
    public void testGetBubbleSpriteDirection()
    {
        directionalSprite = jBubbleBobbleSprites.getBubbleSprite();
        assertEquals(directionalSprite.size(), 4);
    }

    /**
     * Tests that the program stops when a sprite can not be found.
     */
    @Test(expected = RuntimeException.class)
    public void testLoadSpriteFakefile()
    {
        sprite = jBubbleBobbleSprites.loadSprite("/youwillneverfindme!.txt");
    }


    /**
     * Test getPlatformSprite to see if it returns a platform sprite.
     */
    @Test
    public void testGetPlatformSprite()
    {
        sprite = jBubbleBobbleSprites.getPlatformSprite();
        assertNotNull(sprite);
    }
}