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

    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof EmptySprite))
        {
            return false;
        }
        EmptySprite otherSprite = (EmptySprite) other;
        if(otherSprite.getWidth() != getWidth())
        {
            return false;
        }

        if(otherSprite.getHeight() != getHeight())
        {
            return false;
        }

        return true;
    }

    /**
     * The hashcode for this object.
     * @return hashcode.
     */
    @Override
    public int hashCode()
    {
        return 0;
    }
}
