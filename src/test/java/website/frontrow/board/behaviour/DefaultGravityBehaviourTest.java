package website.frontrow.board.behaviour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Mover;
import website.frontrow.game.GameConstants;
import website.frontrow.util.Collision;
import website.frontrow.util.CollisionComputer;
import website.frontrow.util.Point;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;


/**
 * Tests the default gravity behaviour.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultGravityBehaviourTest
{

    /**
     * Tests that the correct behaviour is performed.
     */
    @Test
    public void testApply()
    {
        Mover mover = mock(Mover.class);
        CollisionComputer collisionComputer = mock(CollisionComputer.class);
        Collision collision = mock(Collision.class);
        Point motion = new Point(0, 0);

        when(collision.isCollided()).thenReturn(false);
        when(collisionComputer.findNextPosition(mover)).thenReturn(collision);
        when(mover.getMotion()).thenReturn(motion);

        DefaultGravityBehaviour defaultGravityBehaviour = DefaultGravityBehaviour.getInstance();

        defaultGravityBehaviour.apply(mover, collisionComputer);
        assertEquals(-GameConstants.GRAVITY, motion.getY(), 0.001);
    }

    /**
     * Tests that the correct behaviour is performed.
     */
    @Test
    public void testApplyWithCollision()
    {
        Mover mover = mock(Mover.class);
        CollisionComputer collisionComputer = mock(CollisionComputer.class);
        Collision collision = mock(Collision.class);
        Point motion = new Point(0, -5);

        when(collision.isCollided()).thenReturn(true);
        when(collisionComputer.findNextPosition(mover)).thenReturn(collision);
        when(mover.getMotion()).thenReturn(motion);

        DefaultGravityBehaviour defaultGravityBehaviour = DefaultGravityBehaviour.getInstance();

        defaultGravityBehaviour.apply(mover, collisionComputer);
        assertEquals(0, motion.getY(), 0.001);
    }
}