package website.frontrow.level;

import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
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
public class MapParser
    implements Logable
{

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
        if(lines.length < 1)
        {
            addToLog("[ERROR]\t[MAP PARSER]\t1 - Input length is too small.");
            return false;
        }

        int len = lines[0].length();
        if(len < 1)
        {
            addToLog("[ERROR]\t[MAP PARSER]\t2 - Line length too small.");
            return false;
        }

        for (String line: lines)
        {
            if (line.length() != len)
            {
                addToLog("[ERROR]\t[MAP PARSER]\t3 - One of the lines in the file "
                        + "is not as long as the other.");
                return false;
            }
        }

        addToLog("[MAP PARSER]\tLevel was parsed successfully.");
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
        addToLog("[MAP PARSER]\tparseMap() called.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        ArrayList<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null)
        {
            lines.add(line);
        }

        addToLog("[MAP PARSER]\tparseMap() succeeded.");
        return parseMap(lines.toArray(new String[lines.size()]));
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
        addToLog("[MAP PARSER]\tparseMap() called.");
        if (!validateLevelShape(lines))
        {
            addToLog("[ERROR]\t[MAP PARSER]\t4 - RuntimeException.");
            throw new RuntimeException("The given lines are invalidly shaped or zero size.");
        }
        Grid<Cell> grid = new Grid<>(lines[0].length(), lines.length);
        ArrayList<Unit> units = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();

        int y = 0;
        for (String line: lines)
        {
            int x = 0;
            for(char character: line.toCharArray())
            {
                handleCharacter(character, x, y, units, players, grid);
                x++;
            }
            ++y;
        }

        addToLog("[MAP PARSER]\tparseMap() completed successfully.");
        return new Level(players, units, grid);
    }

    /**
     * Handle behaviour of a certain character.
     * @param character The character to parse.
     * @param x x position of the character according to the grid.
     * @param y y position of the character according to the grid,
     * @param units List of where to place units in.
     * @param grid Grid to place the map in.
     */
    private void handleCharacter(char character, int x, int y,
                                 ArrayList<Unit> units, ArrayList<Player> players, Grid<Cell> grid)
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
                Player player = new Player(new Point(x, y));
                units.add(player);
                players.add(player);
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

    /**
     * Log actions taken by MapParser.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
