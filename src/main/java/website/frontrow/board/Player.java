package website.frontrow.board;

import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

import java.util.Map;

/**
 * The player as part of a game.
 */
public class Player
        extends Mover
        implements Logable
{
    /**
     * The points accumulated by the player.
     */
    private int score;
    
    /**
     * The amount of lives the player has.
     */
    private int lives;
    
    /**
     * To avoid Checkstyle warnings.
     * The amount of lives the player starts with is 3.
     * However, if that integer is final, we can't change it.
     */
    private final int three = 3;

    /**
     * The constructor of the Player Unit.
     * @param position A players starting position.
     * @param sprites The sprite for this player.
     */
    public Player(Point position, Map<Direction, Sprite> sprites)
    {
        super(true, position, new Point(0, 0), sprites);
        new Log();
        addToLog("[PLAYER]\t[SPAWN]\tPlayer created.");
        lives = three;
    }

    /**
     * Returns the player's score.
     * @return score integer
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Adds p (points) to the score of the player, when it gets a pickup.
     * @param p integer
     */
    public void addScore(int p)
    {
        addToLog("[PLAYER]\t[SCORE]\tScore increased by " + p);
        score += p;
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);

        if(getMotion().getY() == 0)
        {
            getMotion().setX(0);
        }
    }

    /**
     * Input an action that is perfored by the player class.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
    
    /**
     * A getter for lives.
     * @return lives integer
     */
    public int getLives() 
    {
    	return lives;
    }
    
    /**
     * A setter for lives.
     * @param l integer
     */
    public void setLives(int l)
    {
    	lives = l;
    	addToLog("The player's current amount of lives is now: " + lives);
    }
    
    /**
     * Decreases the lives by one.
     */
    public void loseLife()
    {
    	lives--;
    	addToLog("The player lost a life, total lives is now: " + lives);
    }
    
    /**
     * Increases the lives by one.
     */
    public void addLife()
    {
    	lives++;
    	addToLog("The player earned a life, total lives is now: " + lives);
    }
    
    @Override
    public boolean isAlive()
    {
    	return lives > 0;
    }
}
