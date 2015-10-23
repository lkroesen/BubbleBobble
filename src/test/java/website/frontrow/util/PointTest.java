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
        Point point = new Point(DEFAULT_X, DEFAULT_Y);
        assertEquals(point.getX(), DEFAULT_X, DEFAULT_X);
    }

    /**
     * Test the Y of the constructor.
     */
    @Test
    public void constructorYTest()
    {
        Point point = new Point(DEFAULT_X, DEFAULT_Y);
        assertEquals(point.getY(), DEFAULT_Y, DEFAULT_Y);
    }

    /**
     * Test equals with a different FIRST_TEST_POINT.
     */
    @Test
    public void equalsDifferentPointsTest()
    {
        Point point = new Point(ONE, ONE);
        Point point1 = new Point(DEFAULT_X, DEFAULT_Y);

        assertFalse(point.equals(point1));
    }

    /**
     * Test equals method with setters.
     */
    @Test
    public void equalsWithSettersTest()
    {
        Point point = new Point(ONE, ONE);
        Point point1 = new Point(DEFAULT_X, DEFAULT_Y);

        point.setX(DEFAULT_X);
        point.setY(DEFAULT_Y);

        assertTrue(point.equals(point1));
    }

    /**
     * Test equals with null.
     */
    @Test
    public void equalsNullTest()
    {
        Point point = null;
        Point point1 = new Point(DEFAULT_X, DEFAULT_Y);

        assertFalse(point1.equals(point));
    }

    /**
     * Test hashcode.
     */
    @Test
    public void hashCodeTest()
    {
        Point point = new Point(DEFAULT_X, DEFAULT_Y);
        int hash = PRIME
                * (PRIME + new Double(DEFAULT_X).hashCode()) + new Double(DEFAULT_Y).hashCode();
        assertEquals(point.hashCode(), hash);
    }

    /**
     * Test equals method with differing X.
     */
    @Test
    public void equalsWithDiffXTest()
    {
        Point point = new Point(ONE, DEFAULT_Y);
        Point point1 = new Point(DEFAULT_X, DEFAULT_Y);

        point.setX(DEFAULT_X);
        point.setY(DEFAULT_Y);

        assertTrue(point.equals(point1));
    }

    /**
     * Test equals method with differing Y.
     */
    @Test
    public void equalsWithDiffYTest()
    {
        Point point = new Point(DEFAULT_X, ONE);
        Point point1 = new Point(DEFAULT_X, DEFAULT_Y);

        point.setX(DEFAULT_X);
        point.setY(DEFAULT_Y);

        assertTrue(point.equals(point1));
    }

    /**
     * Test adding numbers together.
     */
    @Test
    public void addTest()
    {
        Point point = new Point(1, 0);
        Point point1 = new Point(2, 4);
        Point expected = new Point(3, 4);

        assertEquals(expected, point.add(point1));
    }

    /**
     * Test dividing numbers by a divisor.
     */
    @Test
    public void divideTest()
    {
        Point point = new Point(2, 0);
        Point expected = new Point(1, 0);

        assertEquals(expected, point.divide(2));
    }

    /**
     * Test dividing numbers by a divisor.
     */
    @Test
    public void subtractTest()
    {
        Point point = new Point(2, 0);
        Point expected = new Point(1, 0);

        assertEquals(expected, point.subtract(new Point(1, 0)));
    }

    /**
     * Test length.
     */
    @Test
    public void lengthTest1()
    {
        Point point = new Point(2, 0);
        assertEquals(2, point.length(), 0);
    }

    /**
     * Test length.
     */
    @Test
    public void lengthTest2()
    {
        Point point = new Point(0, 2);
        assertEquals(2, point.length(), 0);
    }

    /**
     * Test angle.
     */
    @Test
    public void angleTest1()
    {
        Point point = new Point(1, 0);
        assertEquals(0, point.angle(), 0.0001);
    }

    /**
     * Test angle.
     */
    @Test
    public void angleTest2()
    {
        Point point = new Point(0, 2);
        assertEquals(Math.PI / 2, point.angle(), 0.0001);
    }
}
