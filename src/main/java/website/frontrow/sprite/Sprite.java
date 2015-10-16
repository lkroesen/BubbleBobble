package website.frontrow.sprite;

import java.awt.Graphics;

/**
 * An interface which, when implemented, is used to draw the object.
 */
public interface Sprite
{
    /**
     * Draws the sprites.
     *
     * @param graphics The graphics context in which is being drawn.
     * @param x The x location to draw at.
     * @param y The y location to draw at.
     */
    void draw(Graphics graphics, int x, int y);

    /**
     * Slices the image and returns the portion of the image, which is specified.
     * If the specified region is outside the image, an empty sprite is returned.
     *
     * @param x The start x coordinate
     * @param y The start y coordinate
     * @param width The width of the new sprite.
     * @param height The height of the new sprite.
     * @return The spliced image of the sprite.
     */

    Sprite slice(int x, int y, int width, int height);

    /**
     * The width of the sprite.
     *
     * @return The width
     */
    int getWidth();

    /**
     * The height of the sprite.
     *
     * @return  The height.
     */
     int getHeight();
}