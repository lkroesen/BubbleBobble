package website.frontrow.util;

import sun.misc.IOUtils;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A method for indexing the levels in the resources and returning them.
 */
public class FileNameCollector
    implements Logable
{
    /**
     * This method is used for obtaining the files names in a directory.
     * @param dir Input a string of the directory, levels are in: /level/.
     * @return Returns a String array with the directory + their file names.
     * @throws URISyntaxException Throws URISyntaxException when URI is not valid.
     */
    public String[] obtain(String dir) throws URISyntaxException
    {
        if (dir == null)
        {
            addToLog("[LC]\t[ERROR]\tDirectory is null.");
            throw new URISyntaxException("null", "Null is not an accepted Syntax as a directory.");
        }

        InputStream inputStream = FileNameCollector.class.getResourceAsStream(dir);

        Scanner scanner = new Scanner(inputStream);

        ArrayList<String> stringList = new ArrayList<>();

        // Get the amount of levels.
        while(scanner.hasNextLine())
        {
            stringList.add(dir + scanner.nextLine());
        }

        String[] levels = new String[stringList.size()];

        for (int c = 0; c < levels.length; c++)
        {
            levels[c] = stringList.get(c);
        }

        scanner.close();

        return levels;
    }

    /**
     * Log actions in the FileNameCollector.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
