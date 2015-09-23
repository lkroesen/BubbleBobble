package website.frontrow.board;

import website.frontrow.game.GameConstants;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;
import website.frontrow.util.Point;

/**
 * The player as part of a game.
 */
public class Player
        extends Mover
            implements Logable
{

	private static SpriteStore ss = new SpriteStore();
	
    /**
     * The points accumulated by the player.
     */
    private int score;

    /**
     * The constructor of the Player Unit.
     * Input a byte with the amount of lives the Player has.
     * @param position A players starting position.
     */
    public Player(Point position)
    {
        super(true, position, new Point(0, 0));
        new Log();
        addToLog("[PLAYER]\t[SPAWN]\tPlayer created.");
    }

    /**
     * Returns the player's score.
     * @return score integer
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Adds p (points) to the score of the player, when it gets a pickup.
     * @param p integer
     */
    public void addScore(int p)
    {
        addToLog("[PLAYER]\t[SCORE]\tScore increased by " + p);
        score += p;
    }
    
    /**
     * Returns the sprite of the unit, Player/Enemy/Empty respectively.
     * @return The sprite.
     */
    @Override
    public Sprite getSprite()
    {
    	return ss.getPlayerSprite(this.getDirection());
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);

        if(getMotion().getY() == 0)
        {
            getMotion().setX(0);
        }
    }
    
    /**
     * Return the player speed multiplier.
     * @return the player speed multiplier.
     */
    @Override
    public double getUnitSpeedMultiplier()
    {
    	return GameConstants.PLAYER_SPEED_MULTIPLIER;
    }

    /**
     * Input an action that is perfored by the player class.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
