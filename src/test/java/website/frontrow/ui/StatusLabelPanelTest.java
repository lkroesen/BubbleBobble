package website.frontrow.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.game.Game;
import website.frontrow.level.Level;

import javax.swing.JLabel;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * A test for the status label panel.
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusLabelPanelTest
{

    @Mock
    private Level level;

    private ArrayList<Level> levels = new ArrayList<>();
    private Game game;

    /**
     * Set up for testing.
     */
    @Before
    public void setUp()
    {
        when(level.duplicate()).thenReturn(level);
        levels.add(level);
        game = new Game(levels);
    }

    /**
     * Tests whether or not runningLabel turns out correctly when calling
     * the StatusLabelPanel constructor.
     */
    @Test
    public void runningLabelTest()
    {
        StatusLabelPanel slp = new StatusLabelPanel(game);

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
        StatusLabelPanel slp = new StatusLabelPanel(game);

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
        StatusLabelPanel slp = new StatusLabelPanel(game);

        JLabel label = new JLabel("Paused");
        JLabel l = slp.getRunningLabel();

        assertEquals(l.toString(), label.toString());
    }
}
