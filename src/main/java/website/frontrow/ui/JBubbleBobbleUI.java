package website.frontrow.ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by larsstegman on 02-09-15.
 */
public class JBubbleBobbleUI extends JFrame
{
    /**
     * The number of frames per second at which the game refreshes.
     */
    private static final int FRAME_REFRESH_RATE = 60;

    /**
     * Creates a JBubble Bobble UI.
     */
    public JBubbleBobbleUI()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPanel = getContentPane();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new PlayingFieldPanel(), BorderLayout.CENTER);
        //Add the score counter


        //Add the button on the bottom
        String[] strButtonNames = new String[2];
        strButtonNames[0] = "Restart";
        strButtonNames[1] = "Insert Coin";

        contentPanel.add(new ButtonPanel(strButtonNames), BorderLayout.SOUTH);

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
        // Do logic and graphics stuff.
    }
}
