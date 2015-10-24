package website.frontrow.util.keymap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test KeyAction.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class KeyActionTest
{

    @Mock
    Runnable runnable;

    KeyAction keyAction;

    @Before
    public void before()
    {
        keyAction = getKeyAction(runnable);
    }

    /**
     * Test the execute method.
     */
    @Test
    public void testExecute()
    {
        keyAction.execute();

        verify(runnable, times(1)).run();
    }

    /**
     * Test the notifyObservers method.
     */
    @Test
    public void testNotifyObservers()
    {
        Key key = mock(Key.class);
        KeyActionObserver observer = mock(KeyActionObserver.class);

        keyAction.registerObserver(observer);
        keyAction.notifyObservers(key);

        verify(observer, times(1)).onKeyChange(key);
    }

    /**
     * Test the removeObserver method.
     */
    @Test
    public void testRemoveObserver()
    {
        Key key = mock(Key.class);
        KeyActionObserver observer = mock(KeyActionObserver.class);

        keyAction.registerObserver(observer);
        keyAction.removeObserver(observer);
        keyAction.notifyObservers(key);

        verifyNoMoreInteractions(observer);
    }

    /**
     * Create a keyaction.
     *
     * @return A keyaction object.
     */
    public abstract KeyAction getKeyAction(Runnable runnable);

}
