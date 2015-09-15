package website.frontrow.util;

/**
 * Collision
 */
public class Collision
{
    private Point point;
    private boolean collided;

    public Collision(Point point, boolean collided)
    {
        this.point = point;
        this.collided = collided;
    }

    public boolean isCollided() {
        return collided;
    }

    public Point getPoint() {
        return point;
    }
}
