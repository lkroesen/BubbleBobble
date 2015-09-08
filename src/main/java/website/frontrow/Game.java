package website.frontrow;

import website.frontrow.level.Cell;
import website.frontrow.util.Grid;

/**
 * The current state of the game.
 */
public class Game
{
    private static final int BOARD_WIDTH = 36;
    private static final int BOARD_HEIGHT = 36;

    private int highscore;
    private Grid<Cell> grid;

    /**
     * Constructor of Game.
     */
    public Game()
    {
        highscore = 0;
        grid = new Grid<Cell>(BOARD_WIDTH, BOARD_HEIGHT);
    }

    /**
     * Restarts the game and sets the highscore to 0.
     */
    private void restart()
    {
        highscore = 0;
    }

    /**
     * Get the current highscore.
     * @return
     * Returns an int with the highscore
     */
    public int getHighscore()
    {
        return highscore;
    }

    /**
     * Set the current highscore.
     * @param highscore
     * Input an int with the value to set the highscore with.
     */
    public void setHighscore(int highscore)
    {
        this.highscore = highscore;
    }

    /**
     * Get the grid used in the game.
     * @return
     * Returns a Grid class with the grid the game uses.
     */
    public Grid getGrid()
    {
        return grid;
    }

    /**
     * Set the grid for the game to use.
     * @param grid
     * Input a Grid for the game to use.
     */
    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }
}
