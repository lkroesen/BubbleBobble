package website.frontrow.ui;

import website.frontrow.board.Player;

/**
 * This action provides a game object to influence.
 */
public interface PlayerAction
{
    /**
     * This action influences a player.
     * @param player The player to control.
     */
    void doAction(Player player);
}
