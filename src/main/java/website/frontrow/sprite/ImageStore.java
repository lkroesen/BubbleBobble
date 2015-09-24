package website.frontrow.sprite;

import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Transparency;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * A store to get all the images.
 */
public class ImageStore
    implements Logable
{

    private static final int STANDARD_IMAGE_FORMAT = 32;

    /**
     * Returns an icon to be used for the border.
     * @return The border icom.
     */
    public ImageIcon getBorderImage()
    {
        try
        {
            return new ImageIcon(getImage("/sprites/block.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            addToLog("[ERROR]\t[IMAGESTORE]\t1 - IOException in getBorderImage().");
            new DumpLog();
            return new ImageIcon(getFileNotFoundImage());
        }
    }

    /**
     * Loads the image.
     * @param filename The filename
     * @return The wanted picture.
     * @throws IOException The file might not be found.
     */
    public Image getImage(String filename) throws IOException
    {
        InputStream imageStream = getClass().getResourceAsStream(filename);
        return ImageIO.read(imageStream);
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
     * Creates a temporary blue image as a placeholder.
     * @return The image.
     */
    private Image getFileNotFoundImage()
    {
        BufferedImage bi = new BufferedImage(STANDARD_IMAGE_FORMAT, STANDARD_IMAGE_FORMAT,
                BufferedImage.TYPE_INT_ARGB);
        Graphics imageGraphics = bi.createGraphics();

        imageGraphics.setColor(Color.CYAN);
        imageGraphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        return bi;
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
