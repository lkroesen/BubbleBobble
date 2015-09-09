package website.frontrow;

import website.frontrow.level.Level;

/**
 * The current state of the game.
 */
public class Game
{
    private int highscore;
    private Level currentLevel;

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
