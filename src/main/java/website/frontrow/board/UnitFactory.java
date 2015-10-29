package website.frontrow.board;

import website.frontrow.util.Point;

/**
 * Constructs units.
 */
public interface UnitFactory
{
    /**
     * Creates a player at a given location.
     * @param location Starting location of this player.
     * @return A player
     */
    Player createPlayer(Point location, int playerIndex);

    /**
     * Creates an enemy at a given position.
     * @param location Starting location of this enemy.
     * @return An enemy
     */
    Enemy createEnemy(Point location);

    /**
     * Creates a bubble.
     * At a given position,
     * moving at a certain rate,
     * created by a certain player.
     * @param location Starting location of this bubble.
     * @param motion Starting motion of this bubble.
     * @param creator Creator of this bubble.
     * @return A bubble
     */
    Bubble createBubble(Point location, Point motion, Player creator);
}
