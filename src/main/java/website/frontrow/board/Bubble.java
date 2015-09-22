package website.frontrow.board;

import website.frontrow.game.GameConstants;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;
import website.frontrow.util.Point;

/**
 * A bubble!
 */
public class Bubble
        extends Mover
            implements Logable
{
    public static final SpriteStore SS = new SpriteStore();

    private static final Point HIT_MOTION = new Point(0, -2);

    /**
     * The amount of ticks needed to escape.
     */
    private static final long TIME_TO_ESCPAPE = 2 * GameConstants.TICKS_PER_SEC;

    // A bubble can contain an enemy.
    private Enemy contains;

    private long timeLeft = TIME_TO_ESCPAPE;

    /**
     * Did this bubble hit something?
     */
    private boolean hit = false;

    /**
     * Constructor of the Bubble Unit.
     * Input a byte to specify the amount of lives this unit has.
     * @param position The starting position of the bubble.
     * @param motion The starting motion of the bubble.
     */
    public Bubble(Point position, Point motion)
    {
        super(true, position, motion);
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

        if(this.contains != null)
        {
            timeLeft--;

            if(timeLeft <= 0)
            {
                this.contains.revive();
                this.contains.setLocation(this.location);
                level.addUnit(this.contains);
                this.kill();
            }
        }
    }

    @Override
    public Unit clone()
    {
        return new Bubble(location, motion);
    }

    @Override
    public Sprite getSprite()
    {
        if(this.contains == null)
        {
            return SS.getBubbleSprite();
        }

        return SS.getBubbleWithEnemySprite();
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
        this.setMotion(new Point(HIT_MOTION));
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
}
