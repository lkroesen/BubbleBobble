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
import website.frontrow.util.keymap.KeyRegistry;

import static org.junit.Assert.assertNotNull;

/**
 * Test the UI.
 */
public class JBubbleBobbleUITest
{
    @Mock
    private KeyRegistry mockedRegistry;

    private Level level;
    private Game game;

    /**
     * Setup, run before tests.
     */
    @Before
    public void setup()
    {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Unit> units = new ArrayList<>();
        ArrayList<Level> levels = new ArrayList<>();
        Grid<Cell> grid = new Grid<>(2, 2);

        players.add(new Player(new Point(0, 0), null));
        units.add(new Enemy(new Point(1, 1), null));

        level = new Level(players, units, grid);
        levels.add(level);

        game = new Game(levels);
    }

    /**
     * Teardown, run after tests.
     */
    @After
    public void teardown()
    {
        level = null;
        game = null;
    }

    /**
     * Test if the ui is not null after creation.
     */
    @Test
    public void testConstructor()
    {
        JBubbleBobbleUI jBubbleBobbleUI = new JBubbleBobbleUI(game, mockedRegistry);
        assertNotNull(jBubbleBobbleUI);
    }
}