package website.frontrow.board;

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

    private final static Point HIT_MOTION = new Point(0, -2);

    // A bubble can contain an enemy.
    private Enemy contains;

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

    /**
     * Set an enemy to be contained by a bubble.
     * @param contains
     * Sets the enemy as contained by the bubble.
     */
    public void setContains(Enemy contains)
    {
        this.contains = contains;
    }

    @Override
    public Sprite getSprite()
    {
        return SS.getBubbleSprite();
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
