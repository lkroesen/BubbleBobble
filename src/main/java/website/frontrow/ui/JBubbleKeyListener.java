package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The key listener makes sure that when certain keys are pressed,
 * the corresponding action is performed in the game.
 */
public class JBubbleKeyListener implements KeyListener
{

    /**
     * The mappings of keyCode to action.
     */
    private final Map<Integer, Action> mapping;

    private Map<Integer, Boolean> pressed = new HashMap<>();
    private Set<Integer> pressedKeys = Collections.newSetFromMap(new ConcurrentHashMap<>());

    /**
     * Creates a JBubbleKeyListener.
     * @param mapping The mapping to use for the keylistener.
     */
    public JBubbleKeyListener(Map<Integer, Action> mapping)
    {
        this.mapping = mapping;
    }

    /**
     * Performs the corresponding action when the key is pressed.
     * @param keyEvent The event a key is pressed.
     */
    public void keyPressed(KeyEvent keyEvent)
    {
        if (mapping.get(keyEvent.getKeyCode()) == null)
        {
            return;
        }

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
     */
    public void update()
    {
        pressedKeys.forEach(keyCode -> mapping.get(keyCode).doAction());
        cleanList();
    }
    
    /**
     * Clean jumps and bubbles out of the list so we don't spam them.
     */
    public void cleanList()
    {
        pressedKeys.remove(KeyEvent.VK_SPACE);
        pressedKeys.remove(KeyEvent.VK_Z);
    }

}
