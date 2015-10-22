package website.frontrow.keybindings;

/**
 * The types of actions that can be performed by pressing a key.
 */
public enum ActionType
{
    JUMP,
    RIGHT,
    LEFT,
    SHOOT,
    STORE_LOG,
    TOGGLE_PRINT_LOG,
    VOLUME_UP,
    VOLUME_DOWN;

    @Override
    public String toString()
    {
        switch (this)
        {
            case JUMP: return "JUMP";
            case RIGHT: return "RIGHT";
            case LEFT: return "LEFT";
            case SHOOT: return "SHOOT";
            case STORE_LOG: return "STORE_LOG";
            case TOGGLE_PRINT_LOG: return "TOGGLE_PRINT_LOG";
            case VOLUME_UP: return "VOLUME_UP";
            case VOLUME_DOWN: return "VOLUME_DOWN";
        }
        return "";
    }

    /**
     * Interprets a string and creates an ActionType from it.
     * @param string The string to interpret.
     * @return The ActionType, null if the string cannot be interpreted.
     */
    public static ActionType fromString(String string)
    {
        switch(string)
        {
            case "JUMP": return JUMP;
            case "RIGHT": return RIGHT;
            case "LEFT": return LEFT;
            case "SHOOT": return SHOOT;
            case "STORE_LOG": return STORE_LOG;
            case "TOGGLE_PRINT_LOG": return TOGGLE_PRINT_LOG;
            case "VOLUME_UP": return VOLUME_UP;
            case "VOLUME_DOWN": return VOLUME_DOWN;
            default: return null;
        }
    }
}
