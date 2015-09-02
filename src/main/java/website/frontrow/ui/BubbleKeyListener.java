package website.frontrow.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Created by Remi on 2-9-2015.
 */
class BubbleKeyListener implements KeyListener {

    private final Map<Integer, Action> mapping;

    BubbleKeyListener(Map<Integer, Action> keyMap) {

        assert keyMap != null;
        this.mapping = keyMap;

    }

    @Override
    public void keyPress(KeyEvent ke) {

        assert ke != null;
        Action action = mapping.get(ke.getKeyCode());
        if (action != null) {
            action.doAction();
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // do nothing at all
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // do nothing at all
    }

}
