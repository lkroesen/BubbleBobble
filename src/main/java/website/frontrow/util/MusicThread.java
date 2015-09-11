package website.frontrow.util;

import javazoom.jl.player.Player;
/**
 * A thread to play music in.
 */
public class MusicThread extends Thread
{
    /**
     * Start playing music.
     * @param music
     * Input the music to be played.
     */
    public void run(Player music)
    {
        try
        {
            music.play();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
