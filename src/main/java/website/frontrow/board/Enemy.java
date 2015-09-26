package website.frontrow.board;

import website.frontrow.game.GameConstants;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

import java.util.Map;

/**
 * Enemy Class.
 */
public class Enemy
        extends Mover
        implements Logable
{
	private int tickCounter = 0;
	private float random = 1.00f;
	private Boolean lastWall = false;
	private Boolean wallCollision = false;

    /**
     * The amount of ticks needed to escape.
     */
    public static final long TIME_TO_ESCPAPE = 2 * GameConstants.TICKS_PER_SEC;

    /**
     * Constructor of the Enemy Unit.
     * Input a byte with the amount of lives the Enemy has.
     * @param position The starting position of the enemy.
     * @param sprites The sprites of the enemy.
     */
    public Enemy(Point position, Map<Direction, Sprite> sprites)
    {
        super(true, position, new Point(0, 0), sprites);
        new Log();
        addToLog("[BUBBLE]\t[SPAWN]\tEnemy created.");
    }

    @Override
    public Unit duplicate()
    {
        return new Enemy(location, this.getSprites());
    }

    /**
     * Log actions that happened to an Enemy.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
    
    /**
     * Return the tick counter.
     * @return the tick counter.
     */
    public int getTickCounter()
    {
    	return this.tickCounter;
    }
    
    /**
     * Set the ticks counter.
     * @param ticks the amount of ticks to be set.
     */
    public void setTickCounter(int ticks)
    {
    	this.tickCounter = ticks;
    }
    
    /**
     * If the enemy collides with a wall we set the boolean to true.
     * This way the ai can set a new course 
     */
    @Override
    public void onWallCollision()
    {
    	this.wallCollision = true;
    }
    
    /**
     * Return the wall collision.
     * @return the wall collision boolean.
     */
    public Boolean getWallCollision()
    {
    	return this.wallCollision;
    }
    
    /**
     * Set the wall collision.
     * @param collision set the wall collision boolean.
     */
    public void setWallCollision(Boolean collision)
    {
    	this.wallCollision = collision;
    }
    
    
    /**
     * Return the random float.
     * @return the random float.
     */
    public float getRandom()
    {
    	return this.random;
    }
    
    /**
     * Set the random float.
     * @param rand the value to set the float to.
     */
    public void setRandom(float rand)
    {
    	this.random = rand;
    }
    
    /**
     * Return the lastWall boolean.
     * @return the lastWall boolean.
     */
    public Boolean getLastWall()
    {
    	return this.lastWall;
    }
    
    /**
     * Set the lastWall boolean.
     * @param lastWall the value to set the boolean to.
     */
    public void setLastWall(Boolean lastWall)
    {
    	this.lastWall = lastWall;
    }
    
    /**
     * Return the enemy speed multiplier.
     * @return the enemy speed multiplier.
     */
    @Override
    public double getSpeedMultiplier()
    {
    	return GameConstants.ENEMY_SPEED_MULTIPLIER;
    }

    /**
     * The amount of time it costs for this enemy to escape.
     * @return Amount of time it costs for this enemy to escape.
     */
    public long getCaughtTime()
    {
        return TIME_TO_ESCPAPE;
    }
}
