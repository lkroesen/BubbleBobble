package website.frontrow.sprite;

import website.frontrow.board.Direction;

/**
 * Created by larsstegman on 08-09-15.
 */
public class SpriteStore
{
    private static ImageStore is = new ImageStore();

    /**
     * The wall sprite.
     * @return The sprite.
     */
    public Sprite getWallSprite()
    {
        return new StaticImageSprite(is.getWallImage());
    }
    
    /**
     * The platform sprite.
     * @return The sprite.
     */
    public Sprite getPlatformSprite()
    {
        return new StaticImageSprite(is.getPlatformImage());
    }
    
    /**
     * The player sprite, can differ depending on the units face direction.
     * @return The sprite.
     */
    public Sprite getPlayerSprite(Direction face)
    {
        if(face == Direction.LEFT){
        	return new StaticImageSprite(is.getPlayerLeftImage());
        }
        else if(face == Direction.RIGHT){
        	return new StaticImageSprite(is.getPlayerRightImage());
        }
        else {
        	throw new IllegalArgumentException(
        		"Sprite direction can only be left or right");
        }
    }
    
    /**
     * The enemy sprite, can differ depending on the units face direction.
     * @return The sprite.
     */
    public Sprite getEnemySprite(Direction face)
    {
        if(face == Direction.LEFT){
        	return new StaticImageSprite(is.getEnemyLeftImage());
        }
        else if(face == Direction.RIGHT){
        	return new StaticImageSprite(is.getEnemyRightImage());
        }
        else {
        	throw new IllegalArgumentException(
        		"Sprite direction can only be left or right");
        }
    }
}