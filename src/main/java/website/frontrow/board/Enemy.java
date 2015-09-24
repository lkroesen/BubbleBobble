package website.frontrow.board;

import website.frontrow.game.GameConstants;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;
import website.frontrow.util.Point;

/**
 * Enemy Class.
 */
public class Enemy
        extends Mover
            implements Logable
{

	private static SpriteStore ss = new SpriteStore();
	private int tickCounter;
	private float random;
	private Boolean lastWall;
	
    /**
     * Constructor of the Enemy Unit.
     * Input a byte with the amount of lives the Enemy has.
     * @param position THe starting position of the enemy.
     */
    public Enemy(Point position)
    {
        super(true, position, new Point(0, 0));
        this.tickCounter = 0;
        this.random = 1.00f;
        this.lastWall = false;
        new Log();
        addToLog("[BUBBLE]\t[SPAWN]\tEnemy created.");
    }
    
    /**
     * Returns the sprite of the unit, Player/Enemy/Empty respectively.
     * @return The sprite.
     */
    @Override
    public Sprite getSprite()
    {
    	return ss.getEnemySprite(this.getDirection());
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
    public double getUnitSpeedMultiplier()
    {
    	return GameConstants.ENEMY_SPEED_MULTIPLIER;
    }
}
