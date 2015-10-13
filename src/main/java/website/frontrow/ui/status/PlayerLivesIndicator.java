package website.frontrow.ui.status;

import website.frontrow.sprite.ImageStore;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * The player indicator.
 */
public class PlayerLivesIndicator
    extends JPanel
{
    private int playerNumber;

    private int remainingLives;

    /**
     * Creates a panel which displays the player number and the corresponding lives.
     * @param playerNumber The number of the player.
     * @param remainingLives The number of remaining lives.
     */
    public PlayerLivesIndicator(int playerNumber, int remainingLives)
    {
        super(new BorderLayout());
        this.playerNumber = playerNumber;
        this.remainingLives = remainingLives;
        updateLives();
    }

    /**
     * Change the number of remaining lives.
     * @param lives the number of remaining lives.
     */
    public void setRemainingLives(int lives)
    {
        this.remainingLives = lives;
        updateLives();
    }

    /**
     * Updates the lives of the players.
     */
    @SuppressWarnings("magicnumber")
    private void updateLives()
    {
        this.removeAll();
        System.out.println("Removed all things!");

        this.add(new JLabel("Player " + playerNumber, SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel livesPanel = new JPanel();
        livesPanel.setPreferredSize(new Dimension(remainingLives * 20, 30));

        for(int i = 0; i < remainingLives; i++)
        {
            JLabel label = new JLabel(ImageStore.getInstance().getHeartIcon());
            label.setVisible(true);
            label.setPreferredSize(new Dimension(16, 16));

            livesPanel.add(label);
        }
        this.add(livesPanel, BorderLayout.SOUTH);
    }
}
