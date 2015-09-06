package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test the image store.
 * Created by larsstegman on 06-09-15.
 */
public class TestImageStore
{
    private ImageStore is;

    @Before
    public void setUp()
    {
        is = new ImageStore();
    }

    @After
    public void tearDown()
    {
        is = null;
    }

    @Test
    public void testGetBorderImage()
    {
        assertNotNull(is.getBorderImage());
    }
}
