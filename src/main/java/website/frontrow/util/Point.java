package website.frontrow.util;

/**
 * A point or vector.
 */
public class Point
{

    private double x;
    private double y;

    /**
     * Clone a point.
     * @param point Point to duplicate.
     */
    public Point(Point point)
    {
        this.x = point.x;
        this.y = point.y;
    }

    /**
     * A simple point.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x value.
     * @return The value of x.
     */
    public double getX()
    {
        return x;
    }

    /**
     * Set the x value.
     * @param x The value to set x to.
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Get the y value.
     * @return The value of y.
     */
    public double getY()
    {
        return y;
    }

    /**
     * Set the y value.
     * @param y The value to set x to.
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Add two points together.
     * @param b The point to add.
     * @return The result.
     */
    public Point add(Point b)
    {
        return new Point(this.x + b.x, this.y + b.y);
    }

    /**
     * Divide both coordinates of a point by a divisor.
     * @param divisor The number to devide by.
     * @return A point with both coordinates devided by the divisor.
     */
    public Point divide(double divisor)
    {
        return new Point(this.x / divisor, this.y / divisor);
    }

    /**
     * Subtract a point from the current point.
     * @param b Point to subtract.
     * @return Result of subtraction.
     */
    public Point subtract(Point b)
    {
        return new Point(this.x - b.x, this.y - b.y);
    }

    /**
     * Multiply both coordinates of a point by a number.
     * @param b The number to multiply by.
     * @return A point with both coordinates multiplied by b.
     */
    public Point multiply(double b)
    {
        return new Point(this.x * b, this.y * b);
    }

    /**
     * Calculates the distance to the origin squared.
     * @return The distance to the origin squared
     */
    public double lengthsq()
    {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2);
    }

    /**
     * Calculates the distance to the origin squared.
     * @return The distance to the origin squared
     */
    public double length()
    {
        return Math.sqrt(this.lengthsq());
    }

    /**
     * Get the angle in radians as used by polar coordinates.
     * @return Angle in -pi to pi range.
     */
    public double angle()
    {
        return Math.atan2(this.y, this.x);
    }


    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Point)
        {
            Point that = (Point) other;
            return  this.x == that.x
                    &  this.y == that.y;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Point(" + x + ", " + y + ")";
    }

    @SuppressWarnings("checkstyle:magicnumber")
    private static int prime = 31;
    @Override
    public int hashCode()
    {
        return  prime * (prime + new Double(x).hashCode()) + new Double(y).hashCode();
    }
}
