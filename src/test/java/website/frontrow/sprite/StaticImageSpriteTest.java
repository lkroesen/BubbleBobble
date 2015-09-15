package website.frontrow.sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by larsstegman on 06-09-15.
 */
public class StaticImageSpriteTest extends SpriteTest
{
    /**
     * Creates an image and returns it.
     * @return returns the test image
     * @throws IOException Throws Exception when triggered.
     */
    public Image createTestImage() throws IOException
    {
        InputStream imageStream = getClass().getResourceAsStream("/testImage100x100.png");
        Image testImage = ImageIO.read(imageStream);
        return testImage;
    }

    /**
     * Returns a static image sprite.
     * @return Returns a StaticImageSprite out of test image.
     * @throws IOException  Throws Exception when triggered.
     */
    public Sprite createSprite() throws IOException
    {
        return new StaticImageSprite(createTestImage());
    }
}
