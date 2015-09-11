package website.frontrow;

/**
 * An interface which defines an observer for the game.
 */
public interface GameObserver
{
    /**
     * Is called when the game is stopped.
     */
    void gameStop();

    /**
     * Is called when the game is started.
     */
    void gameStart();
}
