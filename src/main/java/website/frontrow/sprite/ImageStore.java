package website.frontrow.sprite;


import javax.swing.ImageIcon;

/**
 * Created by larsstegman on 03-09-15.
 */
public class ImageStore
{
    private static final String BORDER_IMAGE_FILE_NAME = "/block.jpg";

    /**
     * Returns an icon to be used for the border.
     * @return The border icom.
     */
    public ImageIcon getBorderImage()
    {
        return new ImageIcon(getClass().getResource(BORDER_IMAGE_FILE_NAME));
    }
}
