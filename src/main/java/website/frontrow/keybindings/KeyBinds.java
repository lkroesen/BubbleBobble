package website.frontrow.keybindings;

import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Simple Class for keeping track of all keybindings.
 *
 * {@link java.awt.event.KeyEvent}
 */
public abstract class KeyBinds
{
    /**
     * The first key is the action type, the first integer is the player index,
     * the last is the key code.
     */
    private static Map<ActionType, Map<Integer, Integer>> mapping;

    private static ArrayList<KeyBindsObserver> observers = new ArrayList<>();

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
    public synchronized static void setKeyCodeFor(ActionType action, int playerIndex, int keyCode)
    {
        Map<Integer, Integer> actionMap = mapping.get(action);
        actionMap.put(playerIndex, keyCode);
        notifyObservers();
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
    public synchronized static void setKeyCodeForUtil(ActionType action, int keyCode)
    {
        setKeyCodeFor(action, -1, keyCode);
    }

    public synchronized static void createDefaultKeyMapping()
    {
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
     * Prints the key bindings to a print writer.
     * @param writer The print writer to write the bindings to.
     */
    public synchronized static void printTo(PrintWriter writer)
    {
        for(ActionType action : ActionType.values())
        {
            Map<Integer, Integer> actionMap = mapping.get(action);
            for(Map.Entry<Integer, Integer> entry : actionMap.entrySet())
            {
                writer.println(action.toString() + " " + entry.getKey() + " " + entry.getValue());
            }
        }
    }

    /**
     * Sets the key bindings from the information in the scanner.
     * @param scanner The scanner which provides the information.
     */
    public synchronized static void readFrom(Scanner scanner)
    {
        while(scanner.hasNextLine())
        {
            String[] entries = scanner.nextLine().split(" ");
            assert entries.length == 3;
            ActionType action = ActionType.fromString(entries[0]);
            int playerIndex = Integer.parseInt(entries[1]);
            int keyCode = Integer.parseInt(entries[2]);
            switch(action)
            {
                case JUMP: case RIGHT: case LEFT: case SHOOT:
                    setKeyCodeFor(action, playerIndex, keyCode); break;
                case STORE_LOG: case TOGGLE_PRINT_LOG: case VOLUME_UP: case VOLUME_DOWN:
                    setKeyCodeForUtil(action, keyCode); break;
                default: break;
            }
        }
    }

    /**
     * Adds observer. It will be notified when a key binding changes.
     */
    public static void addObserver(KeyBindsObserver observer)
    {
        observers.add(observer);
    }

    /**
     * Removes observer.
     */
    public static void removeObserver(KeyBindsObserver observer)
    {
        observers.remove(observer);
    }

    /**
     * When the key binding has changed, notify the observers.
     */
    private static void notifyObservers()
    {
        observers.forEach(o -> o.keyBindingChanged());
    }
}
