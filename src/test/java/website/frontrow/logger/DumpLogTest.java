package website.frontrow.logger;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test DumpLog.
 */
public class DumpLogTest
{
    /**
     * See if a file is made when a new DumpLog is made.
     */
    @Test
    public void testDumping()
    {
        DumpLog.getInstance().createDump();
        assertTrue(DumpLog.getInstance().getTemporaryFile().exists());
    }
}