package website.frontrow.util.keymap;

/**
 * Allows for listening for keychanges.
 */
public interface KeyActionObserver
{
    /**
     * Gets called when the key changes.
     * @param newKey The new key.
     */
    void onKeyChange(Key newKey);

}
