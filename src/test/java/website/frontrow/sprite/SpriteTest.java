package website.frontrow.sprite;

import java.awt.Image;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by larsstegman on 06-09-15.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class SpriteTest
{
    private Image testImage;
    private Sprite sprite;

    /**
     * Provides the test image to be used in the tests.
     * @return The test image.
     * @throws IOException Throws Exception when triggered.
     */
    abstract Image createTestImage() throws IOException;


    /**
     * Creates the Sprite object to use for the tests.
     * Use the testImage from this class to test.
     * @return  The Sprite object to be used.
     * @throws IOException  Throws Exception when triggered.
     */
    abstract Sprite createSprite() throws IOException;


    /**
     * Sets things up for the tests.
     * @throws IOException  Throws Exception when triggered.
     */
    @Before
    public void setUp() throws IOException
    {
        sprite = createSprite();
        testImage = createTestImage();
    }

    /**
     * Tears things down after the tests.
     */
    @After
    public void tearDown()
    {
        sprite = null;
        testImage = null;
    }

    /**
     * Tests whether a slice within boundaries does not return an empty sprite.
     */
    @Test
    public void testSliceWithinBoundaries()
    {
        int x = new Double(testImage.getWidth(null) * 0.2).intValue();
        int y = new Double(testImage.getHeight(null) * 0.2).intValue();

        int width = new Double(testImage.getWidth(null) * 0.4).intValue();
        int height = new Double(testImage.getHeight(null) * 0.4).intValue();

        Sprite result = sprite.slice(x, y, width, height);
        assertFalse(result instanceof EmptySprite);
    }

    /**
     * Tests that the slice outside boundaries returns an empty sprite.
     */
    @Test
    public void testSliceOutsideBoundaries()
    {
        int x = new Double(testImage.getWidth(null) * 1.2).intValue();
        int y = new Double(testImage.getHeight(null) * 1.2).intValue();

        int width = new Double(testImage.getWidth(null) * 1.4).intValue();
        int height = new Double(testImage.getHeight(null) * 1.4).intValue();

        Sprite result = sprite.slice(x, y, width, height);
        assertTrue(result instanceof EmptySprite);
    }

    /**
     * Tests that the correct width is returned.
     */
    @Test
    public void testWidth()
    {
        assertEquals(sprite.getWidth(), testImage.getWidth(null));
    }

    /**
     * Tests that the correct height is returned.
     */
    @Test
    public void testHeight()
    {
        assertEquals(sprite.getHeight(), testImage.getHeight(null));
    }

    /**
     * Tests that the new sprite has the correct dimensions after splicing.
     */
    @Test
    public void testNewSpriteHasCorrectDimensionsAfterSplicing()
    {
        int newSpriteHeight = new Double(testImage.getWidth(null) * 0.4).intValue();
        int newSpriteWidth = new Double(testImage.getWidth(null) * 0.4).intValue();

        int x = new Double(testImage.getWidth(null) * 0.2).intValue();
        int y = new Double(testImage.getHeight(null) * 0.2).intValue();

        Sprite result = sprite.slice(x, y, newSpriteWidth, newSpriteHeight);
        assertEquals(newSpriteHeight, result.getHeight());
        assertEquals(newSpriteWidth, result.getWidth());
    }

}
