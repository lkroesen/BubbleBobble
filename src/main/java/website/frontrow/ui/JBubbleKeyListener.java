package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import website.frontrow.util.keymap.KeyCodeKey;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * The key listener makes sure that when certain keys are pressed,
 * the corresponding action is performed in the game.
 */
public class JBubbleKeyListener implements KeyListener
{

    /**
     * The mappings of keyCode to action.
     */
    private final KeyRegistry keyRegistry;

    private Map<Integer, Boolean> pressed = new HashMap<>();
    private Set<Integer> pressedKeys = Collections.newSetFromMap(new ConcurrentHashMap<>());

    /**
     * Creates a JBubbleKeyListener.
     * @param keyRegistry The mapping to use for the keylistener.
     */
    public JBubbleKeyListener(KeyRegistry keyRegistry)
    {
        this.keyRegistry = keyRegistry;
    }

    /**
     * Performs the corresponding action when the key is pressed.
     * @param keyEvent The event a key is pressed.
     */
    public void keyPressed(KeyEvent keyEvent)
    {
        if(pressed.get(keyEvent.getKeyCode()) == null || !pressed.get(keyEvent.getKeyCode()))
        {
            pressed.put(keyEvent.getKeyCode(), true);
            pressedKeys.add(keyEvent.getKeyCode());
        }
    }

    /**
     * keyTyped won't interrupt the game in any way.
     * @param keyEvent The event a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent keyEvent)
    {
    }

    /**
     * keyReleased doesn't interrupt the game in any way.
     * @param keyEvent The event a key is released.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent)
    {
    	pressed.put(keyEvent.getKeyCode(), false);
    	pressedKeys.remove(keyEvent.getKeyCode());
    }

    /**
     * Perform the actions of all the keys which are being pressed.
     * And remove them in certain cases.
     */
    public void update()
    {
        pressedKeys.forEach(keyCode -> {
            if(keyRegistry.handle(new KeyCodeKey(keyCode)))
            {
                pressedKeys.remove(keyCode);
            }
        });
    }

}
