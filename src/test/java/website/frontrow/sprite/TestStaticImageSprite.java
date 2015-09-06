package website.frontrow.sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by larsstegman on 06-09-15.
 */
public class TestStaticImageSprite extends SpriteTest
{

    public Image createTestImage() throws IOException
    {
        InputStream imageStream = getClass().getResourceAsStream("/testImage100x100.png");
        Image testImage = ImageIO.read(imageStream);
        return testImage;
    }

    public Sprite createSprite() throws IOException
    {
        return new StaticImageSprite(createTestImage());
    }
}
