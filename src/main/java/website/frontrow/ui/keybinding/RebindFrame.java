package website.frontrow.ui.keybinding;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import website.frontrow.game.PlayerActions;
import website.frontrow.game.UtilActions;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * The UI for rebinding.
 */
public class RebindFrame extends JFrame
{

    private static final int DEFAULT_WIDTH  = 300;
    private static final int DEFAULT_HEIGHT = 400;
    /**
     * Create north aligned wrapper panel.
     * @param registry Registry to register to.
     * @param playerActionsList PlayerActions to add rebinding for.
     * @param utilActions UtilityActions to add rebinding for.
     */
    public RebindFrame(KeyRegistry registry,
                       List<PlayerActions> playerActionsList, UtilActions utilActions)
    {
        this.setLayout(new BorderLayout());
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JTabbedPane tabbedPane = new JTabbedPane();
        ListIterator<PlayerActions> playerActionsListIterator = playerActionsList.listIterator();
        while (playerActionsListIterator.hasNext())
        {
            JPanel playerPanel = createNorthAlignedWrapperPanel(
                    new PlayerRebindPanel(registry, playerActionsListIterator.next()));
            tabbedPane.addTab("Player " + (playerActionsListIterator.nextIndex()),
                    playerPanel);
        }
        JPanel utilityPanel =
                createNorthAlignedWrapperPanel(new UtilRebindPanel(registry, utilActions));
        tabbedPane.addTab("Utilities", utilityPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    // PMD is being weird, as this method is being used in the method above.
    @SuppressWarnings("unused")
    private JPanel createNorthAlignedWrapperPanel(Component component)
    {
        JPanel result = new JPanel(new BorderLayout());
        result.add(component, BorderLayout.NORTH);
        return result;
    }
}
