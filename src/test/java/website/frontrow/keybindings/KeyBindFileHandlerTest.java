package website.frontrow.keybindings;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * Test the FileHandler.
 */
public class KeyBindFileHandlerTest
{
    public static final String PATH = "src/test/resources/binds.txt";

    /**
     * Set the path for the tests.
     */
    @Before
    public void setup()
    {
        KeyBindFileHandler.setFilename(PATH);
    }

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

        Scanner scanner = new Scanner(new File(PATH), "UTF-8");
        assertEquals(scanner.nextInt(), 37);
    }

    /**
     * Test if LoadBindings work.
     * @throws IOException Throws exception when triggered.
     */
    @Test
    public void testLoadBindings() throws IOException
    {
        KeyBindFileHandler.getInstance().loadBindings();
        assertEquals(KeyBinds.player1GoLeft, 37);
    }

    /**
     * Test if the FileChecker throws an exception when null is inputed.
     * @throws IOException Throws exception when triggered.
     */
    @Test(expected = IOException.class)
    public void testFileChecker() throws IOException
    {
        KeyBindFileHandler.getInstance().fileChecker(null, true);
    }

    /**
     * Test if setFilename works.
     */
    @Test
    public void testSetFilename()
    {
        KeyBindFileHandler.setFilename("src/test/resources/binds.txt");
        assertEquals(KeyBindFileHandler.getFilename(), "src/test/resources/binds.txt");
    }
}