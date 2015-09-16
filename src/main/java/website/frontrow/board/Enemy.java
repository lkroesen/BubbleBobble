package website.frontrow.board;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;
import website.frontrow.util.Point;

/**
 * Enemy Class.
 */
public class Enemy
        extends Mover
            implements Logable
{

	private static SpriteStore ss = new SpriteStore();
	
    /**
     * Constructor of the Enemy Unit.
     * Input a byte with the amount of lives the Enemy has.
     * @param position THe starting position of the enemy.
     */
    public Enemy(Point position)
    {
        super(true, position, new Point(0, 0));
        new Log();
        addToLog("[BUBBLE]\t[SPAWN]\tEnemy created.");
    }
    
    /**
     * Returns the sprite of the unit, Player/Enemy/Empty respectively.
     * @return The sprite.
     */
    @Override
    public Sprite getSprite()
    {
    	return ss.getEnemySprite(this.getDirection());
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
