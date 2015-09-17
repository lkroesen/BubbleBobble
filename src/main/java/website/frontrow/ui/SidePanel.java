package website.frontrow.ui;

import website.frontrow.game.Game;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

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
        //Add the score counter

        Map<String, Action> buttonMappings = new HashMap<>();

        buttonMappings.put("Pause/Start", () ->
        {
            if(game.isRunning())
            {
                game.stop();
            }
            else
            {
                game.start();
            }
        });

        add(new ButtonPanel(buttonMappings));
        add(new StatusLabelPanel(game));
        addToLog("[SiP]\tSide Panel Created Successfully.");
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
