package website.frontrow.util;

import javazoom.jl.player.Player;

/**
 * A thread to play music in.
 */
public class MusicThread extends Thread
{
    private Player music;

    /**
     * Start playing music.
     * @param m
     * Input the music to be played.
     */
    public void run(Player m)
    {
        this.music = m;
        try
        {
            music.play();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error Occured in Thread " + e);
        }
    }

    /**
     * Kill the thread.
     */
    public void kill()
    {
        music.close();
    }

}
