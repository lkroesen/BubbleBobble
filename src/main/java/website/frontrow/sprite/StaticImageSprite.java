package website.frontrow.sprite;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * Created by larsstegman on 02-09-15.
 * A static image sprite which shows a static image.
 */
public class StaticImageSprite
    implements Sprite
{
    private static Image image;

    /**
     * Creates an image sprite.
     * @param image The image to use for the sprite.
     */
    StaticImageSprite(Image image)
    {
        this.image = image;
    }

    /**
     * Draws this sprite.
     * @param graphics The graphics context in which is being drawn.
     * @param x The x location to draw at.
     * @param y The y location to draw at.
     * @param width The width to draw the image.
     * @param height The height to draw the image.
     */
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        graphics.drawImage(image, x, x, width, height, null);
    }

    /**
     * Returns a slice of the image of this sprite.
     * @param x The start x coordinate
     * @param y The start y coordinate
     * @param width The width of the new sprite.
     * @param height The height of the new sprite.
     * @return The slice.
     */
    public Sprite slice(int x, int y, int width, int height)
    {
        if(withinImage(x, y) && withinImage(x + width - 1, y + height - 1))
        {
            BufferedImage bi = createTranslucentImage(width, height);
            bi.createGraphics().drawImage(image, x, y, width, height, null);
            return new StaticImageSprite(bi);
        }
        return new EmptySprite();
    }

    /**
     * Creates a buffered image which can display transparent colours.
     * @param width the width of the image.
     * @param height the height of the image.
     * @return A buffered image.
     */
    private BufferedImage createTranslucentImage(int width, int height)
    {
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        return gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    /**
     * The width of the image.
     * @return Width
     */
    public int getWidth()
    {
        return image.getWidth(null);
    }

    /**
     * The height of the image.
     * @return Height
     */
    public int getHeight()
    {
        return image.getHeight(null);
    }

    /**
     * Returns whether the point is within the image.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return Within the image.
     */
    private boolean withinImage(int x, int y)
    {
        return x < image.getWidth(null) && y < image.getHeight(null) && x >= 0 && y >= 0;
    }
}