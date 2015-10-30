package website.frontrow.util.keymap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test the Key Registry.
 */
@RunWith(MockitoJUnitRunner.class)
public class KeyRegistryTest
{
    @Mock
    private KeyAction keyAction;

    private Key key = new KeyCodeKey(1);

    private KeyRegistry registry = new KeyRegistry();

    /**
     * Test the register method.
     */
    @Test
    public void testRegister()
    {
        registry.register(key, keyAction);

        verify(keyAction, times(1)).bind(key, registry);
        assertTrue(registry.isUsed(key));
    }

    /**
     * Test registering twice.
     */
    @Test
    public void testRegisterTwice()
    {
        registry.register(key, keyAction);
        registry.register(key, keyAction);

        verify(keyAction, times(2)).bind(key, registry);
        verify(keyAction, times(1)).unbind(key, registry);
        assertTrue(registry.isUsed(key));
    }

    /**
     * Test the unregister method.
     */
    @Test
    public void testUnregister()
    {
        registry.register(key, keyAction);
        registry.unregister(key);

        verify(keyAction, times(1)).unbind(key, registry);
        assertFalse(registry.isUsed(key));
    }

    /**
     * Test the unregister method.
     */
    @Test
    public void testUnregisterNotPresent()
    {
        registry.unregister(key);

        verifyNoMoreInteractions(keyAction);
        assertFalse(registry.isUsed(key));
    }


    /**
     * Test for unregistered key.
     */
    @Test
    public void testHandle1()
    {
        registry.handle(key);

        verifyNoMoreInteractions(keyAction);
    }

    /**
     * Test for registered key.
     */
    @Test
    public void testHandle2()
    {
        registry.register(key, keyAction);
        registry.handle(key);

        verify(keyAction, times(1)).execute();
    }

}
