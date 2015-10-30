package website.frontrow.util.keymap;

import java.awt.event.KeyEvent;
import java.util.List;
import website.frontrow.game.PlayerActions;
import website.frontrow.game.UtilActions;

/**
 * Handles reading the key bindings from the configuration file or when the file does not
 * exist creates the default bindings.
 */
public abstract class KeyRegistryHandler
{
    /**
     * Instantiates the keybindings for the game.
     *
     * @param playerActions The actions for a player.
     * @param utilActions The actions for utilities.
     * @return The resulting key bindings.
     */
    public static KeyRegistry createKeyBindings(List<PlayerActions> playerActions,
                                                UtilActions utilActions)
    {
        KeyRegistry registry = new KeyRegistry();
        createPlayerKeys(registry, playerActions);
        createUtilKey(registry, utilActions);
        return registry;
    }

    private static void createPlayerKeys(KeyRegistry registry, List<PlayerActions> playerActions)
    {
        for(int i = 0; i < playerActions.size(); i++)
        {
            createPlayerKey(i, playerActions.get(i), registry);
        }
    }

    private static void createPlayerKey(int playerIndex,
                                        PlayerActions actions,
                                        KeyRegistry registry)
    {
        switch(playerIndex)
        {
            case 0:
                registry.register(KeyEvent.VK_UP, actions.getJump());
                registry.register(KeyEvent.VK_LEFT, actions.getLeft());
                registry.register(KeyEvent.VK_RIGHT, actions.getRight());
                registry.register(KeyEvent.VK_Z, actions.getShoot());
                break;
            case 1:
                registry.register(KeyEvent.VK_W, actions.getJump());
                registry.register(KeyEvent.VK_A, actions.getLeft());
                registry.register(KeyEvent.VK_D, actions.getRight());
                registry.register(KeyEvent.VK_SHIFT, actions.getShoot());
                break;
            default:
                throw new UnsupportedOperationException(
                        "No binds available for player " + playerIndex
                );
        }
    }

    private static void createUtilKey(KeyRegistry registry, UtilActions actions)
    {
        registry.register(KeyEvent.VK_F1, actions.getToggleLog());
        registry.register(KeyEvent.VK_F2, actions.getDumpLog());
        registry.register(KeyEvent.VK_MINUS, actions.getVolumeDown());
        registry.register(KeyEvent.VK_EQUALS, actions.getVolumeUp());
    }

}
