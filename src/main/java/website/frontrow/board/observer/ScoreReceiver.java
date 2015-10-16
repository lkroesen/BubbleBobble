package website.frontrow.board.observer;

/**
 * Anything which implements this interface can let its score be increased.
 */
public interface ScoreReceiver
{
    /**
     * Increases the score with the specified value.
     * @param value The value to increase the score with.
     */
    void increaseScoreWith(int value);
}
