package website.frontrow.ui.status;

import website.frontrow.sprite.ImageStore;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * The player indicator.
 */
public class PlayerStatusPanel
    extends JPanel
{
    private int playerNumber;

    private final int maxLives;

    private int remainingLives;

    private int score;

    /**
     * Creates a panel which displays the player number and the corresponding lives.
     * @param playerNumber The number of the player.
     * @param remainingLives The number of remaining lives.
     * @param score The score of the player.
     */
    public PlayerStatusPanel(int playerNumber, int remainingLives,  int score)
    {
        super(new GridLayout(0, 1));
        this.playerNumber = playerNumber;
        this.remainingLives = remainingLives;
        this.maxLives = remainingLives;
        this.score = score;
        update();
    }

    /**
     * Change the number of remaining lives.
     * @param lives The number of remaining lives.
     */
    public void setRemainingLives(int lives)
    {
        this.remainingLives = lives;
        update();
    }

    /**
     * Change the score for the player.
     * @param score The new player score.
     */
    public void setScore(int score)
    {
        this.score = score;
        update();
    }

    /**
     * Updates the lives of the players.
     */
    @SuppressWarnings({"magicnumber", "methodlength"})
    private void update()
    {
        this.removeAll();
        add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(new JLabel("Player " + playerNumber, SwingConstants.CENTER));
        this.add(new JLabel("Score: " + score, SwingConstants.CENTER));
        JPanel livesPanel = new JPanel();
        livesPanel.setPreferredSize(new Dimension(remainingLives * 20, 30));

        for(int i = 0; i < remainingLives; i++)
        {
            JLabel label = new JLabel(ImageStore.getInstance().getHeartIcon());
            label.setPreferredSize(new Dimension(16, 16));
            livesPanel.add(label);
        }

        for(int i = remainingLives; i < maxLives; i++)
        {
            i = Math.max(i, 0);
            if(i == 0)
            {
                break;
            }

            JLabel label = new JLabel(ImageStore.getInstance().getGreyHeartIcon());
            label.setPreferredSize(new Dimension(16, 16));
            livesPanel.add(label);
        }
        this.add(livesPanel);
    }
}
