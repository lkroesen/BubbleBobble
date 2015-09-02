package website.frontrow;

import website.frontrow.ui.JBubbleBobbleUI;

/**
 * Instantiates the game so it can be played.
 */
public class Launcher
{

    public static void main(String[] args)
    {
        JBubbleBobbleUI ui = new JBubbleBobbleUI();
        ui.start();
        ui.setTitle("JBUBBLE BOBBLE");
    }
}
