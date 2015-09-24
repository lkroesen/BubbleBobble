package website.frontrow.board;

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

    // A bubble can contain an enemy.
    private Enemy contains;

    /**
     * Constructor of the Bubble Unit.
     * Input a byte to specify the amount of lives this unit has.
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
    public void onWallCollision()
    {
        addToLog("[BUBBLE]\tHit wall.");
        this.kill();
    }

    @Override
    public void applyGravity()
    {
        // Ignore gravity.
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
