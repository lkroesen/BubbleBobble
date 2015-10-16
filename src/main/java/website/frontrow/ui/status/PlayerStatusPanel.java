package website.frontrow.ui.status;

import website.frontrow.sprite.ImageStore;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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

    // Max Height is a manually measured height.
    @SuppressWarnings("checkstyle:magicnumber")
    private static final int MAX_HEIGHT = 350;

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
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));
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
    private void update()
    {
        this.removeAll();
        add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(new JLabel("Player " + playerNumber, SwingConstants.CENTER));
        this.add(new JLabel("Score: " + score, SwingConstants.CENTER));
        JPanel livesPanel = new JPanel();
        for(int i = 0; i < remainingLives; i++)
        {
            ImageIcon heart = ImageStore.getInstance().getHeartIcon();
            JLabel label = new JLabel(heart);
            label.setPreferredSize(new Dimension(heart.getIconWidth(), heart.getIconHeight()));
            livesPanel.add(label);
        }

        for(int i = remainingLives; i < maxLives; i++)
        {
            i = Math.max(i, 0);
            if(i == 0)
            {
                break;
            }
            ImageIcon heart = ImageStore.getInstance().getGreyHeartIcon();
            JLabel label = new JLabel(heart);
            label.setPreferredSize(new Dimension(heart.getIconWidth(), heart.getIconHeight()));
            livesPanel.add(label);
        }
        this.add(livesPanel);
        add(Box.createVerticalGlue());
    }
}
