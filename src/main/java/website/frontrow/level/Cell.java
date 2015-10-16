package website.frontrow.level;

import website.frontrow.sprite.EmptySprite;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

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
        public boolean collides(Point movement)
        {
            return true;
        }

        @Override
        public Sprite getSprite()
        {
            return JBubbleBobbleSprites.getInstance().getWallSprite();
        }
    },

    /**
     * A platform.
     */
    PLATFORM
    {

        @Override
        public boolean collides(Point movement)
        {
            return movement.getY() > 0;
        }

        @SuppressWarnings("checkstyle:magicnumber")
        /* Magic number is AABB height. */
        @Override
        public Point getAABBDimensions()
        {
            return new Point(1, 0.2);
        }

        @Override
        public Point getAABBOffset()
        {
            return new Point(0, -1);
        }
        
        @Override
        public Sprite getSprite()
        {
            return JBubbleBobbleSprites.getInstance().getPlatformSprite();
        }
    };

    /**
     * Returns the sprite of the cell.
     * @return The sprite.
     */
    public Sprite getSprite()
    {
        return EmptySprite.getInstance();
    }

    /**
     * Would this type of cell collide with the unit in its current state.
     * @param movement The movement to keep in mind
     * @return Whether we need to run collision code or not.
     */
    public boolean collides(Point movement)
    {
        return false;
    }

    /**
     * The width and height of this tile.
     * @return The width and height of the AABB.
     */
    public Point getAABBDimensions()
    {
        return new Point(1, 1);
    }

    /**
     * Get the x and y offset of the AABB for this tile.
     * @return AABB offset.
     */
    public Point getAABBOffset()
    {
        return new Point(0, 0);
    }

    /**
     * Draws the cell.
     * @param graphics The graphics context to draw in.
     * @param x The x coordinate to draw the cell at.
     * @param y The y coordinate to draw the cell at.
     * @param width The width to draw the cell with.
     * @param height The height to draw the cell with.
     */
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        getSprite().draw(graphics, x, y, width, height);
    }
}
