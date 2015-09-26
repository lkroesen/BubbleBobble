package website.frontrow.util;

/**
 * Denotes an axis aligned bounding box.
 */
public class AABB
{
    /**
     * Starting point.
     */
    private final Point start;

    /**
     * Ending point.
     */
    private final Point end;

    /**
     * Construct an AABB.
     * So that:
     * For start, both coordinates (x and y) are smaller than or equal to
     * the coordinates of end.
     * @param a Point a
     * @param b Point b
     */
    public AABB(Point a, Point b)
    {
        this.start = new Point(Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()));
        this.end = new Point(Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()));
    }

    /**
     * Get the starting point.
     * @return The starting point
     */
    public Point getStart()
    {
        return start;
    }

    /**
     * Get the ending point.
     * @return The ending point
     */
    public Point getEnd()
    {
        return end;
    }

    /**
     * Returns the range of x values in this AABB.
     * @return Range containing all x values.
     */
    public Range getXRange()
    {
        return new Range(start.getX(), end.getX());
    }

    /**
     * Returns the range of y values in this AABB.
     * @return Range containing all y values.
     */
    public Range getYRange()
    {
        return new Range(start.getY(), end.getY());
    }

    /**
     * Whether two AABB's overlap.
     * @param other The other AABB
     * @return Whether the two AABBs overlap
     */
    public boolean overlaps(AABB other)
    {
        if(!this.getXRange().overlaps(other.getXRange()))
        {
            return false;
        }

        if(!this.getYRange().overlaps(other.getYRange()))
        {
            return false;
        }

        return true;
    }
}
