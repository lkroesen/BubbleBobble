package website.frontrow.level;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.util.Grid;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for level methods.
 */
@RunWith(MockitoJUnitRunner.class)
public class LevelTest {

    public ArrayList<Unit> units = new ArrayList<>();
    public Grid<Cell> grid = new Grid<Cell>(1, 1);
    @Mock
    public Player player;
    @Mock
    public Enemy enemy;
    public ArrayList<Unit> mockunits = new ArrayList<>();

    /**
     * Set up.
     *  - Add items to mocklist.
     */
    @Before
    public void setUp()
    {
        mockunits.add(player);
        mockunits.add(enemy);
        mockunits.add(enemy);
    }

    /**
     * Test getCells().
     */
    @Test
    public void testGetCells()
    {
        Level level = new Level(units, grid);
        assertEquals(grid, level.getCells());
    }

    /**
     *  test getUnits().
     */
    @Test
    public void testGetUnits()
    {
        Level level = new Level(units, grid);
        assertEquals(units, level.getUnits());
    }

    /**
     * Test ticking the units.
     */
    @Test
    public void testTick()
    {
        Level level = new Level(mockunits, grid);
        level.tick();
        verify(player, times(1)).tick(level);
        verify(enemy, times(2)).tick(level);
    }
}