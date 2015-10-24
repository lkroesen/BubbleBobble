package website.frontrow.util.keymap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests KeyCodeKey.
 */
public class KeyCodeKeyTest extends KeyTest
{

    /**
     * Test the getKeyCode method.
     */
    @Test
    public void testGetKeyCode()
    {
        KeyCodeKey key = new KeyCodeKey(1);
        assertEquals(key.getKeyCode(), new Integer(1));
    }

    @Override
    public Key createKey1()
    {
        return new KeyCodeKey(1);
    }

    @Override
    public Key createKey2()
    {
        return new KeyCodeKey(2);
    }
}
