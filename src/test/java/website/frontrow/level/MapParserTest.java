package website.frontrow.level;

import org.junit.Test;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the mapparser class.
 */
public class MapParserTest {

    /**
     * Test whether maps are correctly imported.
     */
    @Test
    public void testSimpleMap()
    {
        String[] map = new String[]{"X_X","X X","XXX"};
        MapParser parser = new MapParser();
        Level res = parser.parseMap(map);

        // There are no units on the map.
        assertEquals(res.getUnits().size(), 0);
        // Test the squares
        Grid<Cell> expected = new Grid<Cell>(Arrays.asList(
                Cell.WALL, Cell.PLATFORM, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3);
        assertEquals(expected, res.getCells());
    }

    /**
     * Test whether players are correctly loaded.
     */
    @Test
    public void testPlayerMap()
    {
        String[] map = new String[]{"XXX","XpX","XXX"};
        MapParser parser = new MapParser();
        Level res = parser.parseMap(map);

        // There is a single unit (the player) on the map.
        assertEquals(res.getUnits().size(), 1);
        assertTrue(res.getUnits().get(0) instanceof Player);
        assertEquals(new Point(1, 1), res.getUnits().get(0).getLocation());
    }

    /**
     * Test whether enemies are correctly loaded.
     */
    @Test
    public void testEnemyMap()
    {
        String[] map = new String[]{"XXX","XeX","XXX"};
        MapParser parser = new MapParser();
        Level res = parser.parseMap(map);

        // There is a single unit (the player) on the map.
        assertEquals(res.getUnits().size(), 1);
        assertTrue(res.getUnits().get(0) instanceof Enemy);
        assertEquals(new Point(1, 1), res.getUnits().get(0).getLocation());

    }

    /**
     * Test whether the parser fails on unknown characters.
     */
    @Test(expected = RuntimeException.class)
    public void testFailOnInvalidCharacter()
    {
        String[] map = new String[]{"XXX","XzX","XXX"};
        MapParser parser = new MapParser();
        parser.parseMap(map);
    }

    /**
     * Test invalid shape.
     */
    @Test(expected = RuntimeException.class)
    public void testFailOnInvalidShape1()
    {
        String[] map = new String[]{"XXX","XXXX","XXX"};
        MapParser parser = new MapParser();
        parser.parseMap(map);
    }

    /**
     * Test invalid shape.
     */
    @Test(expected = RuntimeException.class)
    public void testFailOnInvalidShape2()
    {
        String[] map = new String[]{"XXX","","XXX"};
        MapParser parser = new MapParser();
        parser.parseMap(map);
    }

    /**
     * Test invalid shape.
     */
    @Test(expected = RuntimeException.class)
    public void testFailOnInvalidShape3()
    {
        String[] map = new String[]{};
        MapParser parser = new MapParser();
        parser.parseMap(map);
    }

    /**
     * Test invalid shape.
     */
    @Test(expected = RuntimeException.class)
    public void testFailOnInvalidShape4()
    {
        String[] map = new String[]{"", "", ""};
        MapParser parser = new MapParser();
        parser.parseMap(map);
    }

    /**
     * Test file reader.
     * @throws IOException Because we are reading a file.
     */
    @Test
    public void testFileLoader() throws IOException
    {
        MapParser parser = new MapParser();
        Level level = parser.parseMap(getClass().getResourceAsStream("/testLevelLoader.txt"));
        Grid<Cell> expected = new Grid<Cell>(Arrays.asList(
                Cell.WALL, Cell.WALL, Cell.WALL,
                Cell.WALL, Cell.EMPTY, Cell.WALL,
                Cell.WALL, Cell.WALL, Cell.WALL), 3, 3);
        assertEquals(0, level.getUnits().size());
        assertEquals(expected, level.getCells());
    }

}
