package website.frontrow.util;

/**
 * A point or vector.
 */
public class Point {
    public double x;
    public double y;

    /**
     * A simple point
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Point)
        {
            Point that = (Point) other;
            return this.x == that.x &&
                    this.y == that.y;
        }
        return false;
    }
}
