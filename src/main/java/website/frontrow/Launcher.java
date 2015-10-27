package website.frontrow;

import java.util.List;
import java.util.ListIterator;
import website.frontrow.board.BasicUnitFactory;
import website.frontrow.board.UnitFactory;
import website.frontrow.game.Game;
import website.frontrow.game.PlayerActions;
import website.frontrow.game.UtilActions;
import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.music.Songs;
import website.frontrow.ui.JBubbleBobbleUI;
import website.frontrow.ui.ModeMenu;
import website.frontrow.game.GameConstants;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import website.frontrow.util.keymap.KeyCodeKey;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * Instantiates the game so it can be played.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class Launcher implements Logable
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
     * @throws IOException Some files might not be found.
     */
    public static void main(String[] args) throws IOException
    {
        Log.togglePrinting();

        MusicPlayer.getInstance().selectSong(Songs.TITLE_SCREEN);

        new ModeMenu().setVisible(true);
    }

    /**
     * Starts the game.
     * @param filename The file name of the level to load.
     * @param playerCount The amount of players in this game.
     */
    @SuppressWarnings("methodlength") // We need to make a GameFactory and UIBuilder
    public void start(String[] filename, int playerCount)
    {
        addToLog("[LAUNCHER]\tLoading files: " + Arrays.toString(filename) + ".");

        try
        {
            MapParser parser = new MapParser(new BasicUnitFactory());
            ArrayList<Level> levelList = new ArrayList<>();
            UnitFactory unitFactory = new BasicUnitFactory();
            for(String levelFileName : filename)
            {
                InputStream map = getClass().getResourceAsStream(levelFileName);
                Level level = parser.parseMap(map);
                levelList.add(level);
            }
            
            addToLog("[LAUNCHER]\tLoading files: " + Arrays.toString(filename) + " succeeded.");

            Game game = new Game(levelList, unitFactory, playerCount);
            List<PlayerActions> playerActions = new ArrayList<>();

            game.getPlayers().forEach(
                    player -> playerActions.add(new PlayerActions(player, game, unitFactory)));
            UtilActions utilActions = new UtilActions();

            KeyRegistry keyRegistry = new KeyRegistry();
            registerPlayerDefaults(keyRegistry, playerActions, playerCount);
            registerUtilityDefaults(utilActions, keyRegistry);
            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, keyRegistry);

            InputStream map = getClass().getResourceAsStream("/game_over.txt");
    		Level gameOverLevel = parser.parseMap(map);
    		game.setGameOver(gameOverLevel);

            InputStream winMap = getClass().getResourceAsStream("/game_won.txt");
            Level gameWonLevel = parser.parseMap(winMap);
            game.setGameWon(gameWonLevel);

            game.setKeyListener(ui.getKeyListener());

            ui.start();
            startScheduler(game);

        }
        catch (IOException e)
        {
            addToLog("[ERROR]\tLoading file: " + Arrays.toString(filename) + " failed.");
            DumpLog.getInstance().createDump();
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
     * Register the players into the registry.
     */
    private void registerPlayerDefaults(KeyRegistry keyRegistry,
                                        List<PlayerActions> playerActions,
                                        int playerCount)
    {
        ListIterator<PlayerActions> it = playerActions.listIterator();
        while (it.hasNext())
        {
            int index = it.nextIndex();
            PlayerActions actions = it.next();
            if(playerCount == 1)
            {
                registerSinglePlayerDefaults(actions, keyRegistry);
            }
            else
            {
                registerMultiPlayerDefaults(actions, keyRegistry, index);
            }
        }
    }

    /**
     * Register the player to the default controls.
     * @param playerActions Player actions.
     * @param registry The registry to register to.
     */
    private void registerSinglePlayerDefaults(PlayerActions playerActions, KeyRegistry registry)
    {
        registry.register(new KeyCodeKey(KeyEvent.VK_UP), playerActions.getJump());
        registry.register(new KeyCodeKey(KeyEvent.VK_LEFT), playerActions.getLeft());
        registry.register(new KeyCodeKey(KeyEvent.VK_RIGHT), playerActions.getRight());
        registry.register(new KeyCodeKey(KeyEvent.VK_SPACE), playerActions.getShoot());
    }

    /**
     * Register multiplayer default keybindings.
     * @param playerActions The PlayerActions on which to register.
     * @param registry The registry to register keys in.
     * @param player The index of the current player.
     */
    private void registerMultiPlayerDefaults(PlayerActions playerActions,
                                             KeyRegistry registry,
                                             int player)
    {
        switch (player)
        {
            case 0:
                registry.register(new KeyCodeKey(KeyEvent.VK_W), playerActions.getJump());
                registry.register(new KeyCodeKey(KeyEvent.VK_A), playerActions.getLeft());
                registry.register(new KeyCodeKey(KeyEvent.VK_D), playerActions.getRight());
                registry.register(new KeyCodeKey(KeyEvent.VK_SPACE), playerActions.getShoot());
                break;
            case 1:
                registry.register(new KeyCodeKey(KeyEvent.VK_UP), playerActions.getJump());
                registry.register(new KeyCodeKey(KeyEvent.VK_LEFT), playerActions.getLeft());
                registry.register(new KeyCodeKey(KeyEvent.VK_RIGHT), playerActions.getRight());
                registry.register(new KeyCodeKey(KeyEvent.VK_CONTROL), playerActions.getShoot());
                break;
            default:
                throw new UnsupportedOperationException(
                        "No defaults available for more than 2 players!"
                );
        }
    }

    private void registerUtilityDefaults(UtilActions utilActions,
                                         KeyRegistry registry)
    {
        registry.register(new KeyCodeKey(KeyEvent.VK_EQUALS), utilActions.getVolumeUp());
        registry.register(new KeyCodeKey(KeyEvent.VK_MINUS), utilActions.getVolumeDown());
        registry.register(new KeyCodeKey(KeyEvent.VK_F1), utilActions.getDumpLog());
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
