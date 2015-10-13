package website.frontrow.sprite;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.awt.Image;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * A store to get all the images.
 */
public final class ImageStore
    implements Logable
{
    /**
     * The instance.
     */
    public static final ImageStore INSTANCE = new ImageStore();

    /**
     * A private constructor for Singleton.
     */
    private ImageStore()
    {

    }

    /**
     * Gives the instance.
     * @return the instance.
     */
    public static ImageStore getInstance()
    {
        return INSTANCE;
    }

    /**
     * Creates an icon to indicate the number of lives left.
     * @return The Icon.
     */
    public ImageIcon getHeartIcon()
    {
        return getImageIcon("/sprites/heart.png");
    }

    /**
     * Loads an image icon.
     * @param filename The file you want to load.
     * @return The requested icon.
     */
    public ImageIcon getImageIcon(String filename)
    {
        try
        {
            return new ImageIcon(getImage(filename));
        }
        catch (IOException exception)
        {
            throw new RuntimeException("Could not load icon " + filename, exception);
        }
    }

    /**
     * Loads the image.
     * @param filename The filename
     * @return The wanted picture.
     * @throws IOException the file might not be found.
     */
    public Image getImage(String filename) throws IOException
    {
        try (InputStream input = ImageStore.class.getResourceAsStream(filename))
        {
            if(input == null)
            {
                throw new IOException("Could not find file " + filename);
            }

            return ImageIO.read(input);
        }

    }

    /**
     * Creates an empty buffered image which can display transparent colours.
     * @param width the width of the image.
     * @param height the height of the image.
     * @return A buffered image.
     */
    public static BufferedImage createTranslucentImage(int width, int height)
    {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        return graphicsConfiguration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    /**
     * Input a String to keep track of what's happening in the program.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
