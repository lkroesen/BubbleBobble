package website.frontrow.level;

import website.frontrow.board.Unit;
import website.frontrow.util.Grid;

import java.util.ArrayList;

/**
 * Class containing a level and positions of entities therein.
 */
public class Level {

    private ArrayList<Unit> units;
    private Grid<Cell> cells;

    public Level(ArrayList<Unit> units, Grid<Cell> cells)
    {
        this.units = new ArrayList<Unit>(units);
        this.cells = new Grid<Cell>(cells);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Grid<Cell> getCells() {
        return cells;
    }
}
