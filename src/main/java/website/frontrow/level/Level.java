package website.frontrow.level;

import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.util.Grid;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class containing a level and positions of entities therein.
 */
public class Level
{

    private ArrayList<Player> players;
    private ArrayList<Unit> units;
    private Grid<Cell> cells;

    /**
     * Constructor of Level.
     * @param units
     *      Input an ArrayList of Unit.
     * @param cells
     *      Input a Grid with E of Cell.
     * @param players
     *      The players in the game.
     */
    public Level(ArrayList<Player> players, ArrayList<Unit> units, Grid<Cell> cells)
    {
        this.players = players;
        this.units = new ArrayList<>(units);
        this.cells = new Grid<>(cells);
    }

    /**
     * Returns the ArrayList<Unit>.
     * @return
     * Returns an ArrayList of Unit.
     */
    public ArrayList<Unit> getUnits()
    {
        return units;
    }

    /**
     * Returns the Grid.
     * @return
     * Returns a Grid with E of Cell.
     */
    public Grid<Cell> getCells()
    {
        return cells;
    }


    /**
     * Tick all entities in this level.
     */
    public void tick()
    {
        for (Unit unit : units)
        {
            unit.tick(this);
        }
    }

    /**
     * Draw the level. First draw the cell content's then the units.
     * @param g The graphics context to draw in.
     * @param x The x coordinate to draw the level at.
     * @param y The y coordinate to draw the level at.
     * @param width The width of the field the level has to draw itself on.
     * @param height The height of the field the level has to draw itself on.
     */
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        int numberOfCellsInWidth = cells.getWidth();
        int numberOfCellsInHeight = cells.getHeight();

        int cellWidth = width / numberOfCellsInWidth;
        int cellHeight = height / numberOfCellsInHeight;

        for(int i = 0; i < numberOfCellsInWidth; i++)
        {
            for(int v = 0; v < numberOfCellsInHeight; v++)
            {
                Cell cellToDraw = cells.get(i, v);
                cellToDraw.draw(g,
                        x + i * cellWidth, y + v * cellHeight,
                        cellWidth, cellHeight);
                
                
            }
        }
        
        for(int i = 0; i < units.size(); i++)
        {
        	units.get(i).draw(g, x, y, cellWidth, cellHeight);
        }
    }

    /**
     * Returns the players in the active game.
     * @return The players.
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
}
