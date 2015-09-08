package website.frontrow.board;

import website.frontrow.util.Point;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Enemy extends Unit
{

    /**
     * Constructor of the Enemy Unit.
     * Input a byte with the amount of lives the Enemy has.
     * @param position THe starting position of the enemy.
     */
    public Enemy(Point position)
    {
        super(true, position, new Point(0, 0));
    }
}
