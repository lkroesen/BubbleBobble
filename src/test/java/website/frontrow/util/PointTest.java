package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test the Point class.
 */
public class PointTest
{
    private static final double DEFAULT_X = 0d;
    private static final double DEFAULT_Y = 0d;
    private static final double ONE = 1d;
    private static final int    PRIME = 31;

    /**
     * Test the X of the constructor.
     */
    @Test
    public void constructorXTest()
    {
        Point p = new Point(DEFAULT_X, DEFAULT_Y);
        assertEquals(p.getX(), DEFAULT_X, DEFAULT_X);
    }

    /**
     * Test the Y of the constructor.
     */
    @Test
    public void constructorYTest()
    {
        Point p = new Point(DEFAULT_X, DEFAULT_Y);
        assertEquals(p.getY(), DEFAULT_Y, DEFAULT_Y);
    }

    /**
     * Test equals with a different point.
     */
    @Test
    public void equalsDifferentPointsTest()
    {
        Point p1 = new Point(ONE, ONE);
        Point p2 = new Point(DEFAULT_X, DEFAULT_Y);

        assertFalse(p1.equals(p2));
    }

    /**
     * Test equals method with setters.
     */
    @Test
    public void equalsWithSettersTest()
    {
        Point p1 = new Point(ONE, ONE);
        Point p2 = new Point(DEFAULT_X, DEFAULT_Y);

        p1.setX(DEFAULT_X);
        p1.setY(DEFAULT_Y);

        assertTrue(p1.equals(p2));
    }

    /**
     * Test equals with null.
     */
    @Test
    public void equalsNullTest()
    {
        Point p1 = null;
        Point p2 = new Point(DEFAULT_X, DEFAULT_Y);

        assertFalse(p2.equals(p1));
    }

    /**
     * Test hashcode.
     */
    @Test
    public void hashCodeTest()
    {
        Point p = new Point(DEFAULT_X, DEFAULT_Y);
        int hash = PRIME
                * (PRIME + new Double(DEFAULT_X).hashCode()) + new Double(DEFAULT_Y).hashCode();
        assertEquals(p.hashCode(), hash);
    }

    /**
     * Test equals method with differing X.
     */
    @Test
    public void equalsWithDiffXTest()
    {
        Point p1 = new Point(ONE, DEFAULT_Y);
        Point p2 = new Point(DEFAULT_X, DEFAULT_Y);

        p1.setX(DEFAULT_X);
        p1.setY(DEFAULT_Y);

        assertTrue(p1.equals(p2));
    }

    /**
     * Test equals method with differing Y.
     */
    @Test
    public void equalsWithDiffYTest()
    {
        Point p1 = new Point(DEFAULT_X, ONE);
        Point p2 = new Point(DEFAULT_X, DEFAULT_Y);

        p1.setX(DEFAULT_X);
        p1.setY(DEFAULT_Y);

        assertTrue(p1.equals(p2));
    }

    /**
     * Test adding numbers together.
     */
    @Test
    public void addTest()
    {
        Point a = new Point(1, 0);
        Point b = new Point(2, 4);
        Point expected = new Point(3, 4);

        assertEquals(expected, a.add(b));
    }

    /**
     * Test dividing numbers by a divisor.
     */
    @Test
    public void divideTest()
    {
        Point a = new Point(2, 0);
        Point expected = new Point(1, 0);

        assertEquals(expected, a.divide(2));
    }

    /**
     * Test dividing numbers by a divisor.
     */
    @Test
    public void subtractTest()
    {
        Point a = new Point(2, 0);
        Point expected = new Point(1, 0);

        assertEquals(expected, a.subtract(new Point(1, 0)));
    }

    /**
     * Test length.
     */
    @Test
    public void lengthTest1()
    {
        Point a = new Point(2, 0);
        assertEquals(2, a.length(), 0);
    }

    /**
     * Test length.
     */
    @Test
    public void lengthTest2()
    {
        Point a = new Point(0, 2);
        assertEquals(2, a.length(), 0);
    }

    /**
     * Test angle.
     */
    @Test
    public void angleTest1()
    {
        Point a = new Point(1, 0);
        assertEquals(0, a.angle(), 0.0001);
    }

    /**
     * Test angle.
     */
    @Test
    public void angleTest2()
    {
        Point a = new Point(0, 2);
        assertEquals(Math.PI / 2, a.angle(), 0.0001);
    }
}
