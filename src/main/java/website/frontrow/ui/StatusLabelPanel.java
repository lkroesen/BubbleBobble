package website.frontrow.ui;

import website.frontrow.Game;
import website.frontrow.GameObserver;

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
     * Creates an status label panel.
     * @param game The game to report on.
     */
    public StatusLabelPanel(Game game)
    {
        game.registerObserver(this);
        this.runningLabel = new JLabel("Paused");

        add(this.runningLabel);
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
