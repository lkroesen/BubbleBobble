package website.frontrow.level;

import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.util.CollisionComputer;
import website.frontrow.util.CollisionHandler;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class containing a level and positions of entities therein.
 */
public class Level
        implements  Logable
{
    private final CollisionComputer collisionComputer = new CollisionComputer(this);
    private final CollisionHandler collisionHandler = new CollisionHandler();

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Point> playerPositions = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();

    private boolean playersAlive = true;

    private ConcurrentLinkedQueue<Unit> toAdd = new ConcurrentLinkedQueue<>();

    private Grid<Cell> cells;

    private int numberOfEnemies = 0;

    private List<LevelObserver> observers = new ArrayList<>();

    /**
     * Duplicates the current level.
     * @return A duplicate of the current level.
     */
    public Level duplicate()
    {
        ArrayList<Unit> units = new ArrayList<>(this.getUnits().size());
        ArrayList<Point> playerPositions = new ArrayList<>(this.getPlayers().size());

        for (Unit unit: this.units)
        {
            Unit clone = unit.duplicate();
            units.add(clone);
        }
        for(Point spawnPoint: this.playerPositions)
        {
            playerPositions.add(new Point(spawnPoint));
        }

        addToLog("[LEVEL]\tCloning succeeded.");
        return new Level(units, this.getCells(), playerPositions);
    }

    /**
     * Constructor of Level.
     * @param units Input an ArrayList of Unit.
     * @param cells Input a Grid with E of Cell.
     * @param playerPositions Spawning positions of players.
     */
    public Level(ArrayList<Unit> units, Grid<Cell> cells, ArrayList<Point> playerPositions)
    {
        this.playerPositions = new ArrayList<>(playerPositions);
        this.units = new ArrayList<>(units);
        this.cells = new Grid<>(cells);

        //Abbreviated u because of lambda expression.
        units.stream().filter(this::isEnemy).forEach(u -> numberOfEnemies++);
        addToLog("[LEVEL]\tLevel Object created");
    }

    /**
     * Constructor of Level.
     * @param players Starting players.
     * @param units Input an ArrayList of Unit.
     * @param cells Input a Grid with E of Cell.
     */
    public Level(ArrayList<Player> players, ArrayList<Unit> units, Grid<Cell> cells)
    {
        this.players = new ArrayList<>(players);
        this.units = new ArrayList<>(units);
        this.cells = new Grid<>(cells);

        //Abbreviated u because of lambda expression.
        units.stream().filter(this::isEnemy).forEach(u -> numberOfEnemies++);
        addToLog("[LEVEL]\tLevel Object created");
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
                onUnitDeath(unit);
                // The unit died during the tick, and must be removed.
                it.remove();
            }
        }
    }

    /**
     * Handle special behavior on death.
     * @param unit Unit that has died.
     */
    // TODO: Replace this with a behavior design pattern maybe?
    private void onUnitDeath(Unit unit)
    {
        if(unit instanceof Bubble)
        {
            Bubble bubble = (Bubble) unit;
            if(bubble.getContains() != null && !bubble.getContains().isAlive())
            {
                this.setNumberOfEnemies(this.getNumberOfEnemies() - 1);
                updateObservers();
            }
        }
        else if(unit instanceof Player)
        {
            updatePlayerAliveState();
            updateObservers();
        }
    }

    /**
     * Check if all players are still alive.
     */
    private void updatePlayerAliveState()
    {
        playersAlive = false;

        for(Player player: players)
        {
            playersAlive = playersAlive | player.hasLives();
        }
    }

    /**
     * Draw the level. First draw the cell content's then the units.
     * @param graphics The graphics context to draw in.
     * @param x The x coordinate to draw the level at.
     * @param y The y coordinate to draw the level at.
     * @param width The width of the field the level has to draw itself on.
     * @param height The height of the field the level has to draw itself on.
     */
    public void draw(Graphics graphics, int x, int y, int width, int height)
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
                cellToDraw.draw(graphics,
                        x + i * cellWidth, y + v * cellHeight,
                        cellWidth, cellHeight);
            }
        }

        for (Unit unit : units)
        {
            unit.draw(graphics, x, y, cellWidth, cellHeight);
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
     * @param unit Unit
     * @return boolean
     */
    public boolean isEnemy(Unit unit)
    {
        return unit instanceof Enemy;
    }

    /**
     * Getter for numberOfEnemies.
     * @return numberOfEnemies int
     */
    public int getNumberOfEnemies()
    {
        return numberOfEnemies;
    }

    /**
     * Setter for numberOfEnemies.
     * @param numberOfEnemies int
     */
    public void setNumberOfEnemies(int numberOfEnemies)
    {
        this.numberOfEnemies = numberOfEnemies;
    }

    /**
     * Adds a level observer.
     * @param levelObserver LevelObserver
     */
    public void addObserver(LevelObserver levelObserver)
    {
        if(observers.contains(levelObserver))
        {
            return;
        }
        observers.add(levelObserver);
    }

    /**
     * Removes a level observer.
     * @param levelObserver LevelObserver
     */
    public void removeObserver(LevelObserver levelObserver)
    {
        observers.remove(levelObserver);
    }

    /**
     * Updates the observers about the state of the level.
     */
    public void updateObservers()
    {
        if (!playersAlive())
        {
            observers.forEach(Level.LevelObserver::levelLost);
        }

        if (!enemiesAlive())
        {
            observers.forEach(Level.LevelObserver::levelWon);
        }
    }

    /**
     * Getter for CollisionComputer.
     * @return collisionComputer for this level.
     */
    public CollisionComputer getCollisionComputer()
    {
        return this.collisionComputer;
    }

    /**
     * Getter for CollisionHandler.
     * @return collisionComputer for this level.
     */
    public CollisionHandler getCollisionHandler()
    {
        return this.collisionHandler;
    }

    /**
     * Returns whether or not there are still players alive in the level.
     * @return boolean (for now true)
     */
    public boolean playersAlive()
    {
        return playersAlive;
    }

    /**
     * Returns true if there are still numberOfEnemies alive in the level.
     * @return boolean
     */
    public boolean enemiesAlive()
    {
        return numberOfEnemies != 0;
    }

    /**
     * Add a player to a certain position.
     * @param player Player to add to this level.
     * @param spawnIndex Index to add him at.
     */
    public void registerPlayer(Player player, int spawnIndex)
    {
        if(player.isAlive() && playerPositions.size() > 0)
        {
            player.setLocation(playerPositions.get(spawnIndex));
            players.add(player);
            units.add(player);
            addToLog("[LEVEL]\tPlayer registered.");
        }
    }

    /**
     * Get the spawning positions for a level.
     * @return The spawning positions.
     */
    public ArrayList<Point> getPlayerPositions()
    {
        return playerPositions;
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
