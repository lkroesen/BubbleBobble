package website.frontrow.level;

import website.frontrow.board.Unit;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.util.ArrayList;

/**
 * A tool for creating level objects.
 */
public class LevelBuilder
{
    private Grid<Cell> grid;
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<Point> players = new ArrayList<>();

    /**
     * Prepare a levelbuilder for a certain width and height.
     * @param width Width of the level.
     * @param height Height of the level.
     */
    public LevelBuilder(int width, int height)
    {
        grid = new Grid<>(width, height);
    }

    /**
     * Construct a level object.
     * @return The new level object.
     */
    public Level build()
    {
        return new Level(units, grid, players);
    }

    /**
     * Add an unit.
     * @param unit Unit to add.
     */
    public void addUnit(Unit unit)
    {
        units.add(unit);
    }

    /**
     * Add a spawnpoint for players.
     * @param spawnPoint Player spawnpoint.
     */
    public void addPlayerSpawnPoint(Point spawnPoint)
    {
        players.add(spawnPoint);
    }

    /**
     * Set grid cell at a certain position.
     * @param x x position
     * @param y y position
     * @param type Type of cell.
     */
    public void setGridCell(int x, int y, Cell type)
    {
        grid.set(x, y, type);
    }

}
