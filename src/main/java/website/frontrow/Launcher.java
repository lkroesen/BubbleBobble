package website.frontrow;

import website.frontrow.board.Bubble;
import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.ui.Action;
import website.frontrow.ui.JBubbleBobbleUI;
import website.frontrow.game.GameConstants;
import website.frontrow.util.FileNameCollector;
import website.frontrow.util.MusicPlayer;
import website.frontrow.util.Point;
import website.frontrow.logger.Logable;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
public class Launcher implements Logable
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
        // Initialize the Logger Class, so that it can Log actions taken.
        new Log();
        Log.togglePrinting();

        try
        {
            new Launcher().start(new FileNameCollector().obtain("/level/"));
        }
        catch (URISyntaxException exception)
        {
            Log.add("[LAUNCHER]\t[ERROR]\tLauncher couldn't start due to FileNameCollector.");
            exception.printStackTrace();
        }
    }

    /**
     * Starts the game.
     * @param filename The file name of the level to load.
     */
    public void start(String[] filename)
    {
        addToLog("[LAUNCHER]\tLoading file: " + filename + ".");

        try
        {
            addToLog("[LAUNCHER]\tLoading file: " + filename + " succeeded.");

            MapParser mp = new MapParser();
            ArrayList<Level> levelList = new ArrayList<>();
            for(String levelFileName : filename)
            {
                InputStream map = getClass().getResourceAsStream(levelFileName);
                Level level = mp.parseMap(map);
                levelList.add(level);
            }

            Game game = new Game(levelList);
            Map<Integer, Action> keyMappings = createSinglePlayerKeyMappings(game);
            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, keyMappings);

            game.setKeyListener(ui.getKeyListener());

            ui.start();
            startScheduler(game);

        } catch (IOException e)
        {
            addToLog("[ERROR]\tLoading file: " + filename + " failed.");
            new DumpLog();
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
                addToLog("[KEY]\t< \'<-\' > Pressed.");
                game.getPlayers().get(0).goLeft();
            });

            map.put(KeyEvent.VK_RIGHT, () ->
            {
                addToLog("[KEY]\t< \'->\' > Pressed.");
                game.getPlayers().get(0).goRight();
            });

            map.put(KeyEvent.VK_SPACE, () ->
            {
                addToLog("[KEY]\t< \' \' > Pressed.");
                game.getPlayers().get(0).jump();
            });

            map.put(KeyEvent.VK_Z, () ->
            {
                addToLog("[KEY]\t< \'Z\' > Pressed.");
                JBubbleBobbleSprites spriteStore = new JBubbleBobbleSprites();
                if(game.isRunning())
                {
                    Player p = game.getPlayers().get(0);
                    game.getLevel().addUnit(
                            new Bubble(p.getLocation(),
                                    new Point(p.getDirection().getDeltaX() * 4, 0),
                                    spriteStore.getBubbleSprite()));
                }
            });
        }

        // Keys 1-0 & -, =, SOUND CONTROL
        map.put(KeyEvent.VK_1, () ->
        {
            addToLog("[KEY]\t< \'1\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(0);
            }
        });

        map.put(KeyEvent.VK_2, () ->
        {
            addToLog("[KEY]\t< \'2\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(1);
            }
        });

        map.put(KeyEvent.VK_3, () ->
        {
            addToLog("[KEY]\t< \'3\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(2);
            }
        });

        map.put(KeyEvent.VK_4, () ->
        {
            addToLog("[KEY]\t< \'4\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(3);
            }
        });

        map.put(KeyEvent.VK_5, () ->
        {
            addToLog("[KEY]\t< \'5\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(4);
            }
        });

        map.put(KeyEvent.VK_6, () ->
        {
            addToLog("[KEY]\t< \'6\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(5);
            }
        });

        map.put(KeyEvent.VK_7, () ->
        {
            addToLog("[KEY]\t< \'7\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(6);
            }
        });

        map.put(KeyEvent.VK_8, () ->
        {
            addToLog("[KEY]\t< \'8\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(7);
            }
        });

        map.put(KeyEvent.VK_9, () ->
        {
            addToLog("[KEY]\t< \'9\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(8);
            }
        });

        map.put(KeyEvent.VK_0, () ->
        {
            addToLog("[KEY]\t< \'0\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(9);
            }
        });

        map.put(KeyEvent.VK_OPEN_BRACKET, () ->
        {
            addToLog("[KEY]\t< \'[\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(10);
            }
        });

        map.put(KeyEvent.VK_CLOSE_BRACKET, () ->
        {
            addToLog("[KEY]\t< \']\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.playSelection(11);
            }
        });

        // Volume Control
        map.put(KeyEvent.VK_MINUS, () ->
        {
            addToLog("[KEY]\t< \'-\' > Pressed.");

            if (game.isRunning())
            {
                musicPlayer.volumeAdjust(-0.1f);
            }
        });

        map.put(KeyEvent.VK_EQUALS, () ->
        {
            addToLog("[KEY]\t< \'=\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.volumeAdjust(0.1f);
            }
        });

        // Restart Sound
        map.put(KeyEvent.VK_BACK_SPACE, () ->
        {
            addToLog("[KEY]\t< \'BACK_SPACE\' > Pressed.");
            if (game.isRunning())
            {
                musicPlayer.stopSound();
            }
        });

        // Create a DumpLog
        map.put(KeyEvent.VK_F1, () ->
        {
            addToLog("[KEY]\t< F1 > Pressed.");
            new DumpLog();
        });
        return map;
    }

    /**
     * Add an action to the log.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
