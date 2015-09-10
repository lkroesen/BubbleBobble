package website.frontrow;

import website.frontrow.level.Level;

/**
 * The current state of the game.
 */
public class Game
{
    private int highscore;
    private Level currentLevel;
    boolean running = false;

    public static final int TICKS_PER_SEC = 60;

    /**
     * Constructor of Game.
     * @param level The current level the player can play.
     */
    public Game(Level level)
    {
        highscore = 0;
        this.currentLevel = level;
    }

    /**
     * Tick the current level if the game is not paused.
     */
    public void tick()
    {
        if(running)
        {
            currentLevel.tick();
        }
    }

    /**
     * Start the game.
     */
    public void start()
    {
        running = true;
    }

    /**
     * Stop the game (pause it).
     */
    public void stop()
    {
        running = false;
    }

    /**
     * Whether the game is currently running.
     * @return Current running state of the game.
     */
    public boolean isRunning()
    {
        return running;
    }


    /**
     * Restarts the game and sets the highscore to 0.
     * TODO: Restart the level.
     */
    private void restart()
    {
        highscore = 0;
    }

    /**
     * Get the current highscore.
     * @return
     * Returns an int with the highscore
     */
    public int getHighscore()
    {
        return highscore;
    }

    /**
     * Set the current highscore.
     * @param highscore
     * Input an int with the value to set the highscore with.
     */
    public void setHighscore(int highscore)
    {
        this.highscore = highscore;
    }

    /**
     * The level which is currently being played.
     * @return The level.
     */
    public Level getLevel()
    {
        return this.currentLevel;
    }
}
