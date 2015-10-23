package website.frontrow;

import org.junit.Test;
import website.frontrow.keybindings.KeyBindFileHandler;
import website.frontrow.util.FileNameCollector;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

/**
 * Test the launcher.
 */
public class LauncherTest
{
    /**
     * Test if the launcher is able to launch.
     * @throws IOException Throws when triggered.
     * @throws URISyntaxException Throws when triggered.
     */
    @Test
    public void testLaunch() throws IOException, URISyntaxException
    {
        Launcher launcher = new Launcher();
        KeyBindFileHandler.setFilename("src/test/resources/binds.txt");
        KeyBindFileHandler.getInstance().loadBindings();
        String[] files = FileNameCollector.getInstance().obtain("level/");
        launcher.start(files, 2);
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