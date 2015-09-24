package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * A test class to test the sprite store.
 */
public class SpriteStoreTest
{

    private SpriteStore ss;

    @SuppressWarnings("visibilitymodifier")
    @Rule
    public ExpectedException ee = ExpectedException.none();

    /**
     * Set the things up for the tests.
     */
    @Before
    public void setUp()
    {
        ss = new SpriteStore();
    }

    /**
     * Tear down the things after the tests.
     */
    @After
    public void tearDown()
    {
        ss = null;
    }

    /**
     * Load the sprites succesfully.
     * @throws IOException The file might not exist.
     */
    @Test
    public void testLoadSpriteSuccess() throws IOException
    {
        Sprite sprite = ss.loadSprite("/testImage100x100.png");
        assertNotNull(sprite);
    }

    /**
     * Load an unknown file.
     * @throws IOException This test load an unknown file.
     */
    @Test
    public void testLoadSpriteUnknownFile() throws IOException
    {
        ee.expect(IOException.class);
        ss.loadSprite("/cantfindthisone.png");
    }
}
