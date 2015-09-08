package website.frontrow.sprite;

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
}