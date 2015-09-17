package website.frontrow.util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;

import javazoom.jl.player.Player;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * Play Music during the game.
 */
public class MusicPlayer
    implements Logable
{
    private Player music;
    private MusicThread mt;
    private boolean threadActive;
    private String currSound;
    private FloatControl volume;
    private boolean volumeInitialized;
    private boolean noAudio;

    /**
     * Constructor is empty.
     */
    public MusicPlayer()
    {
        // Check for audio devices
        getAudioDevice();
        volumeInitialized = false;
        threadActive = false;
    }

    /**
     * Play the background music.
     */
    @Deprecated
    public void playBGM()
    {
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }
        play("/sound/Quest Begins.mp3");
    }

    /**
     * Play songs, identified by number.
     * <ul>
     *     <li>0 : Quest Begins - The Normal BGM</li>
     *     <li>1 : Bad End - Bad Ending Music</li>
     *     <li>2 : Boss Theme - Boss BGM</li>
     *     <li>3 : Credits - Credits BGM</li>
     *     <li>4 : Game Over - Sound Effect for Failure State</li>
     *     <li>5 : Happy End - Good Ending Music</li>
     *     <li>6 : Hurry Up - Not much time left BGM</li>
     *     <li>7 : Invincibility - Immune Sound Effect</li>
     *     <li>8 : Secret Room - Secret Room BGM</li>
     *     <li>9 : Thank You! - Thank You! Sound Effect</li>
     *     <li>10 : Title Screen - Title Screen Music</li>
     *     <li>11 : Victory! - Victory Sound Effect</li>
     * </ul>
     * @param selection
     * Input a number to select a song.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    // Magicnumbers ignored due to the numbers being used for song selection.
    public void playSelection(int selection)
    {
        if (selection < 0 || selection > 11)
        {
            addToLog("[MP]\t[ERROR]\tMusic Selection Out Of Bounds");
            new DumpLog();
            throw new RuntimeException("Wrong Selection");
        }
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }
        switch(selection)
        {
            case 0  : play("/sound/Quest Begins.mp3");  break;
            case 1  : play("/sound/Bad End.mp3");       break;
            case 2  : play("/sound/Boss Theme.mp3");    break;
            case 3  : play("/sound/Credits.mp3");       break;
            case 4  : play("/sound/Game Over.mp3");     break;
            case 5  : play("/sound/Happy End.mp3");     break;
            case 6  : play("/sound/Hurry Up.mp3");      break;
            case 7  : play("/sound/Invincibility.mp3"); break;
            case 8  : play("/sound/Secret Room.mp3");   break;
            case 9  : play("/sound/Thank You!.mp3");    break;
            case 10 : play("/sound/Title Screen.mp3");  break;
            case 11 : play("/sound/Victory!.mp3");      break;
            default : throw new RuntimeException("Wrong Selection");
        }
    }

    /**
     * Play a specific filename, will be called internally.
     * @param filename
     * Input the file of the song to be played.
     */
    @SuppressWarnings("checkstyle:methodlength")
    private void play(String filename)
    {
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }

        if (threadActive)
        {
            stopSound();
        }

        try
        {
            music = new Player(getClass().getResourceAsStream(filename));
            currSound = filename;
        }
        catch (Exception e)
        {
            addToLog("[MP]\t[ERROR]\tAudio File couldn't be played.");
            new DumpLog();
            System.out.println(e);
        }

        new Thread()
        {
            public void run()
            {
                try
                {
                    addToLog("[MP]\tMusic: " + currSound + " playing.");
                    mt = new MusicThread();
                    mt.run(music);
                }
                catch (Exception e)
                {
                    addToLog("[MP]\t[ERROR]\tAudio File couldn't be played.");
                    new DumpLog();
                    System.out.println(e);
                }
            }
        }.start();
        threadActive = true;
    }

    /**
     * Stop the music playing.
     */
    public void stopSound()
    {
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }

        if (threadActive)
        {
            addToLog("[MP]\tStopping audio.");
            mt.kill();
            threadActive = false;
        }
    }

    /**
     * Make a FloatControl so that we can control the audio.
     */
    private void makeFloatControl()
    {
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }

        Info device = getAudioDevice();
        assert device != null;

        try
        {
            Port out = (Port) AudioSystem.getLine(device);
            out.open();
            volume = (FloatControl)
                    out.getControl(FloatControl.Type.VOLUME);
        }
        catch (LineUnavailableException ex)
        {
            addToLog("[MP]\t[ERROR]\tLineUnavailableException.");
            new DumpLog();
            ex.printStackTrace();
        }
    }

    /**
     * Adjust volume by inputted amount.
     * -0.1 lowers by 10.
     * 0.1 raises by 10.
     * @param deltaVolume
     * Input a float with the amount of volume to change by.
     */
    public void volumeAdjust(float deltaVolume)
    {
        if (noAudio)
        {
            addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
            return;
        }

        if (!volumeInitialized)
        {
            makeFloatControl();
            volumeInitialized = true;
        }
        addToLog("[MP]\tVolume Adjusted by: " + deltaVolume + ".");
        volume.setValue(volume.getValue() + deltaVolume);
    }

    /**
     * Get the AudioDevice currently in use on the computer.
     * @return
     * Returns the Audio Device used.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public Info getAudioDevice()
    {
        Info[] audioDevicesList = new Info[4];
        audioDevicesList[0] = Port.Info.SPEAKER;
        audioDevicesList[1] = Port.Info.HEADPHONE;
        audioDevicesList[2] = Port.Info.COMPACT_DISC;
        audioDevicesList[3] = Port.Info.LINE_OUT;

        for (int c = 0; c < audioDevicesList.length; c++)
        {
            if (AudioSystem.isLineSupported(audioDevicesList[c]))
            {
                noAudio = false;
                return audioDevicesList[c];
            }
        }
        addToLog("[MP]\t[WARNING]\tNo audio devices detected!");
        noAudio = true;
        return null;
    }

    /**
     * Get the sound that was played last.
     * @return
     * Returns a String with the file name.
     */
    public String getCurrSound()
    {
        return currSound;
    }

    /**
     * Get the FloatControl volume.
     * @return
     * Returns the volume.
     */
    public FloatControl getVolume()
    {
        return volume;
    }

    /**
     * Log actions taken by MusicPlayer.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
