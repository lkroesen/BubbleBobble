package website.frontrow.level;

import org.junit.Test;
import website.frontrow.board.Bubble;
import website.frontrow.board.Mover;
import website.frontrow.board.Player;
import website.frontrow.util.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test the cells.
 */
public class CellTest
{
    /**
     * Test collide with point 0,0.
     */
    @Test
    public void testEmptyCollision()
    {
        Mover mover = new Player(new Point(0, 0));
        assertFalse(Cell.EMPTY.collides(mover.getMotion()));
    }

    /**
     * Test platform collision 0,5.
     */
    @Test
    public void testPlatformCollision1()
    {
        Mover mover = new Bubble(new Point(0, 0), new Point(0, 5));
        assertTrue(Cell.PLATFORM.collides(mover.getMotion()));
    }

    /**
     * Test platform collision with 0,0.
     */
    @Test
    public void testPlatformCollision2()
    {
        Mover mover = new Bubble(new Point(0, 0), new Point(0, 0));
        assertFalse(Cell.PLATFORM.collides(mover.getMotion()));
    }

    /**
     * Test Wall Collision.
     */
    @Test
    public void testWallCollision()
    {
        Mover mover = new Player(new Point(0, 0));
        assertTrue(Cell.WALL.collides(mover.getMotion()));
    }
}