package website.frontrow.logger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the Log function.
 */
public class LogTest
{
    /**
     * Run before tests.
     */
    @Before
    public void setup()
    {
        new Log();
    }


    /**
     * Test if adding an element works properly.
     */
    @Test
    public void testAdd()
    {
        Log.clearLog();

        Log.add("42");
        assertEquals(Log.getLogMap().size(), 1);
    }

    /**
     * Test if clear log works properly.
     */
    @Test
    public void testClearLog()
    {
        Log.clearLog();

        Log.add("In");
        assertEquals(Log.getLogMap().size(), 1);
        Log.clearLog();
        assertEquals(Log.getLogMap().size(), 0);
    }

    /**
     * Test if enablePrinting makes the flag true.
     */
    @Test
    public void testEnablePrinting()
    {
        Log.clearLog();

        Log.enablePrinting();
        assertTrue(Log.isEnablePrinting());
    }

    /**
     * Test with enablePrinting enabled.
     */
    @Test
    public void testAddEnabledPrinting()
    {
        Log.clearLog();

        Log.enablePrinting();
        Log.add("Hi");
    }

    // TODO getter, setter tests.
}