package website.frontrow.util.keymap;

/**
 * Represents a given key that can be pressed.
 */
public abstract class Key
{
    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

}
