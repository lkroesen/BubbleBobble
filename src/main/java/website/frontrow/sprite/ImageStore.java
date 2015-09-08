package website.frontrow.sprite;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * A store to get all the images.
 */
public class ImageStore
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
            return new ImageIcon(getImage("/block.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return new ImageIcon(getFileNotFoundImage());
        }
    }

    /**
     * Returns the image for a wall.
     * @return The image.
     */
    public Image getWallImage()
    {
        try
        {
            return getImage("/wall.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
        }
    }
    
    /**
     * Returns the image for a platform.
     * @return The image.
     */
    public Image getPlatformImage()
    {
        try
        {
            return getImage("/platform.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
        }
    }  
    
    //TODO: Add sprites for face directions, for both Player and Enemy. Left and Right are currently the same!
    
    /**
     * Returns the image for a player facing left.
     * @return The image.
     */
    public Image getPlayerLeftImage()
    {
        try
        {
            return getImage("/player.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
        }
    }
    
    /**
     * Returns the image for a player facing right.
     * @return The image.
     */
    public Image getPlayerRightImage()
    {
        try
        {
            return getImage("/player.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
        }
    }
    
    /**
     * Returns the image for a enemy facing left.
     * @return The image.
     */
    public Image getEnemyLeftImage()
    {
        try
        {
            return getImage("/enemy.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
        }
    }
    
    /**
     * Returns the image for a player facing left.
     * @return The image.
     */
    public Image getEnemyRightImage()
    {
        try
        {
            return getImage("/enemy.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return getFileNotFoundImage();
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

}
