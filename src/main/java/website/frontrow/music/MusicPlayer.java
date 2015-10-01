package website.frontrow.music;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import website.frontrow.logger.Log;

/**
 * Class for playing music files.
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
/* The constructor is used to play the opening song and to make sure JavaFX is running. */
public class MusicPlayer
{
    private static boolean toggleLooping = false;
    private static Media songFile;
    private static MediaPlayer musicPlayer;

    /**
     * The constructor will play the title menu when it's initalized initially.
     */
    public MusicPlayer()
    {
        // Initialize JavaFX, the hacker way!
        new JFXPanel();
        selectSong(Songs.TITLE_SCREEN);
    }

    /**
     * Select a Song to be played.
     * @param fileLocation Input the file location, pick from the abstract Songs class.
     * @return Returns 0 upon success.
     */
    public static int selectSong(String fileLocation)
    {
        songFile = new Media(MusicPlayer.class.getResource(fileLocation).toExternalForm());
        Log.add("[MUSIC]\tPlaying the file at location: " + fileLocation);
        musicPlayer = new MediaPlayer(songFile);

        if (toggleLooping)
        {
            musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
        }

        play();
        return 0;
    }

    /**
     * Stop the music from playing.
     * @return Returns 0 upon success.
     */
    public static int stop()
    {
        musicPlayer.stop();
        return 0;
    }

    /**
     * Plays music, stops before playing again.
     * @return Returns 0 upon success.
     */
    private static int play()
    {
        stop();
        musicPlayer.play();
        return 0;
    }

    /**
     * Adjust the volume by a certain value.
     * @param delta Input a value to adjust the volume by.
     * @return Returns 0 upon success.
     */
    public static int volumeAdjust(double delta)
    {
        musicPlayer.setVolume(musicPlayer.getVolume() + delta);
        Log.add("[MUSIC]\tVolume adjusted by: " + delta);
        return 0;
    }

    /**
     * Set looping to true or false.
     * @param looping Input true or false.
     * @return Returns the previous value.
     */
    public static boolean setLooping(Boolean looping)
    {
        boolean previous = toggleLooping;
        toggleLooping = looping;
        Log.add("[MUSIC]\tLooping set to: " + looping);

        return previous;
    }
}