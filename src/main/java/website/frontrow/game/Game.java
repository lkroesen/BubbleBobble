package website.frontrow.game;

import website.frontrow.board.Player;
import website.frontrow.level.Level;
import website.frontrow.level.Level.LevelObserver;

import website.frontrow.ui.JBubbleKeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The current state of the game.
 */
public class Game implements LevelObserver
{
    private int score = 0;
    private int currentIndex;
    private Level currentLevel;
    private ArrayList<Level> levelPack;

    private boolean running = false;

    private ArrayList<Player> players;

    private ArrayList<GameObserver> observers;

    private JBubbleKeyListener keyListener;

    /**
     * Constructor of Game.
     * @param levels All the levels of the game.
     * @param players The players in this game.
     */
    public Game(ArrayList<Level> levels, ArrayList<Player> players)
    {
        this.levelPack = levels;
        this.currentIndex = 0;
        this.currentLevel = levelPack.get(currentIndex);
        this.players = players;
        this.observers = new ArrayList<>();
    }

    /**
     * Tick the current level if the game is not paused.
     */
    public void tick()
    {
        if(running)
        {
            if(keyListener != null)
            {
                keyListener.update();
            }
            currentLevel.tick();

            if(currentLevel.getEnemies() == 0)
            {
                levelWon();
            }
        }
        updateObservers();
    }

    /**
     * Start the game.
     */
    public void start()
    {
        running = true;
        currentLevel.addObserver(this);
        System.out.println("Started");
    }

    /**
     * Stop the game (pause it).
     */
    public void stop()
    {
        running = false;
        System.out.println("Paused");
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
     * Sets the keyListener for this game.
     * @param keyListener The key listener to use.
     */
    public void setKeyListener(JBubbleKeyListener keyListener)
    {
        this.keyListener = keyListener;
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

    @Override
    public void levelWon()
    {
        stop();
        currentIndex++;

        if(currentIndex != levelPack.size())
        {
            currentLevel = levelPack.get(currentIndex);
            start();

        }
    }

    @Override
    public void levelLost()
    {
        stop();
    }
}
