package website.frontrow.util;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * Test the FileNameCollector.
 */
public class FileNameCollectorTest
{
    /**
     * Test if a NullPointerException is thrown when null is asked.
     * @throws URISyntaxException Throws URISyntaxException when URI is not valid.
     */
    @Test(expected = NullPointerException.class)
    public void testObtainNonExisting() throws URISyntaxException
    {
        new FileNameCollector().obtain("/foo/");
    }

    /**
     * Test if the right amount of sounds are returned, should be 12 of them.
     * @throws URISyntaxException Throws URISyntaxException when URI is not valid.
     * //TODO if the amount of sounds ever change update this test.
     */
    @Test
    public void testObtainSoundDir() throws URISyntaxException
    {
        assertEquals(new FileNameCollector().obtain("/sound/").length, 13);
    }

    /**
     * Test if a URISyntaxException is thrown when we ask for null.
     * @throws URISyntaxException Throws URISyntaxException when URI is not valid.
     */
    @Test(expected = URISyntaxException.class)
    public void testObtainNullDir() throws URISyntaxException
    {
        new FileNameCollector().obtain(null);
    }
}