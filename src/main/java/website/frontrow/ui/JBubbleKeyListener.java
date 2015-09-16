package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The key listener makes sure that when certain keys are pressed,
 *      the corresponding action is performed in the game.
 * Created by Remi Flinterman on 2-9-2015.
 */
public class JBubbleKeyListener implements KeyListener
{

    /**
     * The mappings of keyCode to action.
     */
    private final Map<Integer, Action> mapping;

    private Set<Integer> pressedKeys = new HashSet<>();

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
     * @param ke The event a key is pressed.
     */
    public void keyPressed(KeyEvent ke)
    {
        if (mapping.get(ke.getKeyCode()) != null)
        {
            pressedKeys.add(ke.getKeyCode());
        }
    }

    /**
     * keyTyped won't interrupt the game in any way.
     * @param ke The event a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent ke)
    {

    }

    /**
     * keyReleased doesn't interrupt the game in any way.
     * @param ke The event a key is released.
     */
    @Override
    public void keyReleased(KeyEvent ke)
    {
        pressedKeys.remove(ke.getKeyCode());
    }

    /**
     * Perform the actions of all the keys which are being pressed.
     */
    public void update()
    {
        pressedKeys.forEach(keyCode -> mapping.get(keyCode).doAction());
    }

}
