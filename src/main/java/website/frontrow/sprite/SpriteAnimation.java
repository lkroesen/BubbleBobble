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
    private int currentFrameNum;

    /**
     * Stores the fps of the sprite.
     */
    private long millisecondsPerFrame;

    /**
     * Stores the time of the last update so we can calculate if the next frame has to be shown.
     */
    private long lastUpdate;

    /**
     * Constructor of SpriteAnimation.
     * @param frames Input the frames for the Sprite.
     * @param loop Boolean to decide if the animation loops.
     * @param active Boolean to decide if the animation is executed.
     * @param millisecondsPerFrame The number of milliseconds a frame is shown.
     */
    public SpriteAnimation(Sprite[] frames, boolean loop, boolean active, long millisecondsPerFrame)
    {
        assert frames.length > 0;

        this.frames = frames.clone();
        this.loop = loop;
        this.active = active;
        this.millisecondsPerFrame = millisecondsPerFrame;
        this.lastUpdate = System.currentTimeMillis();

        currentFrameNum = 0;
    }

    /**
     * Get the sprite of the currently active frame.
     * @return Returns the currently active frame.
     */
    private Sprite currentFrame()
    {
        return frames[currentFrameNum];
    }

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        update();
        currentFrame().draw(graphics, x, y, width, height);
    }

    @Override
    public Sprite slice(int x, int y, int width, int height)
    {
        return currentFrame().slice(x, y, width, height);
    }

    @Override
    public int getWidth()
    {
        return currentFrame().getWidth();
    }

    @Override
    public int getHeight()
    {
        return currentFrame().getHeight();
    }

    /**
     * Updates the frame index if the sprite is animated.
     */
    private void update()
    {
        long currentTime = System.currentTimeMillis();

        if(this.active)
        {
            while(lastUpdate < currentTime)
            {
                lastUpdate += millisecondsPerFrame;
                currentFrameNum++;
                if(loop)
                {
                    currentFrameNum %= frames.length;
                }
                else if (currentFrameNum == frames.length)
                {
                    active = false;
                }
            }
        }
        else
        {
            lastUpdate = currentTime;
        }
    }

    /**
     * Set if the sprites is animating.
     * @param active Active.
     */
    public void setActive(boolean active)
    {
        this.active = active;
    }
}
