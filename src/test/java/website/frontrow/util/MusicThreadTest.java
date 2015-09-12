package website.frontrow.util;

import org.junit.Test;

/**
 * Test Music Thread.
 */
public class MusicThreadTest
{
    /**
     * Test for exception.
     */
    @Test(expected = RuntimeException.class)
    public void testRun()
    {
        MusicThread mt = new MusicThread();
        mt.run(null);
    }
}