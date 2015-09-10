package website.frontrow;

import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.ui.Action;
import website.frontrow.ui.JBubbleBobbleUI;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Instantiates the game so it can be played.
 */
public class Launcher
{
    /**
     * Construct a launcher, currently not doing anything.
     */
    public Launcher()
    {

    }

    /**
     * The starting point of the program.
     * @param args The arguments of the program. Currently no parameters are used.
     */
    public static void main(String[] args)
    {
        new Launcher().start("/1.txt");
    }

    /**
     * Starts the game.
     * @param filename The file name of the level to load.
     */
    public void start(String filename)
    {
        try
        {
            InputStream map = getClass().getResourceAsStream(filename);
            MapParser mp = new MapParser();
            Level level = mp.parseMap(map);
            Game game = new Game(level, level.getPlayers());
            Map<Integer, Action> keyMappings = createSinglePlayerKeyMappings(game);

            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, keyMappings);
            ui.start();
        } catch (IOException e)
        {
            throw new RuntimeException();
        }

    }

    /**
     * Creates the key mappings for a single player game.
     * @param game The game to control with the keys.
     * @return The mapping.
     */
    private Map<Integer, Action> createSinglePlayerKeyMappings(Game game)
    {
        Map<Integer, Action> map = new HashMap<>();

        if(game.getPlayers().size() == 1)
        {
            map.put(KeyEvent.VK_LEFT, () ->
            {
                game.getPlayers().get(0).goLeft();
            });

            map.put(KeyEvent.VK_RIGHT, () ->
            {
                game.getPlayers().get(0).goRight();
            });
        }

        return map;
    }
}
