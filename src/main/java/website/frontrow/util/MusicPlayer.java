package website.frontrow.util;

import javazoom.jl.player.Player;

/**
 * Play Music during the game.
 */
public class MusicPlayer
{
    private Player music;
    private MusicThread mt;

    /**
     * Constructor is empty.
     */
    public MusicPlayer()
    {

    }

    /**
     * Play the background music.
     */
    public void playBGM()
    {
        try
        {
            music = new Player(getClass().getResourceAsStream(
                    "/sound/Quest Begins.mp3"));
        }
        catch (Exception e)
        {
            System.out.println("Problem playing file");
            System.out.println(e);
        }

        // run in new thread to play in background
        // TODO stop it at some point...
        new Thread()
        {
            public void run()
            {
                try
                {
                    mt = new MusicThread();
                    mt.run(music);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }.start();
    }

    /**
     * Stop the music playing.
     */
    public void stopSound()
    {
        mt.interrupt();
        mt = new MusicThread();
    }
}
