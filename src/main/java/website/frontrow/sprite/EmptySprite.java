package website.frontrow.sprite;

import java.awt.Graphics;

/**
 * An empty image sprite, which draws nothing.
 */
public class EmptySprite
    implements Sprite
{
    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        // Nothing
    }

    @Override
    public Sprite slice(int x, int y, int width, int height)
    {
        return new EmptySprite();
    }

    @Override
    public int getWidth()
    {
        return 0;
    }

    @Override
    public int getHeight()
    {
        return 0;
    }
}
