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
public class ImageStore
    implements Logable
{

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
        catch (IOException e)
        {
            throw new RuntimeException("Could not load icon " + filename, e);
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
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        return gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
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
