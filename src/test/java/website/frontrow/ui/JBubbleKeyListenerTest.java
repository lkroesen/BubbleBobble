package website.frontrow.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for the JBubbleKeyListener class.
 */
@RunWith(MockitoJUnitRunner.class)
public class JBubbleKeyListenerTest
{
    private Map<Integer, Action> mapping;

    @Mock
    private KeyEvent event;

    /**
     * Sets the things up.
     */
    @Before
    public void setUp()
    {
        mapping = new HashMap<>();
    }

    /**
     * Cleans up after the tests.
     */
    @After
    public void tearDown()
    {
        mapping = null;
    }

    /**
     * Tests whether the action is performed when pressing a key.
     */
    @Test
    public void testKeyPressed()
    {
        Action action = mock(Action.class);
        mapping.put(KeyEvent.VK_ENTER, action);

        JBubbleKeyListener listener = new JBubbleKeyListener(mapping);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);

        listener.keyPressed(event);

        verify(action).doAction();
    }

    /**
     * Tests whether the action is not performed when an unknown key is pressed.
     */
    @Test
    public void testKeyPressedUnknownKey()
    {
        Action action = mock(Action.class);
        mapping.put(KeyEvent.VK_ENTER, action);
        JBubbleKeyListener listener = new JBubbleKeyListener(mapping);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        listener.keyPressed(event);
        verifyZeroInteractions(action);
    }

    /**
     * Tests whether the action is not performed when a key is typed.
     */
    @Test
    public void testKeyTyped()
    {
        Action action = mock(Action.class);
        mapping.put(KeyEvent.VK_ENTER, action);
        JBubbleKeyListener listener = new JBubbleKeyListener(mapping);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        listener.keyTyped(event);

        verifyZeroInteractions(action);
    }

    /**
     * Tests whether the action is not performed when a key is released.
     */
    @Test
    public void testKeyReleased()
    {
        Action action = mock(Action.class);
        mapping.put(KeyEvent.VK_ENTER, action);
        JBubbleKeyListener listener = new JBubbleKeyListener(mapping);

        when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        listener.keyReleased(event);

        verifyZeroInteractions(action);
    }

}
