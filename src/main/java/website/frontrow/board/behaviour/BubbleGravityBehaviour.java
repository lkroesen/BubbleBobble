package website.frontrow.board.behaviour;

import website.frontrow.board.Mover;
import website.frontrow.util.CollisionComputer;

/**
 * The gravity behaviour for bubbles.
 */
public final class BubbleGravityBehaviour
    implements GravityBehaviour
{

    private static final BubbleGravityBehaviour INSTANCE = new BubbleGravityBehaviour();

    /**
     * A private constructor for the singleton design pattern.
     */
    private BubbleGravityBehaviour()
    {

    }

    /**
     * Returns the instance for the behaviour.
     * @return The behaviour
     */
    public static BubbleGravityBehaviour getInstance()
    {
        return INSTANCE;
    }

    /**
     * Provides the gravity behaviour for bubbles. As bubbles are not affected by gravity, it just
     * returns the existing motion.
     * @param mover The mover to apply the gravity to.
     * @param cc The collision computer which has to be used for the calculation.
     */
    public void apply(Mover mover, CollisionComputer cc)
    {
    }
}
