package website.frontrow.util;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test the grid class.
 */
public class GridTest
{

    /**
     * Test the standard constructor.
     */
    @Test
    public void testConstructor1()
    {
        Grid<Object> obj = new Grid<Object>(10, 5);
        assertEquals(10, obj.getWidth());
        assertEquals(5, obj.getHeight());
    }

    /**
     * Test the arraylist constructor with invalid arraylist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2()
    {
        new Grid<Object>(new ArrayList<Object>(), 2, 2);
    }

    /**
     * Test the get and set functionality.
     */
    @Test
    public void testSetAndGet()
    {
        Grid<Object> obj = new Grid<Object>(10, 5);

        obj.set(1, 1, 13);
        obj.set(1, 4, 8);
        obj.set(3, 1, 4);

        assertEquals(13, obj.get(1, 1));
        assertEquals(8, obj.get(1, 4));
        assertEquals(4, obj.get(3, 1));
    }


    /**
     *
     * BORDER TESTS for set
     *
     */
    /**
     * HORIZONTAL
     */
    /**
     * Test left in FIRST_TEST_POINT.
     */
    @Test
     public void horizontalInsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 0, 2);
    }

    /**
     * Test left out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(-1, 0, 2);
    }

    /**
     * Test right in FIRST_TEST_POINT.
     */
    @Test
    public void horizontalInsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(3, 0, 2);
    }

    /**
     * Test right out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(4, 0, 2);
    }
    /**
     * VERTICAL
     */
    /**
     * Test top in FIRST_TEST_POINT.
     */
    @Test
    public void verticalInsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 0, 2);
    }

    /**
     * Test top out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, -1, 2);
    }

    /**
     * Test bottom in FIRST_TEST_POINT.
     */
    @Test
    public void verticalInsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 3, 2);
    }

    /**
     * Test right out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 4, 2);
    }

    /**
     *
     * BORDER TESTS for get
     *
     */
    /**
     * HORIZONTAL
     */
    /**
     * Test left in FIRST_TEST_POINT.
     */
    @Test
    public void horizontalInsideGetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(0, 0);
    }

    /**
     * Test left out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideGetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(-1, 0);
    }

    /**
     * Test right in FIRST_TEST_POINT.
     */
    @Test
    public void horizontalInsideGetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(3, 0);
    }

    /**
     * Test right out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideGetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(4, 0);
    }
    /**
     * VERTICAL
     */
    /**
     * Test top in FIRST_TEST_POINT.
     */
    @Test
    public void verticalInsideGetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(0, 0);
    }

    /**
     * Test top out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideGetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(0, -1);
    }

    /**
     * Test bottom in FIRST_TEST_POINT.
     */
    @Test
    public void verticalInsideGetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(0, 3);
    }

    /**
     * Test right out FIRST_TEST_POINT.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideGetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.get(0, 4);
    }

    /**
     * Test equals which is absolutely by object not equal.
     */
    @Test
    public void testNotTypeEqual()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        // Use assertFalse instead of assertEquals, to make clear that we are testing equals.
        assertFalse(obj.equals("Cat"));
    }

    /**
     * Test the hashcode.
     */
    @Test
    public void testHashCode()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        assertEquals(1353309697, obj.hashCode());
    }
}
