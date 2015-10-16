package website.frontrow.ui.status;

import website.frontrow.game.Game;
import website.frontrow.game.GameObserver;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * A panel which contains stats on the game.
 */
public class StatusLabelPanel extends JPanel
    implements GameObserver
{
    private JLabel runningLabel;

    // MAX Height is a measured number.
    @SuppressWarnings("checkstyle:magicnumber")
    private static final int MAX_HEIGHT = 100;
    /**
     * Creates a status label panel.
     * @param game The game to report on.
     */
    public StatusLabelPanel(Game game)
    {
        game.registerObserver(this);
        this.runningLabel = new JLabel("Paused");
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));
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

    /**
     * Setter for runningLabel.
     * @param jLabel JLabel
     */
    public void setRunningLabel(JLabel jLabel)
    {
        runningLabel = jLabel;
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
