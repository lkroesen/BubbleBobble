package website.frontrow.keybindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Class for keeping track of all keybindings.
 *
 * {@link java.awt.event.KeyEvent}
 */
public abstract class KeyBinds
{
    // TODO: Load previous bindings set in the game.
    public static int player1GoLeft = KeyEvent.VK_LEFT;
    public static int player1GoRight = KeyEvent.VK_RIGHT;
    public static int player1Jump = KeyEvent.VK_UP;
    public static int player1Shoot = KeyEvent.VK_Z;

    public static int player2GoLeft = KeyEvent.VK_A;
    public static int player2GoRight = KeyEvent.VK_D;
    public static int player2Jump = KeyEvent.VK_W;
    public static int player2Shoot = KeyEvent.VK_SHIFT;

    public static int utilToggleLog = KeyEvent.VK_F1;
    public static int utilDumpLog = KeyEvent.VK_F2;
    public static int utilVolumeUp = KeyEvent.VK_EQUALS;
    public static int utilVolumeDown = KeyEvent.VK_MINUS;

    /**
     * The first key is the action type, the first integer is the player index,
     * the last is the key code.
     */
    private static Map<ActionType, Map<Integer, Integer>> mapping;

    // Static initializer, look it up.
    static
    {
        mapping = new HashMap<>();
        mapping.put(ActionType.JUMP, new HashMap<>());
        mapping.put(ActionType.RIGHT, new HashMap<>());
        mapping.put(ActionType.LEFT, new HashMap<>());
        mapping.put(ActionType.SHOOT, new HashMap<>());
        mapping.put(ActionType.TOGGLE_PRINT_LOG, new HashMap<>());
        mapping.put(ActionType.STORE_LOG, new HashMap<>());
        mapping.put(ActionType.VOLUME_UP, new HashMap<>());
        mapping.put(ActionType.VOLUME_DOWN, new HashMap<>());

        setKeyCodeFor(ActionType.LEFT,  0, KeyEvent.VK_LEFT);
        setKeyCodeFor(ActionType.RIGHT, 0, KeyEvent.VK_RIGHT);
        setKeyCodeFor(ActionType.JUMP,  0, KeyEvent.VK_UP);
        setKeyCodeFor(ActionType.SHOOT, 0, KeyEvent.VK_Z);

        setKeyCodeFor(ActionType.LEFT,  1, KeyEvent.VK_A);
        setKeyCodeFor(ActionType.RIGHT, 1, KeyEvent.VK_D);
        setKeyCodeFor(ActionType.JUMP,  1, KeyEvent.VK_W);
        setKeyCodeFor(ActionType.SHOOT, 1, KeyEvent.VK_SHIFT);

        setKeyCodeForUtil(ActionType.TOGGLE_PRINT_LOG, KeyEvent.VK_F1);
        setKeyCodeForUtil(ActionType.STORE_LOG, KeyEvent.VK_F2);
        setKeyCodeForUtil(ActionType.VOLUME_UP, KeyEvent.VK_EQUALS);
        setKeyCodeForUtil(ActionType.VOLUME_DOWN, KeyEvent.VK_MINUS);
    }

    /**
     * Returns the key for the player for the specified action.
     * @param action The action to perform.
     * @param playerIndex The player to get the code for.
     * @return The key code.
     */
    public static int getKeyCodeFor(ActionType action, int playerIndex)
    {
        return mapping.get(action).get(playerIndex);
    }

    /**
     * Sets the key code for the specified action and player.
     * @param action The action to perform.
     * @param playerIndex The player to perform the action with.
     * @param keyCode The key code.
     */
    public static void setKeyCodeFor(ActionType action, int playerIndex, int keyCode)
    {
        Map<Integer, Integer> actionMap = mapping.get(action);
        actionMap.put(playerIndex, keyCode);
    }

    /**
     * Returns the key code for a util action.
     * @param action The action.
     * @return The key code.
     */
    public static int getKeyCodeForUtil(ActionType action)
    {
        return getKeyCodeFor(action, -1);
    }

    /**
     * Sets the key code for an util action.
     * @param action The action.
     * @param keyCode The new key code.
     */
    public static void setKeyCodeForUtil(ActionType action, int keyCode)
    {
        setKeyCodeFor(action, -1, keyCode);
    }
}
