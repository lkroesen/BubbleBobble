package website.frontrow.board.behaviour;

import website.frontrow.board.Mover;
import website.frontrow.util.CollisionComputer;

/**
 * This interface defines the methods which should be able to calculate gravity.
 */
public interface GravityBehaviour
{

    /**
     * This method applies gravity to a mover.
     * @param mover The mover to apply the gravity to.
     * @param collisionComputer The collision computer which has to be used for the calculation.
     */
    void apply(Mover mover, CollisionComputer collisionComputer);
}
