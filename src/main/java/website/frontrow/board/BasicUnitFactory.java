package website.frontrow.board;

import java.util.Map;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

/**
 * Constructs basic units.
 */
public class BasicUnitFactory
        implements UnitFactory
{
    @Override
    public Player createPlayer(Point location, int playerIndex)
    {
        Map<Direction, Sprite> playerSprite
                = JBubbleBobbleSprites.getInstance().getPlayerSprite(playerIndex);

        return new Player(location, playerSprite);
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
