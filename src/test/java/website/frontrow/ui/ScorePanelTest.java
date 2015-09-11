package website.frontrow.ui;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;

import org.junit.rules.ExpectedException;
import website.frontrow.board.Player;
import website.frontrow.util.Point;

import javax.swing.JLabel;

/**
 * Created by Remi on 8-9-2015.
 */
public class ScorePanelTest
{
    @SuppressWarnings("visibilitymodifier") // Is necessary for @Rule.
    @Rule
    public final ExpectedException ee = ExpectedException.none();

    /**
     * Tests the ScorePanel constructor with no players.
     * The method should throw an IllegalArgumentException with message "No Players".
     */
    @Test
    public void scorePanelNoPlayerTest()
    {
        ee.expect(AssertionError.class);
        List<Player> players = null;
        new ScorePanel(players);
    }

    /**
     * Tests the ScorePanel constructor with one player.
     * The Map labels should contain the correct keySet.
     */
   @Test
    public void scorePanelOnePlayerTest()
    {

        Point po = new Point(2, 2);
        Player pl1 = new Player(po);
        List<Player> players = new ArrayList<Player>();
        players.add(pl1);

        ScorePanel test = new ScorePanel(players);

        Map<Player, JLabel> l = new LinkedHashMap();

        JLabel sl = new JLabel("0", JLabel.CENTER);
        l.put(pl1, sl);

        Map<Player, JLabel> labels = test.getLabels();
        assertEquals(labels.keySet(), l.keySet());

    }

    /**
     * Tests the ScorePanel constructor with one player.
     * The Map labels should contain the correct keySet.
     */
    @Test
    public void scorePanelTwoPlayersTest()
    {
        Point po1 = new Point(2, 2);
        Player pl1 = new Player(po1);
        Point po2 = new Point(17, 2);
        Player pl2 = new Player(po2);

        List<Player> players = new ArrayList<>();
        players.add(pl1);
        players.add(pl2);

        ScorePanel test = new ScorePanel(players);

        Map<Player, JLabel> l = new LinkedHashMap<>();

        JLabel sl = new JLabel("0", JLabel.CENTER);
        l.put(pl1, sl);
        l.put(pl2, sl);

        Map<Player, JLabel> labels = test.getLabels();
        assertEquals(labels.keySet(), l.keySet());

    }

}