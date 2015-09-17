package website.frontrow.logger;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

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
     * Test if togglePrinting makes the flag true.
     */
    @Test
    public void testEnablePrinting()
    {
        Log.clearLog();

        Log.setTogglePrinting(true);
        assertTrue(Log.isTogglePrinting());
    }

    /**
     * Test with togglePrinting enabled.
     */
    @Test
    public void testAddEnabledPrinting()
    {
        Log.clearLog();

        Log.togglePrinting();
        Log.add("Hi");
    }

    /**
     * Test setlogmap.
     */
    @Test
    public void testSetLogMap()
    {
        Map<Long, String> oldLog = Log.getLogMap();
        Log.setLogMap(null);
        assertNull(Log.getLogMap());

        Log.setLogMap(oldLog);
        assertEquals(oldLog, Log.getLogMap());
    }

    /**
     * Test counter getter/setter.
     */
    @Test
    public void testSetGetCounter()
    {
        Long oldCounter = Log.getCounter();
        Log.setCounter(-1L);
        assertNotEquals(Log.getCounter(), (long) oldCounter);

        Log.setCounter(oldCounter);
        assertEquals(Log.getCounter(), (long) oldCounter);
    }

    /**
     * Test millis time setter.
     */
    @Test
    public void testSetMillis()
    {
        long old = Log.getStartOfLoggingMillis();
        Log.setStartOfLoggingMillis(0L);
        assertNotEquals(Log.getStartOfLoggingMillis(), old);

        Log.setStartOfLoggingMillis(old);
        assertEquals(Log.getStartOfLoggingMillis(), old);
    }
}