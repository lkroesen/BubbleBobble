package website.frontrow.util.keymap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * A combined test that checks if everything works together.
 */
@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest
{

    private KeyRegistry registry = new KeyRegistry();

    @Mock
    private Runnable runnable;

    /**
     * Simple keypress test.
     */
    @Test
    public void testKeyPress()
    {
        registry.register(new KeyCodeKey(1),
                new SingleKeyAction(runnable, false));

        registry.handle(new KeyCodeKey(1));

        verify(runnable).run();
    }

    /**
     * Unregistration test.
     */
    @Test
    public void testUnregister()
    {
        KeyAction action = new SingleKeyAction(runnable, false);
        registry.register(new KeyCodeKey(1),
                action);

        assertTrue(action.isBound());

        registry.unregister(new KeyCodeKey(1));
        assertFalse(action.isBound());

        registry.handle(new KeyCodeKey(1));

        verify(runnable, never()).run();
    }
}
