package website.frontrow;

import website.frontrow.ui.JBubbleBobbleUI;

/**
 * Instantiates the game so it can be played.
 */
public class Launcher
{
    public Launcher() {}

    /**
     * The starting point of the program.
     * @param args The arguments of the program. Currently no parameters are used.
     */
    public static void main(String[] args)
    {
        JBubbleBobbleUI ui = new JBubbleBobbleUI();
        ui.setTitle("JBUBBLE BOBBLE");
        ui.start();

    }
}
