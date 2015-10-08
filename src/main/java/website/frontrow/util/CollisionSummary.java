package website.frontrow.util;


import website.frontrow.board.Mover;
import website.frontrow.level.Cell;

import java.util.Queue;

/**
 * CollisionSummary class.
 * Contains a summary about zero to a few collisions.
 */
public class CollisionSummary
{
    private Point location;
    private Point motion;
    private boolean collided;
    private Queue<Cell> cells;

    /**
     * Create a collision with a point point and collision state collided.
     * But is rather a summary of a full set of collisions, and rather than
     * containing a single cell, contains more cells, but only their types.
     * @param location Position before collision
     * @param current Position doing collision.
     * @param collided Was there a collision.
     * @param cells The cells that have been collided with.
     */
    public CollisionSummary(Point location, Point current, boolean collided, Queue<Cell> cells)
    {
        this.location = location;
        this.motion = current;
        this.collided = collided;
        this.cells = cells;
    }

    /**
     * Was there a collision.
     * @return Whether there was a collision
     */
    public boolean isCollided()
    {
        return collided;
    }

    /**
     * Get the new position.
     * @return The new position.
     */
    public Point getLocation()
    {
        return location;
    }

    /**
     * Point of collision.
     * @return Point at which the object collided.
     */
    public Point getMotion()
    {
        return motion;
    }

    /**
     * Run each call collision event that happened on a given cell.
     * @param mover Mover to run events on.
     */
    public void runCollisionEvents(Mover mover)
    {
        Cell cell;
        while((cell = cells.poll()) != null)
        {
            mover.onCollision(cell);
        }
    }
}
