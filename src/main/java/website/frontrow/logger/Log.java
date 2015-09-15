package website.frontrow.logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Log Class, keeps track of all happenings using a map.
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
/*
I've decided that a constructor is unnecessary for the intended use of the logger,
As the logger is only using static elements, a constructor is not needed.
 */
public class Log
{
    private static Map<Long, String> logMap = new HashMap<>();
    private static long counter = 0;
    private static long startOfLoggingMillis = System.currentTimeMillis();
    private static boolean enablePrinting = false;

    /**
     * Add an action that occured to the current Map.
     * @param action
     * Input a String with the action that occured.
     */
    public static void add(String action)
    {
        String timestamp = "[" + (System.currentTimeMillis() - startOfLoggingMillis) + " ms] ";
        logMap.put(counter++, timestamp + action);

        if (enablePrinting)
        {
            System.out.println(timestamp + action);
        }
    }

    /**
     * Clear the log, by removing all data in the Hashmap.
     */
    public static void clearLog()
    {
        logMap.clear();
        counter = 0;
        startOfLoggingMillis = System.currentTimeMillis();
        enablePrinting = false;
    }

    /**
     * Set a flag to enable system printing.
     */
    public static void enablePrinting()
    {
        enablePrinting = !enablePrinting;
    }

    /**
     * Get the current logMap.
     * @return
     * Returns a Map<Long, String>.
     */
    public static Map<Long, String> getLogMap()
    {
        return logMap;
    }

    /**
     * Set the logMap value.
     * @param logMap
     * Set the value of the LogMap.
     */
    public static void setLogMap(Map<Long, String> logMap)
    {
        Log.logMap = logMap;
    }

    /**
     * Get the counter value.
     * @return
     * Get the current counter value.
     */
    public static long getCounter()
    {
        return counter;
    }

    /**
     * Set the counter value.
     * @param counter
     * Input a long that is the counter value.
     */
    public static void setCounter(long counter)
    {
        Log.counter = counter;
    }

    /**
     * Get the start of logging millis.
     * @return
     * Returns a long.
     */
    public static long getStartOfLoggingMillis()
    {
        return startOfLoggingMillis;
    }

    /**
     * set the StartOfLoggingMillis.
     * @param startOfLoggingMillis
     * Input the moment in time in millis of the start of the program.
     */
    public static void setStartOfLoggingMillis(long startOfLoggingMillis)
    {
        Log.startOfLoggingMillis = startOfLoggingMillis;
    }

    /**
     * Get the value of EnablePrinting.
     * @return
     * Return the value of EnablePrinting.
     */
    public static boolean isEnablePrinting()
    {
        return enablePrinting;
    }

    /**
     * Set the value of EnablePrinting.
     * @param enablePrinting
     * Returns the value of EnablePrinting.
     */
    public static void setEnablePrinting(boolean enablePrinting)
    {
        Log.enablePrinting = enablePrinting;
    }
}
