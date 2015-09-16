package website.frontrow.game;

import website.frontrow.board.Player;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.util.ArrayList;
import java.util.List;

/**
 * The current state of the game.
 */
public class Game
        implements Logable
{
    private int score = 0;
    private Level currentLevel;
    private boolean running = false;

    private ArrayList<Player> players;

    private ArrayList<GameObserver> observers;

    /**
     * Constructor of Game.
     * @param level The current level the player can play.
     * @param players The players in this game.
     */
    public Game(Level level, ArrayList<Player> players)
    {
        this.currentLevel = level;
        this.players = players;
        this.observers = new ArrayList<>();
        addToLog("[GAME]\tGame Object Created");
    }

    /**
     * Tick the current level if the game is not paused.
     */
    public void tick()
    {
        if(running)
        {
            currentLevel.tick();
        }
        updateObservers();
    }

    /**
     * Start the game.
     */
    public void start()
    {
        running = true;
    }

    /**
     * Stop the game (pause it).
     */
    public void stop()
    {
        running = false;
    }

    /**
     * Whether the game is currently running.
     * @return Current running state of the game.
     */
    public boolean isRunning()
    {
        return running;
    }


    /**
     * Restarts the game and sets the score to 0.
     * TODO: Restart the level.
     */
    private void restart()
    {
        score = 0;
    }

    /**
     * Get the current score.
     * @return
     * Returns an int with the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Set the current score.
     * @param score
     * Input an int with the value to set the score with.
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * The level which is currently being played.
     * @return The level.
     */
    public Level getLevel()
    {
        return this.currentLevel;
    }

    /**
     * Returns the players in the game.
     * @return The list of players.
     */
    public List<Player> getPlayers()
    {
        return this.players;
    }

    /**
     * Registers an observer to this game.
     * @param o The observer.
     */
    public void registerObserver(GameObserver o)
    {
        observers.add(o);
    }

    /**
     * Removes an observer from this game.
     * @param o The observer to remove.
     */
    public void removeObserver(GameObserver o)
    {
        observers.remove(o);
    }

    /**
     * Update the observers.
     */
    public void updateObservers()
    {
        if(running)
        {
           observers.forEach(o -> o.gameStart());
        }

        if(!running)
        {
            observers.forEach(o -> o.gameStop());
        }
    }

    /**
     * Input log actions made by game.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
