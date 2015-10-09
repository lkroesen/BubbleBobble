package website.frontrow.logger;

import java.util.Date;
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
    private static boolean togglePrinting = false;

    /**
     * Add an action that occured to the current Map.
     * @param action
     * Input a String with the action that occured.
     */
    public static void add(String action)
    {
        String timestamp = "[" + (new Date()).toString() + "]\t";
        logMap.put(counter++, timestamp + action);

        if (togglePrinting)
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
        togglePrinting = false;
    }

    /**
     * Set a flag to enable system printing.
     */
    public static void togglePrinting()
    {
        togglePrinting = !togglePrinting;
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
     * Get the value of EnablePrinting.
     * @return
     * Return the value of EnablePrinting.
     */
    public static boolean isTogglePrinting()
    {
        return togglePrinting;
    }

    /**
     * Set the value of EnablePrinting.
     * @param togglePrinting
     * Returns the value of EnablePrinting.
     */
    public static void setTogglePrinting(boolean togglePrinting)
    {
        Log.togglePrinting = togglePrinting;
    }
}