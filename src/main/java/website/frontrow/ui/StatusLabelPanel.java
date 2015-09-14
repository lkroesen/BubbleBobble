package website.frontrow.ui;

import website.frontrow.game.Game;
import website.frontrow.game.GameObserver;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel which contains stats on the game.
 */
public class StatusLabelPanel extends JPanel
    implements GameObserver
{

    private JLabel runningLabel;

    /**
     * Creates a status label panel.
     * @param game The game to report on.
     */
    public StatusLabelPanel(Game game)
    {
        game.registerObserver(this);
        this.runningLabel = new JLabel("Paused");

        add(this.runningLabel);
    }

    /**
     * Getter for runningLabel.
     * @return runningLabel JLabel
     */
    public JLabel getRunningLabel()
    {

        return runningLabel;

    }

    @Override
    public void gameStop()
    {
        this.runningLabel.setText("Paused");
    }

    @Override
    public void gameStart()
    {
        this.runningLabel.setText("Running");
    }
}
