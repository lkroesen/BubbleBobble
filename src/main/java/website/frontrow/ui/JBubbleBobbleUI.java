package website.frontrow.ui;

import website.frontrow.game.Game;
import website.frontrow.game.GameConstants;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.ui.keybinding.RebindFrame;
import website.frontrow.ui.status.SidePanel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * The main ui for the game.
 */
public class JBubbleBobbleUI
        extends JFrame
        implements Logable
{
    private PlayingFieldPanel playingFieldPanel;

    private JBubbleKeyListener keyListener;

    /**
     * Creates a JBubble Bobble UI.
     * @param game The game to display in the ui.
     * @param keyRegistry The key registry.
     * @param rebindFrame The rebinding window.
     */
    public JBubbleBobbleUI(Game game, KeyRegistry keyRegistry, RebindFrame rebindFrame)
    {
        super("Bubble Bobble");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        this.keyListener = new JBubbleKeyListener(keyRegistry);
        addKeyListener(this.keyListener);

        Container contentPanel = getContentPane();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BorderLayout());

        playingFieldPanel = new PlayingFieldPanel(game);
        SidePanel sidePanel = new SidePanel(game, rebindFrame);

        contentPanel.add(playingFieldPanel, BorderLayout.LINE_START);
        contentPanel.add(sidePanel, BorderLayout.LINE_END);

        pack();
        addToLog("[JBBUI]\tBubble Bobble UI created successfully.");
    }

    /**
     * Starts the game UI.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public final void start()
    {
        setVisible(true);

        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(() ->
                drawNextFrame(),
        0, 1000 / GameConstants.FRAME_REFRESH_RATE, TimeUnit.MILLISECONDS);
        addToLog("[JBBUI]\tBubble Bobble UI started successfully.");
    }

    /**
     * Draws the next frame and refreshes the score.
     */
    public void drawNextFrame()
    {
        playingFieldPanel.repaint();
    }

    /**
     * Log actions from JBubbleBobbleUI.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Returns the JBubbleKeyListener.
     * @return The key listener.
     */
    public JBubbleKeyListener getKeyListener()
    {
        return this.keyListener;
    }
}
