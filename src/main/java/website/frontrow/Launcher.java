package website.frontrow;

import website.frontrow.board.Bubble;
import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.ui.Action;
import website.frontrow.ui.JBubbleBobbleUI;
import website.frontrow.game.GameConstants;
import website.frontrow.util.MusicPlayer;
import website.frontrow.util.Point;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Instantiates the game so it can be played.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class Launcher
{
    private MusicPlayer musicPlayer;

    /**
     * Construct a launcher, currently not doing anything.
     */
    public Launcher()
    {
        musicPlayer = new MusicPlayer();
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
            ArrayList<Level> levelList = new ArrayList<Level>();
            levelList.add(level);

            Game game = new Game(levelList, level.getPlayers());
            Map<Integer, Action> keyMappings = createSinglePlayerKeyMappings(game);
            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, keyMappings);

            game.setKeyListener(ui.getKeyListener());

            ui.start();

            startScheduler(game);
            
        } catch (IOException e)
        {
            throw new RuntimeException();
        }
    }

    /**
     * Starts the schedular for a game object.
     * @param game Game to start a scheduler for.
     */
    private void startScheduler(Game game)
    {
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(() ->
                {
                    try
                    {
                        game.tick();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        throw e;
                    }
                }, 0, 1000 / GameConstants.TICKS_PER_SEC,
                TimeUnit.MILLISECONDS);
    }

    /**
     * Creates the key mappings for a single player game.
     * @param game The game to control with the keys.
     * @return The mapping.
     */
    @SuppressWarnings("checkstyle:methodlength")
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

            map.put(KeyEvent.VK_SPACE, () ->
            {
                game.getPlayers().get(0).jump();
            });

            map.put(KeyEvent.VK_Z, () ->
            {
                if(game.isRunning())
                {
                    Player p = game.getPlayers().get(0);
                    game.getLevel().addUnit(
                            new Bubble(p.getLocation(),
                                    new Point(p.getDirection().getDeltaX() * 4, 0)));
                }
            });
        }

        // Keys 1-0 & -, =, SOUND CONTROL
        map.put(KeyEvent.VK_1, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(0);
            }
        });

        map.put(KeyEvent.VK_2, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(1);
            }
        });

        map.put(KeyEvent.VK_3, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(2);
            }
        });

        map.put(KeyEvent.VK_4, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(3);
            }
        });

        map.put(KeyEvent.VK_5, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(4);
            }
        });

        map.put(KeyEvent.VK_6, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(5);
            }
        });

        map.put(KeyEvent.VK_7, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(6);
            }
        });

        map.put(KeyEvent.VK_8, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(7);
            }
        });

        map.put(KeyEvent.VK_9, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(8);
            }
        });

        map.put(KeyEvent.VK_0, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(9);
            }
        });

        map.put(KeyEvent.VK_OPEN_BRACKET, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(10);
            }
        });

        map.put(KeyEvent.VK_CLOSE_BRACKET, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.playSelection(11);
            }
        });

        // Volume Control
        map.put(KeyEvent.VK_MINUS, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.volumeAdjust(-0.1f);
            }
        });

        map.put(KeyEvent.VK_EQUALS, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.volumeAdjust(0.1f);
            }
        });

        // Restart Sound
        map.put(KeyEvent.VK_BACK_SPACE, () ->
        {
            if (game.isRunning())
            {
                musicPlayer.stopSound();
            }
        });

        return map;
    }
}
