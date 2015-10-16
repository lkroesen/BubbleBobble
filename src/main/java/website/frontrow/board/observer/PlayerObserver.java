package website.frontrow.board.observer;

import website.frontrow.board.Player;

/**
 * Anything that implements this interface can observe a player.
 */
public interface PlayerObserver
{
    /**
     * The number of lives of the player changed.
     * @param player the player which sent the notification
     */
    void livesChanged(Player player);

    /**
     * The player score changed.
     * @param player the player which sent the notification
     */
    void scoreChanged(Player player);

    /**
     * The player died.
     * @param player the player which sent the notification
     */
    void playerDied(Player player);

    /**
     * The player is invincible.
     * @param player the player which sent the notification
     */
    void invincible(Player player);

    /**
     * The player is no longer invincible.
     * @param player the player which sent the notification
     */
    void notInvincible(Player player);
}
