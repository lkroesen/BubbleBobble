package website.frontrow.board;

import website.frontrow.game.GameConstants;
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
    private int score = 0;
    
    /**
     * The amount of lives the player has.
     */
    private int lives = GameConstants.DEFAULT_AMOUNT_OF_LIVES;

    /**
     * The player is invincible for a certain amount of time.
     */
    private static final int INVINCIBILITY_TICKS = GameConstants.TICKS_PER_SEC;

    /**
     * The amount of ticks the player is still immune to losing lives.
     */
    private int ticksLeft = 0;

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

        if(ticksLeft > 0)
        {
            ticksLeft--;
        }
    }
    
    /**
     * Return the player speed multiplier.
     * @return the player speed multiplier.
     */
    @Override
    public double getSpeedMultiplier()
    {
    	return GameConstants.PLAYER_SPEED_MULTIPLIER;
    }

    @Override
    public Unit duplicate()
    {
        return new Player(location, this.getSprites());
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
     * Handle getting hit.
     */
    public void onEnemyCollision()
    {
        if(ticksLeft <= 0)
        {
            ticksLeft = INVINCIBILITY_TICKS;
            loseLife();
            if(lives < 0)
            {
                this.kill();
            }
        }
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
    	addToLog("[PLAYER]\t Player's current amount of lives is now: " + lives);
    }
    
    /**
     * Decreases the number of lives by one.
     */
    public void loseLife()
    {
    	lives--;
    	addToLog("[PLAYER]\t Player lost a life, total lives is now: " + lives);
    }
    
    /**
     * Increases the number of lives by one.
     */
    public void addLife()
    {
    	lives++;
    	addToLog("[PLAYER]\t Player earned a life, total lives is now: " + lives);
    }
    
    /**
     * Returns whether or not the player still has lives left.
     * @return boolean
     */
    public boolean hasLives()
    {
    	return lives > 0;
    }
}
