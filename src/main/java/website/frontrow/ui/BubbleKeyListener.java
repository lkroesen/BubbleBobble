package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Created by Remi Flinterman on 2-9-2015.
 */
class BubbleKeyListener implements KeyListener
{

    private final Map<Integer, Action> mapping;

    BubbleKeyListener(Map<Integer, Action> keyMap)
    {

        assert keyMap != null;
        this.mapping = keyMap;

    }
    
    public void keyPressed(KeyEvent ke)
    {

        assert ke != null;
        Action action = mapping.get(ke.getKeyCode());
        if (action != null)
        {
            action.doAction();
        }

    }

    public void keyTyped(KeyEvent ke)
    {
        // do nothing at all
    }

    public void keyReleased(KeyEvent ke)
    {
        // do nothing at all
    }

}
