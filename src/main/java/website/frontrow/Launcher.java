package website.frontrow;

import website.frontrow.board.Bubble;
import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.level.Level;
import website.frontrow.level.MapParser;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.music.Songs;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.ui.Action;
import website.frontrow.ui.JBubbleBobbleUI;
import website.frontrow.game.GameConstants;
import website.frontrow.util.FileNameCollector;
import website.frontrow.util.Point;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

        MusicPlayer.getInstance().init();
        MusicPlayer.getInstance().selectSong(Songs.TITLE_SCREEN);

        try
        {
        	new Launcher().start(new FileNameCollector().obtain("level/"));
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Starts the game.
     * @param filename The file name of the level to load.
     */
    @SuppressWarnings("methodlength") // We need to make a GameFactory and UIBuilder
    public void start(String[] filename)
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

            Game game = new Game(levelList);
            Map<Integer, Action> keyMappings = createSinglePlayerKeyMappings(game);
            JBubbleBobbleUI ui = new JBubbleBobbleUI(game, keyMappings);
            
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

                if(game.isRunning())
                {
                    Player p = game.getPlayers().get(0);
                    game.getLevel().addUnit(
                            new Bubble(p.getLocation(),
                                    new Point(p.getDirection().getDeltaX() * 4, 0),
                                    JBubbleBobbleSprites.getInstance().getBubbleSprite()));
                }
            });
        }
        else{
        	map.put(KeyEvent.VK_A, () ->
            {
                addToLog("[KEY]\t< \'<-\' > Pressed.");
                game.getPlayers().get(0).goLeft();
            });

            map.put(KeyEvent.VK_D, () ->
            {
                addToLog("[KEY]\t< \'->\' > Pressed.");
                game.getPlayers().get(0).goRight();
            });

            map.put(KeyEvent.VK_W, () ->
            {
                addToLog("[KEY]\t< \' \' > Pressed.");
                game.getPlayers().get(0).jump();
            });

            map.put(KeyEvent.VK_SPACE, () ->
            {
                addToLog("[KEY]\t< \'Z\' > Pressed.");

                if(game.isRunning())
                {
                    Player p = game.getPlayers().get(0);
                    game.getLevel().addUnit(
                            new Bubble(p.getLocation(),
                                    new Point(p.getDirection().getDeltaX() * 4, 0),
                                    JBubbleBobbleSprites.getInstance().getBubbleSprite()));
                }
            });
            
            map.put(KeyEvent.VK_LEFT, () ->
            {
                addToLog("[KEY]\t< \'<-\' > Pressed.");
                game.getPlayers().get(1).goLeft();
            });
            
            map.put(KeyEvent.VK_RIGHT, () ->
            {
                addToLog("[KEY]\t< \'->\' > Pressed.");
                game.getPlayers().get(1).goRight();
            });
            
            map.put(KeyEvent.VK_UP, () ->
            {
                addToLog("[KEY]\t< \' \' > Pressed.");
                game.getPlayers().get(1).jump();
            });
            
            map.put(KeyEvent.VK_CONTROL, () ->
            {
                addToLog("[KEY]\t< \'Z\' > Pressed.");

                if(game.isRunning())
                {
                    Player p = game.getPlayers().get(1);
                    game.getLevel().addUnit(
                            new Bubble(p.getLocation(),
                                    new Point(p.getDirection().getDeltaX() * 4, 0),
                                    JBubbleBobbleSprites.getInstance().getBubbleSprite()));
                }
            });
        }

        // Volume Control
        map.put(KeyEvent.VK_MINUS, () ->
        {
            addToLog("[KEY]\t< \'-\' > Pressed.");
            MusicPlayer.getInstance().volumeAdjust(-1.0d);
        });

        map.put(KeyEvent.VK_EQUALS, () ->
        {
            addToLog("[KEY]\t< \'=\' > Pressed.");
            MusicPlayer.getInstance().volumeAdjust(1.0d);
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
