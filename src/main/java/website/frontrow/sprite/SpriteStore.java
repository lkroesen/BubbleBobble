package website.frontrow.sprite;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * A store to get sprites.
 */
public class SpriteStore
{
    /**
     * A map to store sprite images in, so they don't need to be read every time.
     */
    private static Map<String, Sprite> spriteMap = new HashMap<>();

	/**
	 * Loads a sprite.
	 * @param resource The location of the file.
	 * @return The sprite.
	 */
	public Sprite loadSprite(String resource)
	{
		Sprite sprite = spriteMap.get(resource);
		if(sprite == null)
		{
			sprite = loadSpriteFromResource(resource);
			spriteMap.put(resource, sprite);
		}

		return sprite;
	}

	/**
	 * Returns a sprite based on the specified resource.
	 * @param resource The location of the file.
	 * @return The sprite.
	 */
	private Sprite loadSpriteFromResource(String resource)
	{
		return new StaticImageSprite(loadImage(resource));
	}

	/**
	 * Loads an image.
	 * @param resource The location of the image.
	 * @return The image
	 */
	public Image loadImage(String resource)
	{
		try (InputStream input = SpriteStore.class.getResourceAsStream(resource))
		{
			if(input == null)
			{
				throw new IOException("Could not find specified resource " + resource);
			}

			BufferedImage image = ImageIO.read(input);
			return image;
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not load image.", e);
		}
	}
}