package website.frontrow.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the grid class.
 */
public class GridTest {

    /**
     * Test the constructor
     */
    @Test
    public void testConstructor()
    {
        Grid<Object> obj = new Grid<Object>(10, 5);
        assertEquals(10, obj.getWidth());
        assertEquals(5, obj.getHeight());
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
     * Test left in point.
     */
    @Test
     public void horizontalInsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 0, 2);
    }

    /**
     * Test left out point.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(-1, 0, 2);
    }

    /**
     * Test right in point.
     */
    @Test
    public void horizontalInsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(3, 0, 2);
    }

    /**
     * Test right out point.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void horizontalOutsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(4, 0, 2);
    }
    /**
     * VERTICAL
     */
    /**
     * Test top in point.
     */
    @Test
    public void verticalInsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 0, 2);
    }

    /**
     * Test top out point.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideSetTest1()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, -1, 2);
    }

    /**
     * Test bottom in point.
     */
    @Test
    public void verticalInsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 3, 2);
    }

    /**
     * Test right out point.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void verticalOutsideSetTest2()
    {
        Grid<Object> obj = new Grid<Object>(4, 4);
        obj.set(0, 4, 2);
    }
}
