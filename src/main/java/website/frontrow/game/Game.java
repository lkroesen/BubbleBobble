package website.frontrow.game;

import website.frontrow.board.Player;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.level.Level.LevelObserver;
import website.frontrow.music.AudioDetector;
import website.frontrow.music.MusicPlayer;
import website.frontrow.music.Songs;
import website.frontrow.ui.JBubbleKeyListener;

import java.util.ArrayList;

/**
 * The current state of the game.
 */
public class Game
        implements  Logable,
                    LevelObserver
{
    private ArrayList<GameObserver> observers = new ArrayList<>();
    private ArrayList<Level> levelPack = new ArrayList<>();

    private boolean running = false;

    private int currentIndex = 0;
    private int score = 0;

    private JBubbleKeyListener keyListener;

    private Level currentLevel;
    private Level gameOver;
    private Level gameWon;

    /**
     * Constructor of Game.
     * @param levels All the levels of the game.
     */
    public Game(ArrayList<Level> levels)
    {
        this.levelPack = levels;
        loadCurrentLevel();
        addToLog("[GAME]\tGame Object Created");
    }

    /**
     * Tick the current level if the game is not paused.
     */
    public void tick()
    {
        if (running)
        {
            if (keyListener != null)
            {
                keyListener.update();
            }

            currentLevel.tick();
        }

        updateObservers();
    }

    /**
     * Replaces the current level with a copy of the level at the current index.
     */
    private void loadCurrentLevel()
    {
        this.currentLevel = levelPack.get(currentIndex).duplicate();

        if(currentLevel != null)
        {
            currentLevel.addObserver(this);
        }
    }

    /**
     * Start the game.
     */
    public void start()
    {
        running = true;
        addToLog("[GAME]\tStarted game.");
    }

    /**
     * Stop the game (pause it).
     */
    public void stop()
    {
        running = false;
        addToLog("[GAME]\tStopped game.");
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
     * Get the current score.
     * @return Returns an int with the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Set the current score.
     * @param score Input an int with the value to set the score with.
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
    public ArrayList<Player> getPlayers()
    {
        return this.currentLevel.getPlayers();
    }

    /**
     * Loads the next level if there are next levels available.
     */
    public void nextLevel()
    {
        currentIndex = Math.min(currentIndex + 1, levelPack.size() - 1);
        loadCurrentLevel();
    }

    /**
     * Registers an observer to this game.
     * @param gameObserver The observer.
     */
    public void registerObserver(GameObserver gameObserver)
    {
        observers.add(gameObserver);
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
     * @param gameObserver The observer to remove.
     */
    public void removeObserver(GameObserver gameObserver)
    {
        observers.remove(gameObserver);
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

    @Override
    public void levelWon()
    {
        addToLog("[GAME]\t[WON]\tYou win this round.");
        if(currentIndex + 1 < levelPack.size())
        {
            nextLevel();
        }
        else
        {
            currentLevel = gameWon;

            if (!AudioDetector.isNoAudio())
            {
                MusicPlayer.stop();
                MusicPlayer.setLooping(false);
                MusicPlayer.selectSong(Songs.DECISIVE_VICTORY);
            }
        }
    }

    @Override
    public void levelLost()
    {
        stop();
        gameOver();
        addToLog("[GAME]\t[LOST]\tYou just lost the game, kind of.");
    }

	/**
	 * Adds Game Over message to log.
	 */
	public void gameOver() 
	{
		currentLevel = gameOver;

        if (!AudioDetector.isNoAudio())
        {
            MusicPlayer.stop();
            MusicPlayer.setLooping(false);
            MusicPlayer.selectSong(Songs.GAME_OVER);
        }
    }

	/**
	 * The setter for gameOver.
     * @param level Input a Level.
	 */
	public void setGameOver(Level level)
	{
		gameOver = level;
	}

    /**
     * The setter for GameWon.
     * @param level Input a Level.
     */
    public void setGameWon(Level level)
    {
        gameWon = level;
    }
}
