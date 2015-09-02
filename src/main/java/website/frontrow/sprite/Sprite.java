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
     * @param width The width to draw the image.
     * @param height The height to draw the image.
     */
    public void draw(Graphics graphics, int x, int y, int width, int height);

    /**
     * Slices the image and returns the portion of the image, which is specified.
     * If the specified region is outside the image, nothing is returned.
     *
     * @param x The start x coordinate
     * @param y The start y coordinate
     * @param width The width of the new sprite.
     * @param height The height of the new sprite.
     */
    public void slice(int x, int y, int width, int height);

    /**
     * The width of the sprite.
     *
     * @return The width
     */
    public int getWidth();

    /**
     * The height of the sprite.
     *
     * @return  The height.
     */
    public int getHeigth();

}