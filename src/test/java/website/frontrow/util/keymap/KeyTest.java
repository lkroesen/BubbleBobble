package website.frontrow.util.keymap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Generic test for all keys.
 */
public abstract class KeyTest
{

    /**
     * Test whether two keys that are the same returns true.
     */
    @Test
    public void testEqualsTrue()
    {
        Key keyA = createKey1();
        Key keyB = createKey1();

        assertEquals(keyA, keyB);
    }

    /**
     * Test whether two keys that are the same returns true.
     */
    @Test
    public void testEqualsFalse()
    {
        Key keyA = createKey1();
        Key keyB = createKey2();

        assertNotEquals(keyA, keyB);
    }

    /**
     * Test whether two keys that are the same returns true.
     */
    @Test
    public void testEqualsFalseWrongType()
    {
        Key keyA = createKey1();

        assertNotEquals(keyA, false);
    }

    /**
     * Test whether two hashcodes of the same data are the same.
     */
    @Test
    public void testHashCodeTrue()
    {
        Key keyA = createKey1();
        Key keyB = createKey1();

        assertEquals(keyA.hashCode(), keyB.hashCode());
    }

    /**
     * Test whether two hashcodes of the different data are not the same.
     */
    @Test
    public void testHashCodeFalse()
    {
        Key keyA = createKey1();
        Key keyB = createKey2();

        assertNotEquals(keyA.hashCode(), keyB.hashCode());
    }

    /**
     * Create a Key.
     *
     * @return A key that is different from Key2.
     */
    public abstract Key createKey1();

    /**
     * Create a Key.
     *
     * @return A key that is different from Key1.
     */
    public abstract Key createKey2();
}
