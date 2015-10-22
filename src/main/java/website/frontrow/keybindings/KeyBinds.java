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
        Map<Integer, Integer> jumpMapping = new HashMap<>();
        jumpMapping.put(0, KeyEvent.VK_UP);
        jumpMapping.put(1, KeyEvent.VK_W);

        Map<Integer, Integer> rightMapping = new HashMap<>();
        rightMapping.put(0, KeyEvent.VK_RIGHT);
        rightMapping.put(1, KeyEvent.VK_D);

        Map<Integer, Integer> leftMapping = new HashMap<>();
        leftMapping.put(0, KeyEvent.VK_LEFT);
        leftMapping.put(1, KeyEvent.VK_A);

        Map<Integer, Integer> shootMapping = new HashMap<>();
        shootMapping.put(0, KeyEvent.VK_Z);
        shootMapping.put(1, KeyEvent.VK_SHIFT);

        Map<Integer, Integer> printToggleMapping = new HashMap<>();
        printToggleMapping.put(-1, KeyEvent.VK_F1);

        Map<Integer, Integer> dumpLogMapping = new HashMap<>();
        dumpLogMapping.put(-1, KeyEvent.VK_F2);

        Map<Integer, Integer> volumeUpMapping = new HashMap<>();
        volumeUpMapping.put(-1, KeyEvent.VK_EQUALS);

        Map<Integer, Integer> volumeDownMapping = new HashMap<>();
        volumeDownMapping.put(-1, KeyEvent.VK_MINUS);

        mapping = new HashMap<>();
        mapping.put(ActionType.JUMP, jumpMapping);
        mapping.put(ActionType.RIGHT, rightMapping);
        mapping.put(ActionType.LEFT, leftMapping);
        mapping.put(ActionType.SHOOT, shootMapping);
        mapping.put(ActionType.TOGGLE_PRINT_LOG, printToggleMapping);
        mapping.put(ActionType.STORE_LOG, dumpLogMapping);
        mapping.put(ActionType.VOLUME_UP, volumeUpMapping);
        mapping.put(ActionType.VOLUME_DOWN, volumeDownMapping);
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
