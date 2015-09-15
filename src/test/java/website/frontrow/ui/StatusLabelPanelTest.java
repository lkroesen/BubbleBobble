package website.frontrow.ui;

import org.junit.Test;
import org.mockito.Mock;
import website.frontrow.game.Game;
import website.frontrow.board.Player;
import website.frontrow.level.Level;

import javax.swing.JLabel;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Remi Flinterman on 13-9-2015.
 */
public class StatusLabelPanelTest
{

    @Mock private ArrayList<Player> p;
    @Mock private Level l;
    private Game g = new Game(l, p);

    /**
     * Tests whether or not runningLabel turns out correctly when calling
     * the StatusLabelPanel constructor.
     */
    @Test
    public void runningLabelTest()
    {

        StatusLabelPanel slp = new StatusLabelPanel(g);

        JLabel rl = slp.getRunningLabel();
        JLabel label = new JLabel("Paused");

        assertEquals(rl.toString(), label.toString());

    }

    /**
     * Tests the setter of runningLabel.
     */
    @Test
    public void setRunningLabelTest()
    {

        StatusLabelPanel slp = new StatusLabelPanel(g);

        JLabel label = new JLabel("Test");
        slp.setRunningLabel(label);

        assertEquals(slp.getRunningLabel().toString(), label.toString());

    }

    /**
     * Tests the getter of runningLabel.
     */
    @Test
    public void getRunningLabelTest()
    {

        StatusLabelPanel slp = new StatusLabelPanel(g);

        JLabel label = new JLabel("Paused");
        JLabel l = slp.getRunningLabel();

        assertEquals(l.toString(), label.toString());

    }

}
