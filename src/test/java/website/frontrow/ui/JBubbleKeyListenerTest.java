package website.frontrow.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.event.KeyEvent;
import website.frontrow.util.keymap.KeyAction;
import website.frontrow.util.keymap.KeyCodeKey;
import website.frontrow.util.keymap.KeyRegistry;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for the JBubbleKeyListener class.
 */
@RunWith(MockitoJUnitRunner.class)
public class JBubbleKeyListenerTest
{
    private KeyRegistry keyRegistry;

    @Mock
    private KeyEvent event;

    /**
     * Sets the things up.
     */
    @Before
    public void setUp()
    {
        keyRegistry = new KeyRegistry();
    }

    /**
     * Cleans up after the tests.
     */
    @After
    public void tearDown()
    {
        keyRegistry = null;
    }

    /**
     * Tests whether the action is performed when pressing a key.
     */
    @Test
    public void testKeyPressed()
    {
        KeyAction action = mock(KeyAction.class);
        keyRegistry.register(new KeyCodeKey(KeyEvent.VK_ENTER), action);
        JBubbleKeyListener listener = new JBubbleKeyListener(keyRegistry);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);

        listener.keyPressed(event);
        listener.update();
        
        verify(action).execute();
    }

    /**
     * Tests whether the action is not performed when an unknown key is pressed.
     */
    @Test
    public void testKeyPressedUnknownKey()
    {
        KeyAction action = mock(KeyAction.class);
        keyRegistry.register(new KeyCodeKey(KeyEvent.VK_ENTER), action);
        JBubbleKeyListener listener = new JBubbleKeyListener(keyRegistry);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        listener.keyPressed(event);
        verify(action, never()).execute();
    }

    /**
     * Tests whether the action is not performed when a key is typed.
     */
    @Test
    public void testKeyTyped()
    {
        KeyAction action = mock(KeyAction.class);
        keyRegistry.register(new KeyCodeKey(KeyEvent.VK_ENTER), action);
        JBubbleKeyListener listener = new JBubbleKeyListener(keyRegistry);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        listener.keyTyped(event);

        verify(action, never()).execute();
    }

    /**
     * Tests whether the action is not performed when a key is released.
     */
    @Test
    public void testKeyReleased()
    {
        KeyAction action = mock(KeyAction.class);
        keyRegistry.register(new KeyCodeKey(KeyEvent.VK_ENTER), action);
        JBubbleKeyListener listener = new JBubbleKeyListener(keyRegistry);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        listener.keyReleased(event);

        verify(action, never()).execute();
    }
}
