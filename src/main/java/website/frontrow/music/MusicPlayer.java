package website.frontrow.music;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * Class for playing music files.
 */
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

    public static void selectSong(String fileLocation)
    {
        songFile = new Media(MusicPlayer.class.getResource(fileLocation).toExternalForm());
        musicPlayer = new MediaPlayer(songFile);

        if (toggleLooping)
        {
            musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
        }

        play();
    }

    public static void stop()
    {
        musicPlayer.stop();
    }

    private static void play()
    {
        stop();
        musicPlayer.play();
    }

    public static void volumeAdjust(double delta)
    {
        musicPlayer.setVolume(musicPlayer.getVolume() + delta);
    }

    public static void setLooping(Boolean looping)
    {
        toggleLooping = looping;
    }
}
