package website.frontrow.music;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import website.frontrow.logger.Log;

/**
 * Class for playing music files.
 */
public final class MusicPlayer
{
    private boolean toggleLooping = false;
    private Media songFile;
    private MediaPlayer musicPlayer;
    private static final MusicPlayer INSTANCE = new MusicPlayer();
    private final JFXPanel panel;

    /**
     * Hidden Constructor for UtilityClass.
     */
    private MusicPlayer()
    {
        panel = new JFXPanel();
    }

    /**
     * Return the instance of the class.
     * @return Returns the current instance.
     */
    public static MusicPlayer getInstance()
    {
        return INSTANCE;
    }


    /**
     * Initialize JavaFX.
     */
    public static void init()
    {
    }

    /**
     * Select a Song to be played.
     * @param fileLocation Input the file location, pick from the abstract Songs class.
     */
    public void selectSong(String fileLocation)
    {
        // Stop if there is no audio device.
        if(AudioDetector.getInstance().isNoAudio())
        {
            return;
        }

        songFile = new Media(MusicPlayer.class.getResource(fileLocation).toExternalForm());
        Log.add("[MUSIC]\tPlaying the file at location: " + fileLocation);
        musicPlayer = new MediaPlayer(songFile);

        if (toggleLooping)
        {
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }

        play();
    }

    /**
     * Stop the music from playing.
     */
    public void stop()
    {
        if(AudioDetector.getInstance().isNoAudio() || musicPlayer == null)
        {
            return;
        }


        musicPlayer.stop();
    }

    /**
     * Plays music, stops before playing again.
     */
    private void play()
    {
        // Stop if there is no audio device.
        if(AudioDetector.getInstance().isNoAudio())
        {
            return;
        }

        stop();
        musicPlayer.play();
    }

    /**
     * Adjust the volume by a certain value.
     * @param delta Input a value to adjust the volume by.
     */
    public void volumeAdjust(double delta)
    {
        // Stop if there is no audio device.
        if(AudioDetector.getInstance().isNoAudio())
        {
            return;
        }

        musicPlayer.setVolume(musicPlayer.getVolume() + delta);
        Log.add("[MUSIC]\tVolume adjusted by: " + delta);
    }

    /**
     * Set looping to true or false.
     * @param looping Input true or false.
     * @return Returns the previous value.
     */
    public boolean setLooping(Boolean looping)
    {
        // Stop if there is no audio device.
        if(AudioDetector.getInstance().isNoAudio())
        {
            return toggleLooping;
        }

        boolean previous = toggleLooping;
        toggleLooping = looping;
        Log.add("[MUSIC]\tLooping set to: " + looping);

        return previous;
    }

    /**
     * The JFXPanel that enables us to use JavaFX and thus audio.
     * Do not worry too much about this.
     * @return The JFXPanel.
     */
    public JFXPanel getPanel()
    {
        return panel;
    }
}