package website.frontrow.ui.keybinding;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import website.frontrow.game.UtilActions;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * A panel for rebinding utility keys.
 */
public class UtilRebindPanel extends JPanel
{
    /**
     * Create an UtilRebindPanel.
     * @param registry Registry to register to.
     * @param actions Actions to rebind.
     */
    public UtilRebindPanel(KeyRegistry registry, UtilActions actions)
    {
        this.setLayout(new GridLayout(0, 2));
        add(new JLabel("Dump Log"));
        add(new SingleKeyRebindButton(registry, actions.getDumpLog()));
        add(new JLabel("Toggle Logging"));
        add(new SingleKeyRebindButton(registry, actions.getToggleLog()));
        add(new JLabel("Volume Up"));
        add(new SingleKeyRebindButton(registry, actions.getVolumeUp()));
        add(new JLabel("Volume Down"));
        add(new SingleKeyRebindButton(registry, actions.getVolumeDown()));
    }
}
