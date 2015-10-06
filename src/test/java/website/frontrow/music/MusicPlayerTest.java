package website.frontrow.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Test the Music Player class.
 */
public class MusicPlayerTest
{
    /**
     * Setup run before every test.
     */
    @Before
    public void setup()
    {
        assumeFalse("No audio devices are available. Skipping...",
                AudioDetector.getInstance().isNoAudio());
        MusicPlayer.init();
    }

    /**
     * Test if we enter a String that doesn't link to a file that we get a nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void testSelectSongNoSong()
    {
        MusicPlayer.getInstance().selectSong("foo");
    }

    /**
     * Test if changing the looping variable works.
     */
    @Test
    public void testSetLooping()
    {
        MusicPlayer.getInstance().selectSong(Songs.CREDITS);
        assertEquals(MusicPlayer.getInstance().setLooping(true), false);
    }

    /**
     * Test the getter of the JavaFX Panel.
     */
    @Test
    public void testGetPanel()
    {
        assertNotNull(MusicPlayer.getInstance().getPanel());
    }
}