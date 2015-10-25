package website.frontrow.util.keymap;

/**
 * A single key action. Only allows one key to press it at a given time.
 */
public class SingleKeyAction
        extends KeyAction
{
    private KeyRegistry currentRegistry = null;
    private Key currentKey = null;
    private boolean reset;

    /**
     * Construct a keyAction.
     *
     * @param action The action to execute.
     * @param reset Whether to reset the key instantly after a key press.
     */
    public SingleKeyAction(Runnable action, boolean reset)
    {
        super(action);
        this.reset = reset;
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

    @Override
    public boolean resetKey()
    {
        return reset;
    }

}
