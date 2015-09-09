package website.frontrow.sprite;

import java.util.HashMap;
import java.util.Map;

import website.frontrow.board.Direction;

/**
 * A store to get sprites.
 */
public class SpriteStore
{
    private static ImageStore is = new ImageStore();
    
    /**
     * A map to store sprite images in, so they don't need to be read every time.
     */
    private static Map<String, StaticImageSprite> spriteMap
			= new HashMap<String, StaticImageSprite>();

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
    		sprite = new StaticImageSprite(is.getWallImage());
    		spriteMap.put("Wall", sprite);
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
    public Sprite getPlayerSprite(Direction face)
    {
    	StaticImageSprite sprite;
    	
    	switch(face)
        {
            case LEFT:
            	sprite = spriteMap.get("PlayerLeft");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
                {
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
            		sprite = new StaticImageSprite(is.getPlayerRightImage());
            		spriteMap.put("PlayerRight", sprite);
            		return sprite;
            	}
            default:
                throw new IllegalArgumentException("Sprite direction can only be left or right");
        }
    }
    
    /**
     * The enemy sprite, can differ depending on the units face direction.
     * @param face The direction the enemy is facing.
     *             Allowed values: LEFT, RIGHT.
     * @return The sprite.
     */
    public Sprite getEnemySprite(Direction face)
    {
        StaticImageSprite sprite;
        
    	switch(face)
        {

        	case LEFT:
            	sprite = spriteMap.get("EnemyLeft");
            	
            	if(sprite != null)
                {
            		return sprite;
            	}
            	else
                {
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
            		sprite = new StaticImageSprite(is.getEnemyRightImage());
            		spriteMap.put("EnemyRight", sprite);
            		return sprite;
            	}
            default:
                throw new IllegalArgumentException("Sprite direction can only be left or right");
        }
    }
}