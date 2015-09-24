package website.frontrow.board;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

import java.util.Map;

/**
 * Enemy Class.
 */
public class Enemy
        extends Mover
            implements Logable
{
    /**
     * Constructor of the Enemy Unit.
     * Input a byte with the amount of lives the Enemy has.
     * @param position The starting position of the enemy.
     * @param sprites The sprites of the enemy.
     */
    public Enemy(Point position, Map<Direction, Sprite> sprites)
    {
        super(true, position, new Point(0, 0), sprites);
        new Log();
        addToLog("[BUBBLE]\t[SPAWN]\tEnemy created.");
    }

    /**
     * Log actions that happened to an Enemy.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
