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
    /**
     * Create north aligned wrapper panel.
     * @param registry Registry to register to.
     * @param playerActionsList PlayerActions to add rebinding for.
     * @param utilActions UtilityActions to add rebinding for.
     */
    public RebindFrame(KeyRegistry registry, List<PlayerActions> playerActionsList, UtilActions utilActions)
    {
        this.setLayout(new BorderLayout());
        this.setSize(300, 500);
        JTabbedPane tabbedPane = new JTabbedPane();
        ListIterator<PlayerActions> playerActionsListIterator = playerActionsList.listIterator();
        while (playerActionsListIterator.hasNext())
        {
            tabbedPane.addTab("Player " + playerActionsListIterator.nextIndex(),
                    createNorthAlignedWrapperPanel(
                            new PlayerRebindPanel(registry, playerActionsListIterator.next()))
            );
        }
        tabbedPane.addTab("Utilities",
                createNorthAlignedWrapperPanel(
                        new UtilRebindPanel(registry, utilActions)));
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createNorthAlignedWrapperPanel(Component component)
    {
        JPanel result = new JPanel(new BorderLayout());
        result.add(component, BorderLayout.NORTH);
        return result;
    }
}
