package website.frontrow.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        new AudioDetector();
        if (!AudioDetector.isNoAudio())
        {
            new MusicPlayer();
        }
    }

    /**
     * Test if we can select a song succesfuly.
     */
    @Test
    public void testSelectSong()
    {
        if (!AudioDetector.isNoAudio())
        {
            assertEquals(MusicPlayer.selectSong(Songs.BOSS_THEME), 0);
        }
    }

    /**
     * Test if we enter a String that doesn't link to a file that we get a nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void testSelectSongNoSong()
    {
        if (!AudioDetector.isNoAudio())
        {
            assertEquals(MusicPlayer.selectSong("foo"), 0);
        }
    }

    /**
     * Test if stopping the music works.
     */
    @Test
    public void testStop()
    {
        if (!AudioDetector.isNoAudio())
        {
            MusicPlayer.selectSong(Songs.INVINCIBILITY);
            assertEquals(MusicPlayer.stop(), 0);
        }
    }

    /**
     * Test if adjusting the volume works.
     */
    @Test
    public void testVolumeAdjust()
    {
        if (!AudioDetector.isNoAudio())
        {
            MusicPlayer.selectSong(Songs.BAD_END);
            assertEquals(MusicPlayer.volumeAdjust(0.0d), 0);
        }
    }

    /**
     * Test if changing the looping variable works.
     */
    @Test
    public void testSetLooping()
    {
        if (!AudioDetector.isNoAudio())
        {
            MusicPlayer.selectSong(Songs.CREDITS);
            assertEquals(MusicPlayer.setLooping(true), false);
        }
    }
}