package website.frontrow;

import java.util.List;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import website.frontrow.ui.keybinding.RebindFrame;
import website.frontrow.util.keymap.KeyRegistry;
import website.frontrow.util.keymap.KeyRegistryHandler;

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
     *
     * @param filename The file name of the level to load.
     * @param playerCount The amount of players in this game.
     */
    public void start(String[] filename, int playerCount)
    {
        try
        {
            UnitFactory unitFactory = new BasicUnitFactory();
            MapParser parser = new MapParser(unitFactory);
            ArrayList<Level> levelList = new ArrayList<>();
            addToLog("[LAUNCHER]\tLoading files: " + Arrays.toString(filename) + ".");
            for(String levelFileName : filename)
            {
                InputStream map = getClass().getResourceAsStream(levelFileName);
                Level level = parser.parseMap(map);
                levelList.add(level);
            }

            addToLog("[LAUNCHER]\tLoading files: " + Arrays.toString(filename) + " succeeded.");

            Game game = new Game(levelList, unitFactory, playerCount);

            JBubbleBobbleUI ui = setUpUI(game, unitFactory);
            loadSpecialScreens(game, parser);

            ui.start();
            startScheduler(game);
        }
        catch (IOException e)
        {
            addToLog("[ERROR]\tLaunching the game failed.");
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

    private JBubbleBobbleUI setUpUI(Game game, UnitFactory unitFactory)
    {
        List<PlayerActions> playerActions = new ArrayList<>();

        game.getPlayers().forEach(
                player -> playerActions.add(new PlayerActions(player, game, unitFactory))
        );
        UtilActions utilActions = new UtilActions();
        KeyRegistry keyRegistry
                = KeyRegistryHandler.createKeyBindings(playerActions, utilActions);
        RebindFrame rebindFrame = new RebindFrame(keyRegistry, playerActions, utilActions);
        JBubbleBobbleUI ui =  new JBubbleBobbleUI(game, keyRegistry, rebindFrame);
        game.setKeyListener(ui.getKeyListener());
        return ui;
    }

    private void loadSpecialScreens(Game game, MapParser parser) throws IOException
    {
        try (InputStream map = getClass().getResourceAsStream("/game_over.txt");
             InputStream winMap = getClass().getResourceAsStream("/game_won.txt"))
        {
            Level gameOverLevel = parser.parseMap(map);
            game.setGameOver(gameOverLevel);

            Level gameWonLevel = parser.parseMap(winMap);
            game.setGameWon(gameWonLevel);
        }
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
