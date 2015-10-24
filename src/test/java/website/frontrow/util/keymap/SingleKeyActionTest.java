package website.frontrow.util.keymap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests the SingleKeyActionTest class.
 */
@RunWith(MockitoJUnitRunner.class)
public class SingleKeyActionTest extends KeyActionTest
{
    Key key1 = new KeyCodeKey(1);
    Key key2 = new KeyCodeKey(2);

    @Mock
    Runnable runnable;
    @Mock
    KeyRegistry keyRegistry;

    KeyAction keyAction;

    @Before
    public void before()
    {
        super.before();
        keyAction = getKeyAction(runnable);
    }

    /**
     * Test automatically unbinding the previous key.
     */
    @Test
    public void testBindTwice()
    {
        keyAction.bind(key1, keyRegistry);
        keyAction.bind(key2, keyRegistry);

        assertTrue(keyAction.isBound());
        verify(keyRegistry, times(1)).unregister(key1);
    }

    /**
     * Test unbinding.
     */
    @Test
    public void testUnbind()
    {
        keyAction.bind(key1, keyRegistry);
        keyAction.unbind(key1, keyRegistry);

        assertFalse(keyAction.isBound());
    }

    @Override
    public KeyAction getKeyAction(Runnable runnable)
    {
        return new SingleKeyAction(runnable);
    }
}
