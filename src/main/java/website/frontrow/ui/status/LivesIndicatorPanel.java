package website.frontrow.ui.status;

import website.frontrow.board.Player;
import website.frontrow.board.observer.PlayerObserver;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Indicates the remaining lives of the players.
 */
public class LivesIndicatorPanel
    extends JPanel
    implements PlayerObserver
{

    private Map<Player, PlayerLivesIndicator> lives;

    /**
     * Creates a panel which contains indicators of the number of lives remaining for the players.
     * @param players The players.
     */
    public LivesIndicatorPanel(ArrayList<Player> players)
    {
        super(new BorderLayout());
        this.lives = new HashMap<>();

        players.forEach(p -> p.addObserver(this));

        for(int i = 0; i < players.size(); i++)
        {
            PlayerLivesIndicator livesPanel
                    = new PlayerLivesIndicator(i + 1, players.get(i).getLives());
            lives.put(players.get(i), livesPanel);
            add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
            add(livesPanel, BorderLayout.NORTH);
        }
    }

    @Override
    public void livesChanged(Player player)
    {
        assert lives.containsKey(player);
        lives.get(player).setRemainingLives(player.getLives());
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
