package website.frontrow.ui;

import website.frontrow.Game;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * The side panel for the ui.
 */
public class SidePanel extends JPanel
{

    private Game game;

    /**
     * Creates a sidepanel to contain the score, buttons and other statistics.
     * @param game The game to display the statistics of.
     */
    public SidePanel(Game game)
    {
        super();
        this.game = game;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Add the score counter

        String[] strButtonNames = new String[2];
        strButtonNames[0] = "Restart";
        strButtonNames[1] = "Insert Coin";

        add(new ButtonPanel(strButtonNames));
    }
}
