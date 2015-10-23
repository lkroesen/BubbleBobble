package website.frontrow.board;

import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.util.Point;

/**
 * Constructs basic units.
 */
public class BasicUnitFactory
        implements UnitFactory
{
    @Override
    public Player createPlayer(Point location)
    {
        return new Player(location,
                JBubbleBobbleSprites.getInstance().getPlayerSprite());
    }

    @Override
    public Enemy createEnemy(Point location)
    {
        return new Enemy(location,
                JBubbleBobbleSprites.getInstance().getEnemySprite());
    }

    @Override
    public Bubble createBubble(Point location, Point motion, Player creator)
    {
        return new Bubble(location, motion,
                JBubbleBobbleSprites.getInstance().getBubbleSprite(),
                creator);
    }
}
