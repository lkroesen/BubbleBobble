package website.frontrow.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create a file out of the current log.
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
/* Due to the way DumpLog is intended, this checkstyle warning will be ignored. */
public class DumpLog
{
    private File fTemp;

    /**
     * Dumps the entire Log of what happened in the program.
     */
    public DumpLog()
    {
        try
        {
            fTemp = File.createTempFile("DumpLog", ".txt");

            PrintWriter printWriter = new PrintWriter(fTemp, "UTF-8");
            for (long c = 0; c < Log.getLogMap().size(); c++)
            {
                printWriter.println(Log.getLogMap().get(c));
            }
            printWriter.close();
        }
        catch (IOException exception)
        {
            Log.add("[DUMP LOG]\tDump Log failed to make a DumpLog.");
            new DumpLog();
            exception.printStackTrace();
        }
    }

    /**
     * Get the file made.
     * @return  Returns the file.
     */
    public File getfTemp()
    {
        return fTemp;
    }
}
