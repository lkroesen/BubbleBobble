package website.frontrow.ui;

import website.frontrow.Game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The main ui for the game.
 */
public class JBubbleBobbleUI extends JFrame
{
    /**
     * The number of frames per second at which the game refreshes.
     */
    private static final int FRAME_REFRESH_RATE = 60;

    private PlayingFieldPanel pfp;
    private SidePanel sp;

    private Game game;

    /**
     * Creates a JBubble Bobble UI.
     * @param game The game to display in the ui.
     */
    public JBubbleBobbleUI(Game game)
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPanel = getContentPane();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BorderLayout());

        pfp = new PlayingFieldPanel(game.getLevel());
        sp = new SidePanel(game);

        contentPanel.add(pfp, BorderLayout.LINE_START);
        contentPanel.add(sp, BorderLayout.LINE_END);

        pack();
    }

    /**
     * Starts the game UI.
     */
    public final void start()
    {
        setVisible(true);

        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                drawNextFrame();
            }
        }, 0, FRAME_REFRESH_RATE, TimeUnit.MILLISECONDS);
    }

    /**
     * Draws the next frame and refreshes the score.
     */
    public void drawNextFrame()
    {
        pfp.repaint();
        // Do logic and graphics stuff.
    }
}
