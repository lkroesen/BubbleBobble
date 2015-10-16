package website.frontrow.keybindings;

import java.awt.event.KeyEvent;
import website.frontrow.board.Bubble;
import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.ui.Action;
import website.frontrow.ui.PlayerAction;
import website.frontrow.util.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for Binding Actions to keys.
 */
public final class BindActions
        implements Logable
{
    private static final BindActions INSTANCE = new BindActions();
    private Map<Integer, PlayerAction> mapIntActions = new HashMap<>();

    /**
     * Private constructor, because Singleton.
     */
    private BindActions()
    {

    }

    /**
     * Get the instance of the singleton.
     * @return Return the instance.
     */
    public static BindActions getInstance()
    {
        return INSTANCE;
    }

    /**
     * Creates the key bindings for a single player.
     * @param game Input the game object to influence.
     * @return Returns the object to allow daisy chaining.
     */
    public BindActions createSinglePlayerKeyMappings(Game game)
    {
        assert game.getPlayers().size() >= 1;

        mapIntActions.put(KeyEvent.VK_SPACE, JBubbleBobbleKeyAction.JUMP);

        return this;
    }

    /**
     * Creates the key bindings for the second player.
     * @param game The game to influence.
     * @return The object to allow daisy chaining.
     */
    public BindActions createSecondPlayerKeyMappings(Game game)
    {
        assert game.getPlayers().size() >= 2;

        return this;
    }

    /**
     * Creates the util bindings.
     * @param game The game to influence.
     * @return The object to allow daisy chaining.
     */
    public BindActions createUtilMappings(Game game)
    {
        return this;
    }

    /**
     * Returns the created mapping.
     * @return The mapping
     */
    public Map<Integer, PlayerAction> getMapping()
    {
        return mapIntActions;
    }

    public void clearMappings()
    {
        this.mapIntActions = new HashMap<>();
    }

    /**
     * Every class that implements Logable, must add actions to the log.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
