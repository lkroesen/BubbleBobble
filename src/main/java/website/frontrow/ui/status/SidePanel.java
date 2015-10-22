package website.frontrow.ui.status;

import website.frontrow.game.Game;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.music.MusicPlayer;
import website.frontrow.music.Songs;
import website.frontrow.ui.Action;
import website.frontrow.ui.ButtonPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;

/**
 * The side panel for the ui.
 */
public class SidePanel
        extends JPanel
        implements Logable
{
    /**
     * Creates a sidepanel to contain the score, buttons and other statistics.
     * @param game The game to display the statistics of.
     */
    public SidePanel(Game game)
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new ButtonPanel(createButtonMappings(game)));
        add(new StatusLabelPanel(game));

        add(new StatusPanel(game.getPlayers()));
        add(Box.createVerticalGlue());

        addToLog("[SiP]\tSide Panel Created Successfully.");
    }

    /**
     * Create the button mappings for this sidepanel.
     * @param game The current game
     * @return The button mappings.
     */
    public Map<String, Action> createButtonMappings(Game game)
    {
        Map<String, Action> buttonMappings = new HashMap<>();

        buttonMappings.put("Pause/Start", () ->
        {
            if(game.isRunning())
            {
                game.stop();
                MusicPlayer.getInstance().stop();
            }
            else
            {
                game.start();

                MusicPlayer.getInstance().setLooping(true);
                MusicPlayer.getInstance().selectSong(Songs.QUEST_BEGINS);
            }
        });
        buttonMappings.put("Restart", () ->
        {
            game.restart();
        });

        return buttonMappings;
    }

    /**
     * Log actions taken by SidePanel.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
