package website.frontrow.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create a file out of the current log.
 */
public final class DumpLog
{
    private File temporaryFile;
    private static final DumpLog INSTANCE = new DumpLog();

    /**
     * Private Constructor because, Singleton.
     */
    private DumpLog()
    {
    }

    /**
     * Returns the DumpLog Instance.
     * @return Returns the Instance.
     */
    public static DumpLog getInstance()
    {
        return INSTANCE;
    }

    /**
     * Dumps the entire Log of what happened in the program.
     */
    public void createDump()
    {
        try
        {
            temporaryFile = File.createTempFile("DumpLog", ".txt");

            PrintWriter printWriter = new PrintWriter(temporaryFile, "UTF-8");
            for (long c = 0; c < Log.getLogMap().size(); c++)
            {
                printWriter.println(Log.getLogMap().get(c));
            }
            printWriter.close();
        }
        catch (IOException exception)
        {
            Log.add("[DUMP LOG]\tDump Log failed to make a DumpLog.");
            exception.printStackTrace();
        }
    }

    /**
     * Get the file made.
     * @return  Returns the file.
     */
    public File getTemporaryFile()
    {
        return temporaryFile;
    }
}
