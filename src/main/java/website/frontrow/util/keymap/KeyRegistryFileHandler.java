package website.frontrow.util.keymap;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import website.frontrow.game.PlayerActions;

/**
 * Read the key binds from a file.
 */
public abstract class KeyRegistryFileHandler
{
    private static final String DEFAULT_KEY_BIND_FILENAME = "jbb_kb.txt";

    private static String filename = DEFAULT_KEY_BIND_FILENAME;

    /**
     * Returns if the keybindings are stored on the file system.
     *
     * @return Whether the keybindings are stored.
     */
    public static boolean keyBindsExist()
    {
        File file = new File(filename);
        boolean exist = file.exists() & !file.isDirectory();
        if(!exist)
        {
            file.delete();
        }
        return exist;
    }

    /**
     * Sets the filename where the key bindings are stored.
     *
     * @param filename The filename. null == resetFilename;
     */
    public static void setFilename(String filename)
    {
        if(filename == null)
        {
            setFilename(DEFAULT_KEY_BIND_FILENAME);
        }
        else
        {
            KeyRegistryFileHandler.filename = filename;
        }
    }

    /**
     * Resets the filename to default.
     */
    public static void resetFilename()
    {
        setFilename(null);
    }

    /**
     * Loads the key bindings from a file into a registry.
     *
     * @param registry The registry to load the bindings into.
     * @param playerActions The actions for the players.
     * @throws IOException The file might not be found.
     */
    public static void loadKeyBinds(KeyRegistry registry,
                                    List<PlayerActions> playerActions)
            throws IOException
    {
        File file = new File(filename);
        assert file.exists();
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] entries = line.split(" ");

            int playerIndex = Integer.parseInt(entries[1]);
            int keyCode = Integer.parseInt(entries[2]);
            switch(entries[0])
            {
                case "RIGHT":
                    registry.register(keyCode, playerActions.get(playerIndex).getRight()); break;
                case "LEFT":
                    registry.register(keyCode, playerActions.get(playerIndex).getLeft()); break;
                case "JUMP":
                    registry.register(keyCode, playerActions.get(playerIndex).getJump()); break;
                case "SHOOT":
                    registry.register(keyCode, playerActions.get(playerIndex).getShoot()); break;
                default: break;
            }
        }
    }
}
