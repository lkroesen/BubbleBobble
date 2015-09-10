package website.frontrow.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import website.frontrow.board.Player;

/**
 * A Panel containing the score for the player.
 * In case of multiplayer, scores of both players are shown seperately.
 * Created by Remi Flinterman on 8-9-2015.
 */
public class ScorePanel extends JPanel
{

    /**
     * Default serialization ID.
     */
    private static final long SERIAL = 1L;

    /**
     * The map of players and their labels with their scores.
     */
    private final Map<Player, JLabel> labels;

    /**
     * Creates a new score panel for the player.
     * When playing multiplayer, a score panel is created for each player.
     * @param players The list of players (max. 2 players).
     * @throws IllegalArgumentException Throws Exception when triggered.
     */
    ScorePanel(List<Player> players) throws IllegalArgumentException
    {

        super();

        if(players == null)
        {
            throw new IllegalArgumentException("No Players");
        }

        assert players != null;

        setLayout(new GridLayout(2, players.size()));

        for(int c = 0; c < players.size(); c++)
        {
            int i = c + 1;
            add(new JLabel("Player" + i, JLabel.CENTER));
        }

        labels = new LinkedHashMap();

        for (Player pl : players)
        {
            JLabel sl = new JLabel("0", JLabel.CENTER);
            labels.put(pl, sl);
            add(sl);
        }

    }

    /**
     * Retuns the labels.
     * @return labels Map<Player, JLabel>
     */
    public Map<Player, JLabel> getLabels()
    {
        return labels;
    }

    // Scores can't be reset yet, because we don't have a state for
    //      being alive or dead for the player yet.

}
