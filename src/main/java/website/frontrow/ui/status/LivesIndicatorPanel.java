package website.frontrow.ui.status;

import website.frontrow.board.Player;
import website.frontrow.board.observer.PlayerObserver;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

import java.util.ArrayList;

/**
 * Indicates the remaining lives of the players.
 */
public class LivesIndicatorPanel
    extends JPanel
    implements PlayerObserver
{
    private Player[] playerList;
    private PlayerLivesIndicator[] playerLivesIndicatorList;

    /**
     * Creates a panel which contains indicators of the number of lives remaining for the players.
     * @param players The players.
     */
    public LivesIndicatorPanel(ArrayList<Player> players)
    {
        super(new BorderLayout());

        players.forEach(p -> p.addObserver(this));

        playerList = new Player[players.size()];
        playerLivesIndicatorList = new PlayerLivesIndicator[players.size()];

        for(int i = 0; i < players.size(); i++)
        {
            PlayerLivesIndicator livesPanel
                    = new PlayerLivesIndicator(i + 1, players.get(i).getLives());

            playerList[i] = players.get(i);
            playerLivesIndicatorList[i] = livesPanel;

            add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
            add(livesPanel, BorderLayout.NORTH);
        }
    }

    @Override
    public void livesChanged(Player player)
    {
        for (int c = 0; c < playerList.length; c++)
        {
            if (playerList[c].getId() == player.getId())
            {
                playerLivesIndicatorList[c].setRemainingLives(player.getLives());
                super.revalidate();
                super.repaint();
            }
        }
    }

    @Override
    public void scoreChanged(Player player)
    {
        //Nothing yet
    }

    @Override
    public void playerDied(Player player)
    {
        // Nothing yet.
    }

    @Override
    public void invincible(Player player)
    {
        //Nothing yet
    }

    @Override
    public void notInvincible(Player player)
    {
        //Nothing yet
    }
}
