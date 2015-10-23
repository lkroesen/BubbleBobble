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
    private ImageStore imageStore;

    @SuppressWarnings("visibilitymodifier") //Needs to be public for @Rule
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Setup before running tests.
     */
    @Before
    public void setUp()
    {
        imageStore = ImageStore.getInstance();
    }

    /**
     * Teardown after running tests.
     */
    @After
    public void tearDown()
    {
        imageStore = null;
    }

    /**
     * Tests whether an image can be loaded.
     * @throws IOException the file might not be found.
     */
    @Test
    public void testGetImageExistingFile() throws IOException
    {
        imageStore.getImage("/testImage100x100.png");
    }

    /**
     * Test that the right exception gets thrown when the file does not exist.
     * @throws IOException the file might not be found.
     */
    @Test
    public void testGetImageBadFileName() throws IOException
    {
        expectedException.expect(IOException.class);
        imageStore.getImage("/nananananananbatmaaaan.png");
    }

    /**
     * Test create an empty image.
     */
    @Test
    public void testCreateTranslucentImage()
    {
        Image image = imageStore.createTranslucentImage(50, 50);
        assertEquals(image.getWidth(null), 50);
        assertEquals(image.getHeight(null), 50);
    }

    /**
     * Test the correct exception imageStore thrown when a nonexistent file imageStore given.
     */
    @Test
    public void testGetImageIconFakeFile()
    {
        expectedException.expect(RuntimeException.class);
        imageStore.getImageIcon("/nanananananfindme!.png");
    }

}
