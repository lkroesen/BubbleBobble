package website.frontrow.keybindings;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * Test the FileHandler.
 */
public class KeyBindFileHandlerTest
{
    /**
     * Test if the instance can be gotten.
     */
    @Test
    public void testGetInstance()
    {
        assertNotNull(KeyBindFileHandler.getInstance());
    }

    /**
     * Test if saveBindings work.
     * @throws IOException Throws exception when triggered.
     */
    @Test
    public void testSaveBindings() throws IOException
    {
        KeyBindFileHandler.getInstance().saveBindings();
        assertNotNull(KeyBindFileHandler.getInstance());
    }

    /**
     * Test if LoadBindings work.
     * @throws IOException Throws exception when triggered.
     */
    @Test
    public void testLoadBindings() throws IOException
    {
        KeyBindFileHandler.getInstance().loadBindings();
        assertNotNull(KeyBindFileHandler.getInstance());
    }

    /**
     * Test if the FileChecker throws an exception when null is inputed.
     * @throws IOException Throws exception when triggered.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFileChecker() throws IOException
    {
        File fileTest = KeyBindFileHandler.getInstance().fileChecker(null, true);
    }

    /**
     * Test if setFilename works.
     */
    @Test
    public void testSetFilename()
    {
        KeyBindFileHandler.setFilename("testfile.txt");
        assertEquals(KeyBindFileHandler.getFilename(), "testfile.txt");
    }
}