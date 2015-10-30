package website.frontrow.game;

import website.frontrow.board.Player;
import website.frontrow.board.UnitFactory;
import website.frontrow.util.keymap.KeyAction;
import website.frontrow.util.keymap.SingleKeyAction;

/**
 * Player actions.
 */
public class PlayerActions
{
    private SingleKeyAction jump;
    private SingleKeyAction left;
    private SingleKeyAction right;
    private SingleKeyAction shoot;

    /**
     * Create a PlayerActions object.
     * @param player The player for which these actions are meant.
     * @param game The current game.
     * @param unitFactory The factory to create bubbles with.
     */
    public PlayerActions(Player player, Game game, UnitFactory unitFactory)
    {
        this.jump = new SingleKeyAction(() -> player.jump(), true);
        this.left = new SingleKeyAction(() -> player.goLeft(), false);
        this.right = new SingleKeyAction(() -> player.goRight(), false);
        this.shoot = new SingleKeyAction(() -> player.shoot(game, unitFactory), true);
    }

    /**
     * Get the jump action.
     * (For example to rebind)
     * @return The jump action.
     */
    public SingleKeyAction getJump()
    {
        return jump;
    }

    /**
     * Get the go left action.
     * (For example to rebind)
     * @return The go left action.
     */
    public SingleKeyAction getLeft()
    {
        return left;
    }

    /**
     * Get the go right action.
     * (For example to rebind)
     * @return The go right action.
     */
    public SingleKeyAction getRight()
    {
        return right;
    }

    /**
     * Get the shoot action.
     * (For example to rebind)
     * @return The shoot action.
     */
    public SingleKeyAction getShoot()
    {
        return shoot;
    }


}
