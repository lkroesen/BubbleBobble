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
        Launcher launcher = new Launcher();
        String[] files = {"/1.txt"};
        launcher.start(files, 1);
        assertNotNull(launcher);
    }

    /**
     * Test when an empty filename is entered that an exception is thrown.
     */
    @Test(expected = RuntimeException.class)
    public void testStartEmptyFileName()
    {
        Launcher launcher = new Launcher();
        String[] files = {""};
        launcher.start(files, 1);
    }
}