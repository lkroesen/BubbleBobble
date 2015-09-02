package website.frontrow.board;

import website.frontrow.level.Square;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Unit
{
    private byte alive;
    private Square[][] location;

    /**
     * Constructor of the Unit Class.
     * @param nAlive
     * Input the amount of lives the Unit has.
     */
    public Unit(byte nAlive)
    {
        alive = nAlive;
    }

    /**
     * Class Under Construction, Moves the unit to the desired space.
     * @param loc
     * Input a square[][] with the location to move towards.
     */
    private void moveTo(Square[][] loc)
    {

    }
}
