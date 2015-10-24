package website.frontrow.util.keymap;

import java.util.LinkedList;

/**
 * Refers to an action as enacted by a keypress.
 */
public abstract class KeyAction
{

    private Runnable action;

    private LinkedList<KeyActionObserver> observers = new LinkedList<>();

    /**
     * Construct a keyAction.
     *
     * @param action The action to execute.
     */
    public KeyAction(Runnable action)
    {
        this.action = action;
    }

    /**
     * Called when a given KeyAction is being unbound from a key.
     * @param key The key to unbind.
     * @param registry The registry in which this happens.
     */
    protected abstract void unbind(Key key, KeyRegistry registry);

    /**
     * Called when a given KeyAction is being bound from a key.
     * @param key The key to unbind.
     * @param registry The related registry.
     */
    protected abstract void bind(Key key, KeyRegistry registry);

    /**
     * Execute a KeyAction.
     */
    public void execute()
    {
        action.run();
    }

    /**
     * Register an observer.
     *
     * @param observer Observer to register
     */
    public void registerObserver(KeyActionObserver observer)
    {
        observers.add(observer);
    }

    /**
     * Unregister an observer.
     *
     * @param observer Observer to unregister
     */
    public void removeObserver(KeyActionObserver observer)
    {
        observers.remove(observer);
    }

    /**
     * Notify all observers of a key change.
     *
     * @param newKey The new key
     */
    public void notifyObservers(Key newKey)
    {
        for (KeyActionObserver observer : observers)
        {
            observer.onKeyChange(newKey);
        }
    }

    /**
     * Check if this action is bound to anything yet.
     *
     * @return Whether this action is bound.
     */
    public abstract boolean isBound();

}