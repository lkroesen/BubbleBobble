package website.frontrow.keybindings;

import java.awt.event.KeyEvent;

/**
 * Simple Class for keeping track of all keybindings.
 */
public class KeyBinds
{
    // TODO: Load previous bindings set in the game.
    public static int player1GoLeft = KeyEvent.VK_LEFT;
    public static int player1GoRight = KeyEvent.VK_RIGHT;
    public static int player1Jump = KeyEvent.VK_UP;
    public static int player1Shoot = KeyEvent.VK_NUMPAD0;

    public static int player2GoLeft = KeyEvent.VK_A;
    public static int player2GoRight = KeyEvent.VK_D;
    public static int player2Jump = KeyEvent.VK_SPACE;
    public static int player2Shoot = KeyEvent.VK_SHIFT;

    public static int utilCreateLog = KeyEvent.VK_F1;
    public static int utilVolumeUp = KeyEvent.VK_EQUALS;
    public static int utilVolumeDown = KeyEvent.VK_MINUS;
}
