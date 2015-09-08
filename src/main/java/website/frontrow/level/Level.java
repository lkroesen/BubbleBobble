package website.frontrow.level;

import website.frontrow.board.Unit;
import website.frontrow.util.Grid;

import java.awt.Graphics;

import java.util.ArrayList;

/**
 * Class containing a level and positions of entities therein.
 */
public class Level
{

    private ArrayList<Unit> units;
    private Grid<Cell> cells;

    /**
     * Constructor of Level.
     * @param units
     * Input an ArrayList of Unit.
     * @param cells
     * Input a Grid with E of Cell.
     */
    public Level(ArrayList<Unit> units, Grid<Cell> cells)
    {
        this.units = new ArrayList<Unit>(units);
        this.cells = new Grid<Cell>(cells);
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
     * Draw the level.
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
    }
}
