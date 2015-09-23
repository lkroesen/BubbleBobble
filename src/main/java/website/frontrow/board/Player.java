package website.frontrow.board;

import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;
import website.frontrow.util.Point;

/**
 * The player as part of a game.
 */
public class Player
        extends Mover
            implements Logable
{

	private static SpriteStore ss = new SpriteStore();
	
    /**
     * The points accumulated by the player.
     */
    private int score;
    
    /**
     * The amount of lives the player has.
     */
    private int lives;

    /**
     * The constructor of the Player Unit.
     * @param position A players starting position.
     */
    public Player(Point position)
    {
        super(true, position, new Point(0, 0));
        new Log();
        addToLog("[PLAYER]\t[SPAWN]\tPlayer created.");
        lives = 3;
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
    
    /**
     * Returns the sprite of the unit, Player/Enemy/Empty respectively.
     * @return The sprite.
     */
    @Override
    public Sprite getSprite()
    {
    	return ss.getPlayerSprite(this.getDirection());
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
    
    /**
     * Returns whether or not the player is alive.
     * @return boolean
     */
    public boolean playerAlive()
    {
    	return lives > 0;
    }
}
