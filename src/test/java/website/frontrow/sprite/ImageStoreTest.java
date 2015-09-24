package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.Image;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test the image store.
 */
public class ImageStoreTest
{
    private ImageStore is;

    @SuppressWarnings("visibilitymodifier") //Needs to be public for @Rule
    @Rule
    public ExpectedException ee = ExpectedException.none();

    /**
     * Setup before running tests.
     */
    @Before
    public void setUp()
    {
        is = new ImageStore();
    }

    /**
     * Teardown after running tests.
     */
    @After
    public void tearDown()
    {
        is = null;
    }

    /**
     * Tests whether an image can be loaded.
     * @throws IOException the file might not be found.
     */
    @Test
    public void testGetImageExistingFile() throws IOException
    {
        is.getImage("/testImage100x100.png");
    }

    /**
     * Test that the right exception gets thrown when the file does not exist.
     * @throws IOException the file might not be found.
     */
    @Test
    public void testGetImageBadFileName() throws IOException
    {
        ee.expect(IllegalArgumentException.class);
        is.getImage("/nananananananbatmaaaan.png");
    }

    /**
     * Test create an empty image.
     */
    @Test
    public void testCreateTranslucentImage()
    {
        Image image = is.createTranslucentImage(50, 50);
        assertEquals(image.getWidth(null), 50);
        assertEquals(image.getHeight(null), 50);
    }

    /**
     * Test the correct exception is thrown when a nonexistent file is given.
     */
    @Test
    public void testGetImageIconFakeFile()
    {
        ee.expect(RuntimeException.class);
        is.getImageIcon("/nanananananfindme!.png");
    }

}
