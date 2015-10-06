package website.frontrow.board.behaviour;

import website.frontrow.board.Mover;
import website.frontrow.game.GameConstants;
import website.frontrow.util.Collision;
import website.frontrow.util.CollisionComputer;

/**
 * The default behaviour for movers affected by gravity.
 */
public class DefaultGravityBehaviour
    implements GravityBehaviour
{

    /**
     * Provides the default gravity behaviour.
     * @param mover The mover to apply the gravity to.
     * @param cc The collision computer which has to be used for the calculation.
     */
    public void apply(Mover mover, CollisionComputer cc)
    {
        mover.getMotion().setY(mover.getMotion().getY() - GameConstants.GRAVITY);

        Collision c = cc.findNextPosition(mover);
        if(c.isCollided())
        {
            mover.getMotion().setY(0.0);
        }
    }
}
