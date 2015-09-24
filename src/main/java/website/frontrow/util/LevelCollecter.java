package website.frontrow.util;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A method for indexing the levels in the resources and returning them.
 */
public class LevelCollecter
    implements Logable
{

    public String[] obtain(String dir)
    {
        try
        {
            URL url = this.getClass().getResource(dir);
            File levelDir = new File(url.toURI());

            if(!levelDir.exists())
            {
                addToLog("[LC]\t[ERROR]\tDirectory: " + dir + " could not be found.");
                throw new IOException("Level directory was not found.");
            }

            String[] levels = levelDir.list();

            for (int c = 0; c < levels.length; c++)
            {
                levels[c] = dir + levels[c];
            }

            addToLog("[LC]\t" + levels.length + " levels were found.");

            return levels;
        }
        catch (URISyntaxException exception)
        {
            addToLog("[LC]\t[ERROR]\tURI Syntax Exception occurred.");
            exception.printStackTrace();
        }
        catch (IOException exception)
        {
            addToLog("[LC]\t[ERROR]\tIO Exception occurred.");
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Log actions in the LevelCollecter.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
