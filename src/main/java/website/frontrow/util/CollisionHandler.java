package website.frontrow.util;

import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * Handles collisions.
 */
public class CollisionHandler
    implements Logable
{

    /**
     * Call the method that handles collisions.
     * @param collider The mover that initiates the collision.
     * @param colidee The mover that is being collided with.
     */
    // TODO: Swap this out for something fancy that uses reflection. But this works fine for now.
    public void applyCollision(Unit collider, Unit colidee)
    {
        if(collider instanceof Player)
        {
            addToLog("[CH]\tPlayer collided with something.");
            playerCollision((Player) collider, colidee);
        }
        else if (colidee instanceof Player)
        {
            addToLog("[CH]\tSomething collided with a Player.");
            playerCollision((Player) colidee, collider);
        }

        if(collider instanceof Bubble)
        {
            bubbleCollision((Bubble) collider, colidee);
        }
        else if(colidee instanceof Bubble)
        {
            addToLog("[CH]\tSomething collided with a Bubble.");
            bubbleCollision((Bubble) colidee, collider);
        }
    }

    /**
     * Called when a player collides with another mover.
     * @param player Player which is currently colliding.
     * @param other The mover that was collided with.
     */
    public void playerCollision(Player player, Unit other)
    {
        // Pop the bubble.
        if(other instanceof Bubble && ((Bubble) other).isHit())
        {
            other.kill();
        }

        // Kill the player.
        if(other instanceof Enemy)
        {
            player.onEnemyCollision();
        }
    }

    /**
     * Called when a bubble collides with another mover.
     * @param bubble Bubble which is currently colliding.
     * @param other The mover that was collided with.
     */
    public void bubbleCollision(Bubble bubble, Unit other)
    {
        if(other instanceof Enemy && bubble.getContains() == null)
        {
            bubble.setLocation(other.getLocation());
            bubble.capture((Enemy) other);
        }
    }

    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
