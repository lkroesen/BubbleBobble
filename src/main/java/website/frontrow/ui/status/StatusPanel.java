package website.frontrow.ui.status;

import website.frontrow.board.Player;
import website.frontrow.board.observer.PlayerObserver;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Indicates the remaining lives of the players.
 */
public class StatusPanel
    extends JPanel
    implements PlayerObserver
{
    private Map<Long, PlayerStatusPanel> mapPlayerIdIndicator;

    /**
     * Creates a panel which contains indicators of the number of lives remaining for the players.
     * @param players The players.
     */
    public StatusPanel(ArrayList<Player> players)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mapPlayerIdIndicator = new HashMap<>();

        players.forEach(p -> p.addObserver(this));

        for(int i = 0; i < players.size(); i++)
        {
            Player player = players.get(i);
            PlayerStatusPanel livesPanel
                    = new PlayerStatusPanel(i + 1, player.getLives(), player.getScore());

            mapPlayerIdIndicator.put(player.getId(), livesPanel);


            add(livesPanel);
        }
    }

    @Override
    public void livesChanged(Player player)
    {
        if (mapPlayerIdIndicator.containsKey(player.getId()))
        {
            mapPlayerIdIndicator.get(player.getId()).setRemainingLives(player.getLives());
            update();
        }
    }

    @Override
    public void scoreChanged(Player player)
    {
        if(mapPlayerIdIndicator.containsKey(player.getId()))
        {
            mapPlayerIdIndicator.get(player.getId()).setScore(player.getScore());
            update();
        }
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
        //Maybe flash the hearts in the player panel.
    }

    @Override
    public void notInvincible(Player player)
    {
        //Nothing yet
    }

    /**
     * Updates all the components!
     */
    public void update()
    {
        super.revalidate();
        super.repaint();
    }
}
