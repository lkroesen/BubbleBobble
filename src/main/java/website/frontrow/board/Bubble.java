package website.frontrow.board;

import website.frontrow.game.GameConstants;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

import java.util.Map;

/**
 * A bubble!
 */
public class Bubble
        extends Mover
        implements Logable
{

    private static final Point FLOAT_UP_MOTION = new Point(0, -2);

    /**
     * The enemy the bubble currently contains.
     * Is null when the bubble is empty.
     */
    private Enemy contains;

    private long timeContained = 0;
    
    private long timeEmpty = 0;
    
    private final long TIME_FLOAT_UPWARDS = 50;
    private final long TIME_KILL = 500;

    /**
     * Did this bubble hit something?
     */
    private boolean hit = false;

    /**
     * Constructor of the Bubble Unit.
     * @param position The starting position of the bubble.
     * @param motion The starting motion of the bubble.
     * @param sprites The sprites for this bubble.
     */
    public Bubble(Point position, Point motion, Map<Direction, Sprite> sprites)
    {
        super(true, position, motion, sprites);
        addToLog("[BUBBLE]\t[SPAWN]\tBubble created.");
    }

    /**
     * Get the enemy that is contained within the bubble.
     * @return
     * Return the enemy that is in the bubble.
     */
    public Enemy getContains()
    {
        return contains;
    }

    /**
     * Capture other.
     * @param other The enemy to capture.
     */
    public void capture(Enemy other)
    {
        addToLog("[BUBBLE]\t" + other.toString() + " captured by bubble.");

        this.contains = other;

        this.hit();
        // Kill the enemy for good measure.
        // (Do not forget to revive and re-add to the level when he escapes.)
        other.kill();
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);
        
        if(this.contains == null && hit == false)
        {
        	timeEmpty++;
        	
        	if(timeEmpty >= TIME_FLOAT_UPWARDS)
        	{
        		this.hit();
        	}
        }
        
        if(this.contains == null && hit == true)
        {
        	timeEmpty++;
        	
        	if(timeEmpty >= TIME_KILL)
        	{
        		this.kill();
        	}
        }
        

        if(this.contains != null)
        {
            timeContained++;

            if(timeContained >= this.contains.getCaughtTime())
            {
                this.contains.revive();
                this.contains.setLocation(this.location);
                level.addUnit(this.contains);
                this.kill();
            }
        }
    }

    @Override
    public Unit duplicate()
    {
        return new Bubble(location, super.getMotion(), this.getSprites());
    }

    @Override
    public void onWallCollision()
    {
        addToLog("[BUBBLE]\tHit wall.");
        this.hit();
    }

    @Override
    public void applyGravity()
    {
        // Ignore gravity.
    }
    
    /**
     * Return the bubble speed multiplier.
     * @return the bubble speed multiplier
     */
    @Override
    public double getSpeedMultiplier()
    {
    	return GameConstants.BUBBLE_SPEED_MULTIPLIER;
    }    

    /**
     * Check whether this bubble has hit something.
     * @return Was this bubble hit.
     */
    public boolean isHit()
    {
        return hit;
    }

    /**
     * Change the bubble to a hit bubble.
     */
    private void hit()
    {
        this.hit = true;
        this.setMotion(new Point(FLOAT_UP_MOTION));
    }

    /**
     * Add log to action that happened to the bubble.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
    
    public long getTimeEmpty()
    {
    	return timeEmpty;
    }
    
    public void setTimeEmpty(long time)
    {
    	timeEmpty = time;
    }
}
