package website.frontrow.util;

import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;

import static org.junit.Assert.assertEquals;

/**
 * Test the music player.
 */
public class MusicPlayerTest
{
    private static final String DIR     = "/sound/";
    private static final String ZERO    = "Quest Begins.mp3";
    private static final String ONE     = "Bad End.mp3";
    private static final String TWO     = "Boss Theme.mp3";
    private static final String THREE   = "Credits.mp3";
    private static final String FOUR    = "Game Over.mp3";
    private static final String FIVE    = "Happy End.mp3";
    private static final String SIX     = "Hurry Up.mp3";
    private static final String SEVEN   = "Invincibility.mp3";
    private static final String EIGHT   = "Secret Room.mp3";
    private static final String NINE    = "Thank You!.mp3";
    private static final String TEN     = "Title Screen.mp3";
    private static final String ELVN    = "Victory!.mp3";

    private boolean noAudio;

    /**
     * Run before a test to identify no Audio devices.
     */
    @Before
    public void setup()
    {
        Line.Info[] audioDevicesList = new Line.Info[4];
        audioDevicesList[0] = Port.Info.SPEAKER;
        audioDevicesList[1] = Port.Info.HEADPHONE;
        audioDevicesList[2] = Port.Info.COMPACT_DISC;
        audioDevicesList[3] = Port.Info.LINE_OUT;

        for (int c = 0; c < audioDevicesList.length; c++)
        {
            if (AudioSystem.isLineSupported(audioDevicesList[c]))
            {
                noAudio = false;
                return;
            }
        }
        noAudio = true;
    }


    /**
     * Test if the BGM will be played.
     */
    @Test
    public void testPlayBGM()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playBGM();

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + ZERO);
        }
    }

    /**
     * Test Selection options.
     */
    @Test(expected = RuntimeException.class)
    public void testPlaySelectionNegOne()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(-1);
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionZer()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(0);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + ZERO);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionOne()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(1);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + ONE);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionTwo()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(2);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + TWO);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionThr()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(3);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + THREE);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionFour()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(4);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + FOUR);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionFive()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(5);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + FIVE);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionSix()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(6);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + SIX);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionSvn()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(7);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + SEVEN);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionEgt()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(8);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + EIGHT);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionNin()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(9);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + NINE);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionTen()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(10);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + TEN);
        }
    }

    /**
     * Test Selection options.
     */
    @Test
    public void testPlaySelectionElvn()
    {
        MusicPlayer mp = new MusicPlayer();
        mp.playSelection(11);

        if (noAudio)
        {
            assertEquals(mp.getCurrSound(), null);
        }
        else
        {
            assertEquals(mp.getCurrSound(), DIR + ELVN);
        }
    }

}