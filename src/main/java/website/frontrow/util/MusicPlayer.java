package website.frontrow.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;

/**
 * Play Music during the game.
 */
public class MusicPlayer
{
    private Clip musicClip;
    private FloatControl volumeControl;

    /**
     * Constructor is empty.
     */
    public MusicPlayer()
    {

    }

    /**
     * Play the Background Music, volume is automatically lowered a bit.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public void playBGM()
    {
        try
        {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(getClass().getResourceAsStream(
                            "/sound/Quest Begins.wav"));
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
            musicClip.start();

            volumeControl =
                    (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            changeVolume(-30.0f);
            // TODO loop music
        }
        catch (IOException e)
        {
            System.out.println("1. " + e);
        }
        catch (UnsupportedAudioFileException e)
        {
            System.out.println("2. " + e);
        }
        catch (LineUnavailableException e)
        {
            System.out.println("3. " + e);
        }
    }

    /**
     * Input the amount to lower or raise the volume by, negative value lowers
     * positive value raises.
     * @param deltaVolume
     * Input a float with the amount of Volume to lower or raise by.
     */
    public void changeVolume(float deltaVolume)
    {
        volumeControl.setValue(deltaVolume);
    }
}
