package website.frontrow;

import website.frontrow.Level.Square;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Game
{
    int highscore;
    Square[][] grid;

    /**
     * Constructor of Game
     */
    public Game()
    {
        highscore = 0;
    }

    /**
     * Restarts the game and sets the highscore to 0
     */
    private void Restart()
    {
        highscore = 0;
    }
}
