package website.frontrow.level;

import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Parses a text file into level.
 */
public class MapParser {

    /**
     * Check whether a level formed by these strings would create a validly shaped level.
     * It fails whenever:
     *  - The size is zero in a single dimension.
     *  - The shape of the strings do not form a rectangle.
     * @param lines The lines of the map.
     * @return Whether these strings will create a validly shaped level.
     */
    private boolean validateLevelShape(String[] lines)
    {
        if(lines.length < 1) return false;

        int len = lines[0].length();
        if(len < 1) return false;

        for (String line: lines)
        {
            if (line.length() != len) return false;
        }

        return true;
    }


    /**
     * Read the lines from an inputstream and attempt to parse them into a level.
     * Will fail if:
     *  - the shape of the level is either non-square.
     *  - one dimension is zero.
     * @param stream The stream to read from.
     * @return A Level generated from the given inputstream.
     * @throws IOException In case the stream is invalid.
     */
    public Level parseMap(InputStream stream) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        int len = -1;
        String line;
        ArrayList<String> lines = new ArrayList<String>();

        while ((line = reader.readLine()) != null)
        {
            lines.add(line);
        }

        return parseMap((String[]) lines.toArray());
    }

    /**
     * Parse lines into a level.
     * Will fail if:
     *  - the shape of the level is either non-square.
     *  - one dimension is zero.
     * @param lines The lines to parse.
     * @return A level generated from the given lines.
     */
    public Level parseMap(String[] lines)
    {
        if (!validateLevelShape(lines)) throw new RuntimeException("The given lines are invalidly shaped or zero size.");
        Grid<Cell> grid = new Grid<Cell>(lines[0].length(), lines.length);
        ArrayList<Unit> units = new ArrayList<Unit>();

        int y = 0;
        for (String line: lines)
        {
            int x = 0;
            for(char character: line.toCharArray())
            {
                handleCharacter(character, x, y, units, grid);
                x++;
            }
            ++y;
        }

        return new Level(units, grid);
    }

    /**
     * Handle behaviour of a certain character
     * @param character The character to parse.
     * @param x x position of the character according to the grid.
     * @param y y position of the character according to the grid,
     * @param units List of where to place units in.
     * @param grid Grid to place the map in.
     */
    private void handleCharacter(char character, int x, int y, ArrayList<Unit> units, Grid<Cell> grid)
    {
        grid.set(x, y, Cell.EMPTY);
        switch (character)
        {
            case 'X':
                // A wall
                grid.set(x, y, Cell.WALL);
                break;
            case '_':
                // A platform
                grid.set(x, y, Cell.PLATFORM);
                break;
            case 'p':
                // The player
                units.add(new Player(new Point(x, y)));
                break;
            case 'e':
                // The enemy.
                units.add(new Enemy(new Point(x, y)));
                break;
            case ' ':
                // Empty area. Keep it empty.
                break;
            default:
                throw new RuntimeException("Invalid character, \"" + character + "'\".");
        }
    }
}
