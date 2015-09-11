package website.frontrow.level;

import website.frontrow.board.Unit;
import website.frontrow.sprite.EmptySprite;
import website.frontrow.sprite.Sprite;
import website.frontrow.sprite.SpriteStore;

import java.awt.Graphics;

/**
 * Something that fits in a single grid cell.
 */
public enum Cell
{
    /**
     * An empty cell.
     */
    EMPTY,
    /**
     * A wall.
     */
    WALL
    {
        @Override
        public boolean collides(Unit unit)
        {
            return true;
        }

        @Override
        public Sprite getSprite()
        {
            return ss.getWallSprite();
        }
    },
    /**
     * A platform.
     */
    PLATFORM
    {
        @Override
        public boolean collides(Unit unit)
        {
            return unit.getMotion().getY() > 0;
        }
        
        @Override
        public Sprite getSprite()
        {
            return ss.getPlatformSprite();
        }
    };

    private static SpriteStore ss = new SpriteStore();

    /**
     * Returns the sprite of the cell.
     * @return The sprite.
     */
    public Sprite getSprite()
    {
        return new EmptySprite();
    }

    /**
     * Would this type of cell collide with the unit in its current state.
     * @param unit The unit to use.
     * @return Whether we need to run collision code or not.
     */
    public boolean collides(Unit unit)
    {
        return false;
    }

    /**
     * Draws the cell.
     * @param g The graphics context to draw in.
     * @param x The x coordinate to draw the cell at.
     * @param y The y coordinate to draw the cell at.
     * @param width The width to draw the cell with.
     * @param height The height to draw the cell with.
     */
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        getSprite().draw(g, x, y, width, height);
    }
}
