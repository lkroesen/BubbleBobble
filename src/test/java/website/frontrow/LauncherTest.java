package website.frontrow;

import org.junit.Before;
import org.junit.Test;
import website.frontrow.keybindings.ActionType;
import website.frontrow.keybindings.KeyBindFileHandler;
import website.frontrow.keybindings.KeyBinds;
import website.frontrow.util.FileNameCollector;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;

/**
 * Test the launcher.
 */
public class LauncherTest
{
    /**
     * Run before LauncherTest to check for Nullpointers.
     * @throws IOException Throws when triggered.
     */
    @Before
    public void setup() throws IOException
    {
        KeyBindFileHandler.setFilename("src/test/resources/binds.txt");
        KeyBindFileHandler.getInstance().loadBindings();

        assumeNotNull("KeyBindings are null...",
                KeyBinds.getKeyCodeFor(ActionType.JUMP, 0));
    }

    /**
     * Test if the launcher is able to launch.
     * @throws IOException Throws when triggered.
     * @throws URISyntaxException Throws when triggered.
     */
    @Test
    public void testLaunch() throws IOException, URISyntaxException
    {
        Launcher launcher = new Launcher();
        String[] files = FileNameCollector.getInstance().obtain("level/");
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