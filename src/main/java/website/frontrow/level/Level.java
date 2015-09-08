package website.frontrow.level;

import website.frontrow.board.Unit;
import website.frontrow.util.Grid;

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
}
