package website.frontrow.keybindings;

import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;
import website.frontrow.ui.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for Binding Actions to keys.
 */
public final class BindActions
        implements Logable
{
    private static final BindActions INSTANCE = new BindActions();
    private Map<Integer, Action> mapping = new HashMap<>();

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
    public BindActions createFirstPlayerKeyMappings(Game game)
    {
        assert game.getPlayers().size() >= 1;
        Player player1 = game.getPlayers().get(0);

        mapping.put(KeyBinds.getKeyCodeFor(ActionType.JUMP, 0), () -> player1.jump());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.LEFT, 0), () -> player1.goLeft());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.RIGHT, 0), () -> player1.goRight());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.SHOOT, 0), () -> player1.shoot());

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
        Player player2 = game.getPlayers().get(1);

        mapping.put(KeyBinds.getKeyCodeFor(ActionType.JUMP, 1), () -> player2.jump());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.LEFT, 1), () -> player2.goLeft());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.RIGHT, 1), () -> player2.goRight());
        mapping.put(KeyBinds.getKeyCodeFor(ActionType.SHOOT, 1), () -> player2.shoot());

        return this;
    }

    /**
     * Creates the util bindings.
     * @return The object to allow daisy chaining.
     */
    public BindActions createUtilMappings()
    {
        mapping.put(KeyBinds.getKeyCodeForUtil(ActionType.VOLUME_DOWN),
                () -> MusicPlayer.getInstance().volumeAdjust(-1.0d));
        mapping.put(KeyBinds.getKeyCodeForUtil(ActionType.VOLUME_UP),
                () -> MusicPlayer.getInstance().volumeAdjust(1.0d));
        mapping.put(KeyBinds.getKeyCodeForUtil(ActionType.TOGGLE_PRINT_LOG),
                () -> Log.togglePrinting());
        mapping.put(KeyBinds.getKeyCodeForUtil(ActionType.STORE_LOG),
                () -> DumpLog.getInstance().createDump());

        return this;
    }

    /**
     * Returns the created mapping.
     * @return The mapping
     */
    public Map<Integer, Action> getMapping()
    {
        return mapping;
    }

    /**
     * Clears the generated mapping.
     */
    public void clearMappings()
    {
        this.mapping = new HashMap<>();
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
