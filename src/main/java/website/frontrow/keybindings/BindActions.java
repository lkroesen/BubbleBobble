package website.frontrow.keybindings;

import website.frontrow.board.Bubble;
import website.frontrow.board.Player;
import website.frontrow.game.Game;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;
import website.frontrow.sprite.JBubbleBobbleSprites;
import website.frontrow.ui.Action;
import website.frontrow.util.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for Binding Actions to keys.
 */
public final class BindActions
        implements Logable
{
    private static final BindActions INSTANCE = new BindActions();
    private static Map<Integer, Action> mapIntActions = new HashMap<>();

    /**
     * Private constructor, because Singleton.
     */
    private BindActions()
    {

    }

    /**
     * Get the instance of the singleton.
     * @return Return the instance.
     */
    public static BindActions getInstance()
    {
        return INSTANCE;
    }

    /**
     * Create a Map<Integer, Action> with the relevant binds for the game.
     * @param game Input the game object.
     * @return Returns a map with the relevant binds and their actions.
     */
    public Map<Integer, Action> createSinglePlayerKeyMappings(Game game)
    {
        if(game.getPlayers().size() == 1)
        {
            player1GoLeftBind(game);
            player1GoRightBind(game);
            player1JumpBind(game);
            player1ShootBind(game);
        }

        utilCreateLog();
        utilVolumeDown();
        utilVolumeUp();

        return mapIntActions;
    }

    /**
     * Define what happens when player 1 goes Left.
     * @param game
     */
    private void player1GoLeftBind(Game game)
    {
        mapIntActions.put(KeyBinds.player1GoLeft, () ->
        {
            addToLog("[KEY]\t< \'<-\' > Pressed.");
            game.getPlayers().get(0).goLeft();
        });
    }

    /**
     * Define what happens when player 1 goes Right.
     * @param game Input the game object.
     */
    private void player1GoRightBind(Game game)
    {
        mapIntActions.put(KeyBinds.player1GoRight, () ->
        {
            addToLog("[KEY]\t< \'->\' > Pressed.");
            game.getPlayers().get(0).goRight();
        });
    }

    /**
     * Define what happens when player 1 jumps.
     * @param game Input the game object.
     */
    private void player1JumpBind(Game game)
    {
        mapIntActions.put(KeyBinds.player1Jump, () ->
        {
            addToLog("[KEY]\t< \' \' > Pressed.");
            game.getPlayers().get(0).jump();
        });
    }

    /**
     * Define what happens when a player shoots.
     * @param game Input the game object.
     */
    private void player1ShootBind(Game game)
    {
        mapIntActions.put(KeyBinds.player1Shoot, () ->
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

    /**
     * Control the volume to go up.
     */
    private void utilVolumeUp()
    {
        mapIntActions.put(KeyBinds.utilVolumeUp, () ->
        {
            addToLog("[KEY]\t< \'=\' > Pressed.");
            MusicPlayer.getInstance().volumeAdjust(1.0d);
        });
    }

    /**
     * Control the volume to go down.
     */
    private void utilVolumeDown()
    {
        mapIntActions.put(KeyBinds.utilVolumeDown, () ->
        {
            addToLog("[KEY]\t< \'-\' > Pressed.");
            MusicPlayer.getInstance().volumeAdjust(-1.0d);
        });
    }

    /**
     * Creates a log in Temp of actions during the game.
     */
    private void utilCreateLog()
    {
        mapIntActions.put(KeyBinds.utilCreateLog, () ->
        {
            addToLog("[KEY]\t< F1 > Pressed.");
            new DumpLog();
        });
    }


    /**
     * Every class that implements Logable, must add actions to the log.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
