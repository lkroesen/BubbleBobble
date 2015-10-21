package website.frontrow.keybindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Class for keeping track of all keybindings.
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
    public static Map<ActionType, Map<Integer, Integer>> mapping;

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
        shootMapping.put(0, KeyEvent.VK_ALT);
        shootMapping.put(1, KeyEvent.VK_SHIFT);

        mapping = new HashMap<>();
        mapping.put(ActionType.JUMP, jumpMapping);
        mapping.put(ActionType.RIGHT, rightMapping);
        mapping.put(ActionType.LEFT, leftMapping);
        mapping.put(ActionType.SHOOT, shootMapping);
    }
}
