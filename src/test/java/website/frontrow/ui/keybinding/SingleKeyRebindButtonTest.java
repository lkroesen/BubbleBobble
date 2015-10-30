package website.frontrow.ui.keybinding;

import java.awt.event.KeyEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import website.frontrow.util.keymap.KeyRegistry;
import website.frontrow.util.keymap.SingleKeyAction;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test the SingleKeyRebindButton
 */
@RunWith(MockitoJUnitRunner.class)
public class SingleKeyRebindButtonTest
{
    @Mock
    KeyRegistry registry;

    SingleKeyAction action = new SingleKeyAction(null, true);

    SingleKeyRebindButton button;

    @Before
    public void prepare()
    {
        button = new SingleKeyRebindButton(registry, action);
    }

    @Test
    public void testPressKeyWithoutSelecting()
    {
        button.keyPressed(new KeyEvent(button, 0, 0, 0, KeyEvent.VK_A, 'a'));
        verifyNoMoreInteractions(registry);
    }

    @Test
    public void testPressKeyWithSelecting()
    {
        button.setSelected(true);
        button.keyPressed(new KeyEvent(button, 0, 0, 0, KeyEvent.VK_A, 'a'));
        verify(registry).register(any(), eq(action));
    }

    @Test
    public void testPressEscWithSelecting()
    {
        button.setSelected(true);
        button.keyPressed(new KeyEvent(button, 0, 0, 0, KeyEvent.VK_ESCAPE, 'E'));
        verifyNoMoreInteractions(registry);
        assertFalse(button.isSelected());
    }

    @Test
    public void testKeyReleasedDoesNothing()
    {
        button.setSelected(true);
        button.keyReleased(new KeyEvent(button, 0, 0, 0, KeyEvent.VK_A, 'a'));
        verifyNoMoreInteractions(registry);
        assertTrue(button.isSelected());
    }

    @Test
    public void testKeyTypedDoesNothing()
    {
        button.setSelected(true);
        button.keyTyped(new KeyEvent(button, 0, 0, 0, KeyEvent.VK_A, 'a'));
        verifyNoMoreInteractions(registry);
        assertTrue(button.isSelected());
    }
}
