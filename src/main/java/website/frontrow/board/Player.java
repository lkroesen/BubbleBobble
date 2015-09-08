package website.frontrow.board;

import website.frontrow.util.Point;

/**
 * The player as part of a game.
 */
public class Player extends Unit
{

    /**
     * The points accumulated by the player.
     */
    private int score;

    /**
     * The constructor of the Player Unit.
     * Input a byte with the amount of lives the Player has.
     * @param position A players starting position.
     */
    public Player(Point position)
    {
        super(true, position, new Point(0, 0));
    }

    /**
     * Returns the player's score.
     * @return score integer
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Adds p (points) to the score of the player, when it gets a pickup.
     * @param p integer
     */
    public void addScore(int p)
    {
        score += p;
    }
}
