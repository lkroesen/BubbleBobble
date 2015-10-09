package website.frontrow.util;

/**
 * Collision class.
 * Contains information about a collision.
 */
public class Collision
{
    private Point last;
    private Point current;
    private boolean collided;
    private Point cell;

    /**
     * Create a collision with a point point and collision state collided.
     * @param last Position before collision
     * @param current Position doing collision.
     * @param collided Was there a collision.
     * @param cell The cell that has collided.
     */
    public Collision(Point last, Point current, boolean collided, Point cell)
    {
        this.last = last;
        this.current = current;
        this.collided = collided;
        this.cell = cell;
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
        return last;
    }

    /**
     * Point of collision.
     * @return Point at which the object collided.
     */
    public Point getCurrent()
    {
        return current;
    }

    /**
     * Get the cell that caused this collision.
     * @return The cell that caused this collision.
     */
    public Point getCell()
    {
        return cell;
    }
}
