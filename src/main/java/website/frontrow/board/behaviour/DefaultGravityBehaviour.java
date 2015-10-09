package website.frontrow.board.behaviour;

import website.frontrow.board.Mover;
import website.frontrow.game.GameConstants;
import website.frontrow.util.CollisionComputer;
import website.frontrow.util.CollisionSummary;

/**
 * The default behaviour for movers affected by gravity.
 */
public final class DefaultGravityBehaviour
    implements GravityBehaviour
{

    private static final DefaultGravityBehaviour INSTANCE = new DefaultGravityBehaviour();

    /**
     * A private constructor for singleton.
     */
    private DefaultGravityBehaviour()
    {

    }

    /**
     * Returns the instance.
     * @return the instance.
     */
    public static DefaultGravityBehaviour getInstance()
    {
        return INSTANCE;
    }

    /**
     * Provides the default gravity behaviour.
     * @param mover The mover to apply the gravity to.
     * @param collisionComputer The collision computer which has to be used for the calculation.
     */
    public void apply(Mover mover, CollisionComputer collisionComputer)
    {
        mover.getMotion().setY(mover.getMotion().getY() - GameConstants.GRAVITY);

        CollisionSummary collision = collisionComputer.findNextPosition(mover);
        if(collision.isCollided())
        {
            mover.getMotion().setY(0.0);
        }

    }
}
