package website.frontrow.ui.keybinding;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JToggleButton;
import website.frontrow.util.keymap.Key;
import website.frontrow.util.keymap.KeyActionObserver;
import website.frontrow.util.keymap.KeyCodeKey;
import website.frontrow.util.keymap.KeyRegistry;
import website.frontrow.util.keymap.SingleKeyAction;

/**
 * A button that rebinds keys.
 */
public class SingleKeyRebindButton extends JToggleButton implements KeyListener, KeyActionObserver
{
    private KeyRegistry registry;
    private SingleKeyAction action;

    /**
     * Create a button that rebinds a single key.
     * @param registry Registry to register to.
     * @param action Action to rebind.
     */
    public SingleKeyRebindButton(KeyRegistry registry, SingleKeyAction action)
    {
        this.registry = registry;
        this.action = action;
        updateText();
        action.registerObserver(this);
        this.addKeyListener(this);
    }

    private void rebind(Key key)
    {
        if (!(key instanceof KeyCodeKey
              &&
             ((KeyCodeKey) key).getKeyCode().equals(KeyEvent.VK_ESCAPE)))
        {
            registry.register(key, action);
        }
        updateText();
    }

    private void updateText()
    {
        this.setText("UNBOUND");
        if(isSelected())
        {
            this.setText("_");
        }
        else if(action.isBound())
        {
            this.setText(action.getCurrentKey().toString());
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(isSelected())
        {
            setSelected(false);
            this.rebind(new KeyCodeKey(e.getKeyCode()));
        }
    }

    @Override
    public void onKeyChange(Key newKey)
    {
        updateText();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

}
