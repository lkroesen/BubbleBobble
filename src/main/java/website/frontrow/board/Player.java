package website.frontrow.board;

import website.frontrow.util.Point;

/**
 * The player as part of a game.
 */
public class Player extends Unit
{

    /**
     * The constructor of the Player Unit.
     * Input a byte with the amount of lives the Player has.
     */
    public Player(Point position)
    {
        super(true, position, new Point(0, 0));
    }
}
