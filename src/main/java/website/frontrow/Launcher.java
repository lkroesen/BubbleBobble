package website.frontrow;

import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.ui.JBubbleBobbleUI;

import java.io.IOException;
import java.io.InputStream;

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
        new Launcher().start();


    }

    /**
     * Starts the game.
     */
    public void start()
    {
        try
        {
            InputStream map = getClass().getResourceAsStream("/1.txt");
            MapParser mp = new MapParser();
            Level level = mp.parseMap(map);
            Game game = new Game(level);
            JBubbleBobbleUI ui = new JBubbleBobbleUI(game);
            ui.setTitle("JBUBBLE BOBBLE");
            ui.start();
        } catch (IOException e)
        {
            throw new RuntimeException();
        }

    }
}
