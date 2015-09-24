package website.frontrow.sprite;

import java.util.HashMap;
import java.util.Map;

import website.frontrow.board.Direction;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * A store to get sprites.
 */
public class SpriteStore
		implements Logable
{
    private static ImageStore is = new ImageStore();
    
    /**
     * A map to store sprite images in, so they don't need to be read every time.
     */
    private static Map<String, StaticImageSprite> spriteMap = new HashMap<>();

    /**
     * The wall sprite.
     * @return The sprite.
     */
    public Sprite getWallSprite()
    {
    	StaticImageSprite sprite = spriteMap.get("Wall");
    	
    	if(sprite != null)
        {
    		return sprite;
    	}
    	else
        {
			addToLog("[WARNING]\t[SPRITESTORE]\tWall Sprite was null.");
    		sprite = new StaticImageSprite(is.getWallImage());
    		spriteMap.put("Wall", sprite);
    		return sprite;
    	}
        
    }

	/**
	 * The Bubble sprite.
	 * @return
	 * The sprite.
	 */
	public Sprite getBubbleSprite()
	{
		StaticImageSprite sprite = spriteMap.get("Bubble");

		if(sprite != null)
		{
			return sprite;
		}
		else
		{
			addToLog("[WARNING]\t[SPRITESTORE]\tBubble Sprite was null.");
			sprite = new StaticImageSprite(is.getBubbleImage());
			spriteMap.put("Bubble", sprite);
			return sprite;
		}
	}

	/**
	 * The Bubble with enemy sprite.
	 * @return
	 * The sprite.
	 */
	public Sprite getBubbleWithEnemySprite()
	{
		StaticImageSprite sprite = spriteMap.get("BubbleEnemy");

		if(sprite != null)
		{
			return sprite;
		}
		else
		{
			addToLog("[WARNING]\t[SPRITESTORE]\tBubble with enemy Sprite was null.");
			sprite = new StaticImageSprite(is.getBubbleWithEnemyImage());
			spriteMap.put("BubbleEnemy", sprite);
			return sprite;
		}
	}
    
    /**
     * The platform sprite.
     * @return The sprite.
     */
    public Sprite getPlatformSprite()
    {
    	StaticImageSprite sprite = spriteMap.get("Platform");
    	
    	if(sprite != null)
        {
    		return sprite;
    	}
    	else
        {
			addToLog("[WARNING]\t[SPRITESTORE]\tPlatform Sprite was null.");
    		sprite = new StaticImageSprite(is.getPlatformImage());
    		spriteMap.put("Platform", sprite);
    		return sprite;
    	}
    }
    
    /**
     * The player sprite, can differ depending on the units face direction.
     * @param face The direction the player is facing.
     *             Allowed values: LEFT, RIGHT.
     * @return The sprite.
     */
	// Don't suppress the method length warning, this method should really be refactored.
    public Sprite getPlayerSprite(Direction face)
    {
    	StaticImageSprite sprite;
    	
    	switch(face)
        {
			case UP:
			case DOWN:
			case LEFT:
            	sprite = spriteMap.get("PlayerLeft");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
                {
					addToLog("[WARNING]\t[SPRITESTORE]\tPlayer Sprite LEFT was null.");
            		sprite = new StaticImageSprite(is.getPlayerLeftImage());
            		spriteMap.put("PlayerLeft", sprite);
            		return sprite;
            	}
            case RIGHT:
            	sprite = spriteMap.get("PlayerRight");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
                {
					addToLog("[WARNING]\t[SPRITESTORE]\tPlayer Sprite RIGHT was null.");
            		sprite = new StaticImageSprite(is.getPlayerRightImage());
            		spriteMap.put("PlayerRight", sprite);
            		return sprite;
            	}
			default:
				return new EmptySprite();
        }
    }
    
    /**
     * The enemy sprite, can differ depending on the units face direction.
     * @param face The direction the enemy is facing.
     *             Allowed values: LEFT, RIGHT.
     * @return The sprite.
     */
	// Don't suppress the method length warning, this method should really be refactored.
    public Sprite getEnemySprite(Direction face)
    {
        StaticImageSprite sprite;
        
    	switch(face)
        {
			case UP:
			case DOWN:
        	case LEFT:
            	sprite = spriteMap.get("EnemyLeft");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
                {
					addToLog("[WARNING]\t[SPRITESTORE]\tEnemy Sprite LEFT was null.");
            		sprite = new StaticImageSprite(is.getEnemyLeftImage());
            		spriteMap.put("EnemyLeft", sprite);
            		return sprite;
            	}
            case RIGHT:
            	sprite = spriteMap.get("EnemyRight");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
				{
					addToLog("[WARNING]\t[SPRITESTORE]\tWall Sprite RIGHT was null.");
					sprite = new StaticImageSprite(is.getEnemyRightImage());
					spriteMap.put("EnemyRight", sprite);
					return sprite;
				}
			default: return new EmptySprite();
        }
    }

	/**
	 * Input a String to be logged.
	 * @param action Input a String that is the action performed.
	 */
	@Override
	public void addToLog(String action)
	{
		Log.add(action);
	}
}