package website.frontrow.util.keymap;

/**
 * A single key action. Only allows one key to press it at a given time.
 */
public class SingleKeyAction
        extends KeyAction
{
    private KeyRegistry currentRegistry = null;
    private Key currentKey = null;

    /**
     * Construct a keyAction.
     *
     * @param action The action to execute.
     */
    public SingleKeyAction(Runnable action)
    {
        super(action);
    }

    @Override
    protected void unbind(Key key, KeyRegistry registry)
    {
        assert key != null : "Key was null";
        assert registry != null : "Registry was null";
        assert key.equals(currentKey) : "Attempt to unbind key from incorrect key.";
        assert registry.equals(currentRegistry) : "Attempt to unbind from different registry.";
        currentKey = null;
        this.notifyObservers(null);
    }

    @Override
    protected void bind(Key key, KeyRegistry registry)
    {
        // Forcibly unbind from the previous key.
        if (this.currentKey != null)
        {
            currentRegistry.unregister(this.currentKey);
        }

        this.notifyObservers(key);
        this.currentRegistry = registry;
        this.currentKey = key;
    }

    @Override
    public boolean isBound()
    {
        return currentKey != null;
    }

}
