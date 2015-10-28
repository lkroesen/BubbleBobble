package website.frontrow.board;

import website.frontrow.board.behaviour.BubbleGravityBehaviour;
import website.frontrow.board.observer.ScoreReceiver;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.JBubbleBobbleSprites;
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
    
    private static final long TIME_FLOAT_UPWARDS = 80;

    // Time to kill the bubble is dependent on the time it has float upwards before.
    @SuppressWarnings("checkstyle:magicnumber")
    private static final long TIME_KILL = 450 + TIME_FLOAT_UPWARDS;

    /**
     * Did this bubble hit something?
     */
    private boolean hit = false;

    /**
     * The receiver of the score when the bubble kills something.
     */
    private ScoreReceiver scoreReceiver;

    private Map<Direction, Sprite> capturedSprite;

    /**
     * Constructor of the Bubble Unit.
     * @param position The starting position of the bubble.
     * @param motion The starting motion of the bubble.
     * @param sprites The sprites for this bubble.
     */
    public Bubble(Point position, Point motion, Map<Direction, Sprite> sprites)
    {
        this(position, motion, sprites, null);
    }

    /**
     * Constructor of the Bubble Unit.
     * @param position The starting position of the bubble.
     * @param motion The starting motion of the bubble.
     * @param sprites The sprites for this bubble.
     * @param creator The creator of this bubble
     */
    public Bubble(Point position, Point motion, Map<Direction, Sprite> sprites, Player creator)
    {
        super(true, position, motion, sprites, BubbleGravityBehaviour.getInstance());
        this.scoreReceiver = creator;
        this.capturedSprite = JBubbleBobbleSprites.getInstance().getCapturedEnemySprite();
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
		// (Do not forget to revive and re-add to the level when he
		// escapes.)
		other.kill();
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);
        
        if(this.contains == null)
        {
        	timeEmpty++;
        	
        	if(!hit)
        	{
        		floatUpwards();
        	}
        	else
        	{
        		killBubble();
        	}
        }        
        else
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
    public void kill()
    {
        super.kill();
        if(this.scoreReceiver != null && this.contains != null)
        {
            this.scoreReceiver.increaseScoreWith(this.contains.getWorth());
        }
    }

    @Override
    public Unit duplicate()
    {
        return new Bubble(location, super.getMotion(), this.getSprites());
    }

    @Override
    public Sprite getSprite()
    {
        if (contains != null)
        {
            return capturedSprite.get(getDirection());
        }
        if(this.getMotion().getY() < 0)
        {
            return super.getSprites().get(Direction.UP);
        }
        return super.getSprite();
    }

    @Override
    public void onWallCollision()
    {
        addToLog("[BUBBLE]\tHit wall.");
        this.hit();
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
    
    /**
     * Getter for timeEmpty.
     * @return timeEmpty long
     */
    public long getTimeEmpty()
    {
    	return timeEmpty;
    }
    
    /**
     * Setter for timeEmpty.
     * @param time long
     */
    public void setTimeEmpty(long time)
    {
    	timeEmpty = time;
    }
    
    /**
     * Makes the bubble float upwards at the right time.
     */
    public void floatUpwards()
    {
        if(timeEmpty >= TIME_FLOAT_UPWARDS)
    	{
    		this.hit();
    		addToLog("[BUBBLE]\t is empty and floating upwards");
    	}
    }
    
    /**
     * Kills the bubble at the right time.
     */
    public void killBubble()
    {
    	if(timeEmpty >= TIME_KILL)
    	{
    		this.kill();
    		addToLog("[BUBBLE]\t was empty and popped");
    	}
    }
}
