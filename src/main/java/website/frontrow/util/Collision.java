package website.frontrow.util;

/**
 * Collision class.
 * Contains information about a collision.
 */
public class Collision
{
    private Point point;
    private boolean collided;

    /**
     * Create a collision with a point point and collision state collided.
     * @param point New position
     * @param collided Was there a collision.
     */
    public Collision(Point point, boolean collided)
    {
        this.point = point;
        this.collided = collided;
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
    public Point getPoint()
    {
        return point;
    }
}
