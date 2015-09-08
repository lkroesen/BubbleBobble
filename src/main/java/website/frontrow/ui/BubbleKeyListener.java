package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * The key listener makes sure that when certain keys are pressed,
 *      the corresponding action is performed in the game.
 * Created by Remi Flinterman on 2-9-2015.
 */
class BubbleKeyListener implements KeyListener
{

    /**
     * The mappings of keyCode to action.
     */
    private final Map<Integer, Action> mapping;

    /**
     * Creates new key listener based on the inputs resulting in actions.
     * @param keyMap The mappings of keyCode to action.
     */
    BubbleKeyListener(Map<Integer, Action> keyMap)
    {

        assert keyMap != null;
        this.mapping = keyMap;

    }

    /**
     * Checks the event that a key is pressed.
     * Causes the program to perform the corresponding action.
     * @param ke
     */
    public void keyPressed(KeyEvent ke)
    {

        assert ke != null;
        Action action = mapping.get(ke.getKeyCode());
        if (action != null)
        {
            action.doAction();
        }

    }

    /**
     * keyTyped won't interrupt the game in any way.
     * @param ke
     */
    public void keyTyped(KeyEvent ke)
    {
        // do nothing at all
    }

    /**
     * keyReleased doesn't interrupt the game in any way.
     * @param ke
     */
    public void keyReleased(KeyEvent ke)
    {
        // do nothing at all
    }

}
