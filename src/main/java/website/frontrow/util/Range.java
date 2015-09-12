package website.frontrow.util;

/**
 * Detones a continuous range.
 */
public class Range {
    /**
     * The starting value.
     */
    private final double start;

    /**
     * The ending value.
     */
    private final double end;

    /**
     * Create a range going from start to end.
     * Start is always the minimum.
     * End always the maximum.
     *
     * As such,
     * if x <= y, then start is x and end is y.
     * else (x > y), then start is y and end is x.
     * @param x Starting value.
     * @param y Ending value.
     */
    public Range(double x, double y)
    {
        this.start = Math.min(x, y);
        this.end = Math.max(x, y);
    }

    /**
     * Get the starting value of this range.
     *
     * getStart always returns a value lower or equal to getEnd.
     * @return The starting value.
     */
    public double getStart()
    {
        return start;
    }

    /**
     * Get the ending value of this range.
     *
     * getEnd always returns a value higher or equal to getStart.
     * @return The ending value.
     */
    public double getEnd()
    {
        return end;
    }

    /**
     * Get the length of this range.
     * @return The length of this range.
     */
    public double length()
    {
        return end - start;
    }

    /**
     * Checks whether two ranges overlap.
     * @param other The range to check against.
     * @return Whether the two ranges overlap.
     */
    public boolean overlaps(Range other)
    {
        return this.getStart() <= other.getEnd() && other.getStart() <= this.getEnd();
    }
}
