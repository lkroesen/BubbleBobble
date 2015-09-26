package website.frontrow.util;

import javazoom.jl.player.Player;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * A thread to play music in.
 */
public class MusicThread
        extends Thread
        implements Logable
{
    private Player music;

    /**
     * Start playing music.
     * @param musicPlayer
     * Input the music to be played.
     */
    public void run(Player musicPlayer)
    {
        this.music = musicPlayer;
        try
        {
            music.play();
        }
        catch (Exception exception)
        {
            addToLog("[MT]\t[ERROR]\tMusic Thread encountered: " + exception.toString() + ".");
            new DumpLog();
            throw new RuntimeException("Error Occurred in Thread " + exception);
        }
    }

    /**
     * Kill the thread.
     */
    public void kill()
    {
        addToLog("[MT]\tMusic Thread got killed.");
        music.close();
    }

    /**
     * Log actions taken by Music Thread.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);

    }
}
