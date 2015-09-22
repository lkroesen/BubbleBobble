package website.frontrow.level;

import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.util.CollisionHandler;
import website.frontrow.util.Grid;
import website.frontrow.util.RealCollisionHandler;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class containing a level and positions of entities therein.
 */
public class Level
    implements Logable
{

    private ConcurrentLinkedQueue<Unit> toAdd = new ConcurrentLinkedQueue<>();

    private ArrayList<Player> players;
    private ArrayList<Unit> units;
    private Grid<Cell> cells;
    private int enemies;

    private List<LevelObserver> observers;

    private final CollisionHandler collisionHandler = new CollisionHandler(this);
    private final RealCollisionHandler realCollisionHandler = new RealCollisionHandler();

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
        addToLog("[LEVEL]\tLevel Object created");

        int e = 0;
        ArrayList<Unit> list = units;
        for(int i = 0; i < list.size(); i++)
        {
            if (isEnemy(list.get(i)))
            {
                e++;
            }
        }

        this.enemies = e;

        this.observers = new ArrayList<>();

    }

    /**
     * Returns a copy of ArrayList<Unit>.
     * (Be warned, it IS a copy, you cannot add anything through this!)
     * @return
     * Returns a copy of the list of units.
     */
    public ArrayList<Unit> getUnits()
    {
        return new ArrayList<>(units);
    }

    /**
     * Add a unit to the queue.
     * @param unit Unit to add to the level.
     */
    public void addUnit(Unit unit)
    {
        toAdd.add(unit);
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
        while(!toAdd.isEmpty())
        {
            units.add(toAdd.poll());
        }
        Unit unit;
        Iterator<Unit> it = units.iterator();
        while (it.hasNext())
        {
            unit = it.next();
            unit.tick(this);
            if(!unit.isAlive())
            {
                if(isEnemy(unit))
                {
                    setEnemies(enemies - 1);
                }
                // The unit died during the tick, and must be removed.
                it.remove();
            }
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

    /**
     * Input an action as a String.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Returns whether or not a unit is an enemy.
     * @param u Unit
     * @return boolean
     */
    public boolean isEnemy(Unit u)
    {
        return u instanceof Enemy;
    }

    /**
     * Getter for enemies.
     * @return enemies int
     */
    public int getEnemies()
    {
        return enemies;
    }

    /**
     * Setter for enemies.
     * @param e int
     */
    public void setEnemies(int e)
    {
        enemies = e;
    }

    /**
     * Adds a level observer.
     * @param o LevelObserver
     */
    public void addObserver(LevelObserver o)
    {

        if(observers.contains(o))
        {
            return;
        }
        observers.add(o);

    }

    /**
     * Removes a level observer.
     * @param o LevelObserver
     */
    public void removeObserver(LevelObserver o)
    {
        observers.remove(o);
    }

    /**
     * Updates the observers about the state of the level.
     */
    public void updateObservers()
    {

        if (!playersAlive())
        {
            for(LevelObserver o : observers)
            {
                o.levelLost();
            }
        }
        if (!enemiesAlive())
        {
            for(LevelObserver o : observers)
            {
                o.levelWon();
            }
        }

    }

    /**
     * @return collisionComputer for this level.
     */
    public CollisionHandler getCollisionHandler()
    {
        return this.collisionHandler;
    }

    /**
     * @return collisionHandler for this level.
     */
    public RealCollisionHandler getRealCollisionHandler()
    {
        return this.realCollisionHandler;
    }

    /**
     * Returns whether or not there are still players alive in the level.
     * @return boolean (for now true)
     */
    public boolean playersAlive()
    {
        return true;
    }

    /**
     * Returns true if there are still enemies alive in the level.
     * @return boolean
     */
    public boolean enemiesAlive()
    {
        return (enemies != 0);
    }

    /**
     * Observer that will be notified when the level is won or lost.
     */
    public interface LevelObserver
    {

        /**
         * The level is won. If it's the last level, the game is won.
         * Otherwise the next level will be loaded.
         */
        void levelWon();

        /**
         * The level is lost.
         * This results in a game over screen.
         */
        void levelLost();

    }

}
