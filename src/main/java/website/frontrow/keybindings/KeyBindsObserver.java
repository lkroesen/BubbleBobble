package website.frontrow.keybindings;

/**
 * Any object which implements this observer and registers itself as an observer is notified when
 * the key bindings are changed.
 */
public interface KeyBindsObserver
{
    /**
     * Is called when a key binding has changed.
     */
    void keyBindingChanged();
}
