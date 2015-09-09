package website.frontrow.sprite;

import website.frontrow.board.Direction;

/**
 * Created by larsstegman on 08-09-15.
 * A store to get sprites.
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
     * @param face The direction the player is facing.
     *             Allowed values: LEFT, RIGHT.
     * @return The sprite.
     */
    public Sprite getPlayerSprite(Direction face)
    {
        switch(face)
        {
            case LEFT:
                return new StaticImageSprite(is.getPlayerLeftImage());
            case RIGHT:
                return new StaticImageSprite(is.getPlayerRightImage());
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
        switch(face)
        {
            case LEFT:
                return new StaticImageSprite(is.getEnemyLeftImage());
            case RIGHT:
                return new StaticImageSprite(is.getEnemyRightImage());
            default:
                throw new IllegalArgumentException("Sprite direction can only be left or right");
        }
    }
}