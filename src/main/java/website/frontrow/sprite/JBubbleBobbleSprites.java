package website.frontrow.sprite;

import website.frontrow.board.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the sprites for JBubbleBobble.
 */
public final class JBubbleBobbleSprites
    extends SpriteStore
{
    private static final JBubbleBobbleSprites INSTANCE = new JBubbleBobbleSprites();

    /**
     * Private Constructor due to being a Singleton.
     */
    private JBubbleBobbleSprites()
    {

    }

    /**
     * Get the instance of the Singleton Class JBubbleBobbleSprites.
     * @return Returns the instance of the Class.
     */
    public static JBubbleBobbleSprites getInstance()
    {
        return INSTANCE;
    }

    /**
     * The order of the directions of the sprites in an image file.
     */
    private static final Direction[] DIRECTIONS
            = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};

    /**
     * The size of a single sprite frame in pixels.
     */
    private static final int SPRITE_SIZE = 16; //pixels;


    /**
     * The wall sprite.
     * @return The sprite.
     */
    public Sprite getWallSprite()
    {
        return loadSprite("/sprites/wall.png");
    }

    /**
     * The bubble sprite.
     * @return The sprite.
     */
    public Map<Direction, Sprite> getBubbleSprite()
    {
        return getDirectionalSprite("/sprites/bubble.png");
    }

    /**
     * The platform sprites.
     * @return The sprite.
     */
    public Sprite getPlatformSprite()
    {
        return loadSprite("/sprites/platform.png");
    }

    /**
     * Creates a map with a sprite for each direction for the player.
     * @return The map.
     */
    public Map<Direction, Sprite> getPlayerSprite()
    {
        return getDirectionalSprite("/sprites/player.png");
    }

    /**
     * Creates a map with a sprite for each direction for the enemies.
     * @return The map.
     */
    public Map<Direction, Sprite> getEnemySprite()
    {
        return getDirectionalSprite("/sprites/enemy.png");
    }

    /**
     * Creates a map with a sprite for each direction for captured enemies.
     * @return The map.
     */
    public Map<Direction, Sprite> getCapturedEnemySprite()
    {
        return getDirectionalSprite("/sprites/Bubble_Containing_Zen.png");
    }

    @Override
    public Sprite loadSprite(String resource)
    {
        return super.loadSprite(resource);
    }

    /**
     * Creates a map with sprites for all directions.
     * @param resource The resource to cut the sprites from.
     * @return The map with sprites for all directions.
     */
    private Map<Direction, Sprite> getDirectionalSprite(String resource)
    {
        Sprite compoundSprites = loadSprite(resource);

        Map<Direction, Sprite> sprites = new HashMap<>();
        for(int i = 0; i < DIRECTIONS.length; i++)
        {
            Sprite directionSprite
                    = compoundSprites.slice(i * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE);
            sprites.put(DIRECTIONS[i], directionSprite);
        }

        return sprites;
    }
}
