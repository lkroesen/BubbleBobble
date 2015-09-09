package website.frontrow.util;
// This class pretty much does not work with the rest of the code
// as-is at the moment of writing and adding this comment.
//
// It makes assumptions that are incorrect,
// and as such will not be able to be used and must be rewritten.


import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Square;

/**
 * Created by lkroesen on 02/09/2015.
 */
public class CollisionHandler
{
    // TODO get Grid from level/game
    private Grid<Square> grid;
    /**
     * TEMPORARY input a grid to read from.
     * @param grid
     * Input the grid to use.
     */
    public CollisionHandler(Grid<Square> grid)
    {
        // TODO get Grid from level/game
        this.grid = grid;
    }

    /**
     * This method checks if the unit that will move
     * to the next square is going to collide with another Unit.
     *
     * This method's main use is for quickly checking if a collision
     * takes place.
     * @param xCurr
     * Input the X coordinate of the current location.
     * @param yCurr
     * Input the Y coordinate of the current location.
     * @param xTowards
     * Input the X coordinate of the location to move to.
     * @param yTowards
     * Input the Y coordinate of the location to move to.
     * @return
     * Return true if a collision is detected, false if no collision.
     */
    public boolean checkCollision(int xCurr, int yCurr, int xTowards, int yTowards)
    {
        Square cSquare = grid.get(xCurr, yCurr);

        if (cSquare.getOccupied() instanceof Unit)
        {
            Square otherSquare = grid.get(xTowards, yTowards);
            if (otherSquare.getOccupied() instanceof Unit)
            {
                collisionApplyer(cSquare.getOccupied(), otherSquare.getOccupied(), yCurr, yTowards);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            /*  No collision is possible because there's no unit on the square inputted,
                this will be caused by not calling the function correctly. */
            throw new NullPointerException(
                    "No unit is occupying the Square at the specified space.");
        }
    }

    /**
     * This method applies the effect that is caused by the collision of 2 Units.
     * <ul>
     *     <li>Player moves onto an Enemy : Lose 1 life</li>
     *     <li>Enemy moves onto a Player : Lose 1 life</li>
     *     <li>Player hits a bubble : Player Jumps</li>
     *     <li>Bubble hits a wall : Bubble loses 1 "life"</li>
     *     <li>Bubble contains an enemy and gets hit by the player from the top
     *          : Enemy lose 1 life</li>
     * </ul>
     * @param uCurrent
     * Input the Unit that initiated the move.
     * @param uOther
     * Input the Unit that gets moved onto.
     * @param currentY
     * Input the Y of the original place.
     * @param otherY
     * Input the Y of the new place.
     *
     */
    private void collisionApplyer(Unit uCurrent, Unit uOther, int currentY, int otherY)
    {
        if (uCurrent instanceof Player)
        {
            if (uOther instanceof Enemy)
            {
                // Meep
            }
            else if (uOther instanceof Bubble)
            {
                // TODO Perform Jump Action
                if (((Bubble) uOther).getContains() instanceof Enemy)
                {
                    // Only allowed when the player jumped from a height that was higher
                    if (currentY > otherY)
                    {
                        // Beep
                    }
                }
            }
            // TODO Player hits wall? Wall as Unit?
        }
        else if (uCurrent instanceof Enemy)
        {
            if (uOther instanceof Player)
            {
                // Watch out with this, because if the enemy and player moved to the same spot
                // at the same time, that would cause an instakill
                // TODO: Add Invincibility frames.
                // BOOP
            }
        }
        else if (uCurrent instanceof Bubble)
        {
            if (uOther instanceof Player)
            {
                // Only allowed when the player(Other) jumped from a height that was higher
                if (otherY > currentY)
                {
                    // Dun dun
                }
            }
        }
    }
}
