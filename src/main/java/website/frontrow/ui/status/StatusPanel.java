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
{
    /**
     * Creates a panel which contains indicators of the number of lives remaining for the players.
     * @param players The players.
     */
    public StatusPanel(ArrayList<Player> players)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for(int i = 0; i < players.size(); i++)
        {
            Player player = players.get(i);
            PlayerStatusPanel livesPanel
                    = new PlayerStatusPanel(i + 1, player);
            add(livesPanel);
        }
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
