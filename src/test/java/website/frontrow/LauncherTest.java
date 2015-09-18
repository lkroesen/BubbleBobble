package website.frontrow;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test the launcher.
 */
public class LauncherTest
{
    /**
     * Test if the launcher is able to launch.
     */
    @Test
    public void testLaunch()
    {
        Launcher l = new Launcher();
        String[] files = {"/1.txt"};
        l.start(files);
        assertNotNull(l);
    }

    /**
     * Test when an empty filename is entered that an exception is thrown.
     */
    @Test(expected = RuntimeException.class)
    public void testStartEmptyFileName()
    {
        Launcher l = new Launcher();
        String[] files = {""};
        l.start(files);
    }
}