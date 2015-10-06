package website.frontrow.board.behaviour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Mover;
import website.frontrow.util.CollisionComputer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * A test for the bubble gravity behaviour.
 */
@RunWith(MockitoJUnitRunner.class)
public class BubbleGravityBehaviourTest
{

    /**
     * This test makes sure no interactions are made with the mover when dealing with a bubble.
     */
    @Test
    public void testApply()
    {
        Mover mover = mock(Mover.class);
        CollisionComputer collisionComputer = mock(CollisionComputer.class);

        BubbleGravityBehaviour bubbleGravityBehaviour = new BubbleGravityBehaviour();
        bubbleGravityBehaviour.apply(mover, collisionComputer);

        verifyZeroInteractions(mover);
        verifyZeroInteractions(collisionComputer);
    }
}