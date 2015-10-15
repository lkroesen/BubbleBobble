package website.frontrow.board.observer;

/**
 * Anything which implements this interface can provide a score which represents the value of it.
 */
public interface ScoreProvider
{
    /**
     * Returns the value of the object.
     * @return The value this object is worth.
     */
    int getWorth();
}
