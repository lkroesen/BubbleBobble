package website.frontrow.sprite;

import java.awt.Graphics;

/**
 * A Sprite Animation implementation.
 */
public class SpriteAnimation
        implements Sprite
{
    private Sprite[] frames;
    private boolean loop;
    private boolean active;
    private byte currentFrameNum;

    /**
     * Constructor of SpriteAnimation.
     * @param frames Input the frames for the Sprite.
     * @param loop Boolean to decide if the animation loops.
     * @param active Boolean to decide if the animation is executed.
     */
    public SpriteAnimation(Sprite[] frames, boolean loop, boolean active)
    {
        this.frames = frames;
        this.loop = loop;
        this.active = active;
        currentFrameNum = 0;
    }

    /**
     * Get the sprite of the currently active frame.
     * @return Returns the currently active frame.
     */
    private Sprite currFrame()
    {
        return frames[currentFrameNum];
    }

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        currFrame().draw(graphics, x, y, width, height);
    }

    @Override
    public Sprite slice(int x, int y, int width, int height)
    {
        return currFrame().slice(x, y, width, height);
    }

    @Override
    public int getWidth()
    {
        return currFrame().getWidth();
    }

    @Override
    public int getHeight()
    {
        return currFrame().getHeight();
    }
}
