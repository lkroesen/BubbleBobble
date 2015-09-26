package website.frontrow.util;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

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

        URL url = this.getClass().getResource(dir);
        File levelDir = new File(url.toURI());

        String[] levels = levelDir.list();
        assert levels != null;

        for(int c = 0; c < levels.length; c++)
        {
            levels[c] = dir + levels[c];
        }

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
