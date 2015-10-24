package website.frontrow.board;

import website.frontrow.board.observer.PlayerObserver;
import website.frontrow.board.observer.ScoreReceiver;
import website.frontrow.game.Game;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The player as part of a game.
 */
public class Player
        extends Mover
        implements Logable,
                   ScoreReceiver
{
    /**
     * The points accumulated by the player.
     */
    private int score = 0;
    
    /**
     * The amount of lives the player has.
     */
    private int lives = GameConstants.DEFAULT_AMOUNT_OF_LIVES;

    /**
     * The player is invincible for a certain amount of time.
     */
    private static final int INVINCIBILITY_TICKS = GameConstants.TICKS_PER_SEC;

    /**
     * The amount of ticks the player is still immune to losing lives.
     */
    private int ticksLeft = 0;

    /**
     * The observers for this player.
     */
    private Set<PlayerObserver> observers = new HashSet<>();

    /**
     * Reset the player to the default state.
     */
    @Override
    public void reset()
    {
        super.reset();
        ticksLeft = 0;
        lives = GameConstants.DEFAULT_AMOUNT_OF_LIVES;
        score = 0;
    }

    /**
     * The constructor of the Player Unit.
     * @param position A players starting position.
     * @param sprites The sprite for this player.
     */
    public Player(Point position, Map<Direction, Sprite> sprites)
    {
        super(true, position, new Point(0, 0), sprites);
        addToLog("[PLAYER]\t[SPAWN]\tPlayer created with ID.");
    }

    /**
     * Returns the player's score.
     * @return score integer
     */
    public int getScore()
    {
        return score;
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);

        if(getMotion().getY() == 0)
        {
            getMotion().setX(0);
        }

        if(ticksLeft > 0)
        {
            ticksLeft--;
        }
        if(!invincible())
        {
            observers.forEach(o -> o.notInvincible(this));
        }
    }
    
    /**
     * Return the player speed multiplier.
     * @return the player speed multiplier.
     */
    @Override
    public double getSpeedMultiplier()
    {
        return GameConstants.PLAYER_SPEED_MULTIPLIER;
    }

    @Override
    public Unit duplicate()
    {
        return new Player(location, this.getSprites());
    }

    /**
     * Input an action that is perfored by the player class.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Handle getting hit.
     */
    public void onEnemyCollision()
    {
        if(ticksLeft <= 0)
        {
            loseLife();
            if(lives <= 0)
            {
                this.kill();
                observers.forEach(o -> o.playerDied(this));
            }
            else
            {
                ticksLeft = INVINCIBILITY_TICKS;
                observers.forEach(o -> o.invincible(this));
            }
        }
    }

    /**
     * Whether the player is invincible.
     * @return invincible.
     */
    public boolean invincible()
    {
        return ticksLeft > 0;
    }

    /**
     * A getter for lives.
     * @return lives integer
     */
    public int getLives() 
    {
        return lives;
    }
    
    /**
     * A setter for lives.
     * @param l integer
     */
    public void setLives(int l)
    {
        lives = l;
        observers.forEach(o -> o.livesChanged(this));
        addToLog("[PLAYER]\t Player's current amount of lives is now: " + lives);
    }
    
    /**
     * Decreases the number of lives by one.
     */
    public void loseLife()
    {
        lives--;
        observers.forEach(o -> o.livesChanged(this));
        addToLog("[PLAYER]\t Player lost a life, total lives is now: " + lives);
    }
    
    /**
     * Increases the number of lives by one.
     */
    public void addLife()
    {
        lives++;
        observers.forEach((o) -> o.livesChanged(this));
        addToLog("[PLAYER]\t Player earned a life, total lives is now: " + lives);
    }

    /**
     * Shoot a bubble.
     */
    public void shoot(Game game, UnitFactory unitFactory)
    {
        if(game.isRunning())
        {
            if(!this.isAlive())
            {
                return;
            }
            game.getLevel().addUnit(
                    unitFactory.createBubble(this.getLocation(),
                            new Point(this.getDirection().getDeltaX() * 4, 0),
                            this));
        }
    }

    /**
     * Returns whether or not the player still has lives left.
     * @return boolean
     */
    public boolean hasLives()
    {
        return lives > 0;
    }


    /**
     * Add an observer to this player. Observers get notified of changes in the player state.
     * @param playerObserver The observer.
     */
    public void addObserver(PlayerObserver playerObserver)
    {
        this.observers.add(playerObserver);
    }

    /**
     * Removes an observer from this player. Observers will no longer be notified of changes
     * @param playerObserver The observer.
     */
    public void removeObserver(PlayerObserver playerObserver)
    {
        this.observers.remove(playerObserver);
    }

    @Override
    public void increaseScoreWith(int value)
    {
        addToLog("[PLAYER]\t[SCORE]\tScore increased by " + value);
        this.score += value;
        observers.forEach(o -> o.scoreChanged(this));
    }

    /**
     * Return a Set with the current Observers.
     * @return Returns a Set with PlayerObserver.
     */
    public Set<PlayerObserver> getObservers()
    {
        return observers;
    }

}
