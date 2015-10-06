package website.frontrow.board.behaviour;

import website.frontrow.board.Mover;
import website.frontrow.util.CollisionComputer;

/**
 * The gravity behaviour for bubbles.
 */
public class BubbleGravityBehaviour
    implements GravityBehaviour
{
    /**
     * Provides the gravity behaviour for bubbles. As bubbles are not affected by gravity, it just
     * returns the existing motion.
     * @param mover The mover to apply the gravity to.
     * @param cc The collision computer which has to be used for the calculation.
     */
    public void apply(Mover mover, CollisionComputer cc)
    {
        // Bubble is not affected by gravity.
    }
}
