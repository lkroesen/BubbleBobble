package website.frontrow.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the enemy class
 */
public class EnemyTest extends UnitTest
{
    /**
     * Test the constructor of the Enemy class
     */
    @Test
    public void ConstructorTest()
    {
        Enemy e = new Enemy(super.p);
        assertEquals(e.getLocation(), super.p);
    }
}
