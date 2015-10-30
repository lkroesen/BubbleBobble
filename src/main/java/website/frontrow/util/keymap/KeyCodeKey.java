package website.frontrow.util.keymap;

import java.awt.event.KeyEvent;

/**
 * A simple charCode key implementation.
 */
public class KeyCodeKey extends Key
{

    private Integer keyCode;

    /**
     * Create a KeyCodeKey with a given keyCode.
     *
     * @param keyCode The keycode.
     */
    public KeyCodeKey(Integer keyCode)
    {
        this.keyCode = keyCode;
    }

    /**
     * Get the keycode that represents this key.
     *
     * @return The keycode.
     */
    public Integer getKeyCode()
    {
        return keyCode;
    }

    @Override
    public String toString()
    {
        return KeyEvent.getKeyText(keyCode);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof KeyCodeKey)
        {
            KeyCodeKey that = (KeyCodeKey) other;
            return keyCode.equals(that.keyCode);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return keyCode.hashCode();
    }
}
