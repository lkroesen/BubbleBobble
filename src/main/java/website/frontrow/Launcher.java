package website.frontrow;

import website.frontrow.game.Game;
import website.frontrow.keybindings.BindActions;
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
            MapParser mp = new MapParser();
            ArrayList<Level> levelList = new ArrayList<>();
            for(String levelFileName : filename)
            {
                InputStream map = getClass().getResourceAsStream(levelFileName);
                Level level = mp.parseMap(map);
                levelList.add(level);
            }

            Game game = new Game(levelList, playerCount);
            BindActions actionBinder = BindActions.getInstance();

            actionBinder.createUtilMappings().createFirstPlayerKeyMappings(game);

            if(playerCount >= 2)
            {
                actionBinder.createSecondPlayerKeyMappings(game);
            }

            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, actionBinder.getMapping());

            InputStream map = getClass().getResourceAsStream("/game_over.txt");
    		Level gameOverLevel = mp.parseMap(map);
    		game.setGameOver(gameOverLevel);

            InputStream winMap = getClass().getResourceAsStream("/game_won.txt");
            Level gameWonLevel = mp.parseMap(winMap);
            game.setGameWon(gameWonLevel);

            game.setKeyListener(ui.getKeyListener());

            ui.start();
            startScheduler(game);
            addToLog("[LAUNCHER]\tLoading files: " + Arrays.toString(filename) + " succeeded.");
        }
        catch (IOException e)
        {
            addToLog("[ERROR]\tLoading file: " + Arrays.toString(filename) + " failed.");
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
     * Add an action to the log.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
