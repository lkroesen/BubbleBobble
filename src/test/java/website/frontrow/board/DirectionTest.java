package website.frontrow.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the Direction Enum.
 */
public class DirectionTest
{
    /**
     * Test DeltaX for UP.
     */
    @Test
    public void testGetDeltaXUP()
    {
        assertEquals(Direction.UP.getDeltaX(), 0);
    }

    /**
     * Test DeltaY for UP.
     */
    @Test
    public void testGetDeltaYUP()
    {
        assertEquals(Direction.UP.getDeltaY(), -1);
    }

    /**
     * Test DeltaX for DOWN.
     */
    @Test
    public void testGetDeltaXDOWN()
    {
        assertEquals(Direction.DOWN.getDeltaX(), 0);
    }

    /**
     * Test DeltaY for DOWN.
     */
    @Test
    public void testGetDeltaYDOWN()
    {
        assertEquals(Direction.DOWN.getDeltaY(), 1);
    }

    /**
     * Test DeltaX for LEFT.
     */
    @Test
    public void testGetDeltaXLEFT()
    {
        assertEquals(Direction.LEFT.getDeltaX(), -1);
    }

    /**
     * Test DeltaY for LEFT.
     */
    @Test
    public void testGetDeltaYLEFT()
    {
        assertEquals(Direction.LEFT.getDeltaY(), 0);
    }

    /**
     * Test DeltaX for RIGHT.
     */
    @Test
    public void testGetDeltaXRIGHT()
    {
        assertEquals(Direction.RIGHT.getDeltaX(), 1);
    }

    /**
     * Test DeltaY for RIGHT.
     */
    @Test
    public void testGetDeltaYRIGHT()
    {
        assertEquals(Direction.RIGHT.getDeltaY(), 0);
    }
}