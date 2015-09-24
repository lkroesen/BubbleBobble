package website.frontrow.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import website.frontrow.game.Game;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Test the UI.
 */
public class JBubbleBobbleUITest
{
    @Mock
    private Map<Integer, Action> mockedMap;

    private Level level;
    private Game g;

    /**
     * Setup, run before tests.
     */
    @Before
    public void setup()
    {
        ArrayList<Player> pl = new ArrayList<>();
        ArrayList<Unit> ul = new ArrayList<>();
        ArrayList<Level> le = new ArrayList<>();
        Grid<Cell> grid = new Grid<>(2, 2);

        pl.add(new Player(new Point(0, 0)));
        ul.add(new Enemy(new Point(1, 1)));

        level = new Level(pl, ul, grid);
        le.add(level);

        g = new Game(le);
    }

    /**
     * Teardown, run after tests.
     */
    @After
    public void teardown()
    {
        level = null;
        g = null;
    }

    /**
     * Test if the ui is not null after creation.
     */
    @Test
    public void testConstructor()
    {
        JBubbleBobbleUI ui = new JBubbleBobbleUI(g, mockedMap);
        assertNotNull(ui);
    }
}