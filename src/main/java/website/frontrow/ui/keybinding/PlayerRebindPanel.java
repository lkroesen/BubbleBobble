package website.frontrow.ui.keybinding;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import website.frontrow.game.PlayerActions;
import website.frontrow.util.keymap.KeyRegistry;

/**
 * Panel for rebinding player keys.
 */
public class PlayerRebindPanel extends JPanel
{
    /**
     * Create a rebind panel for a player.
     * @param registry Registry to register key changes to.
     * @param playerActions The actions to register.
     */
    public PlayerRebindPanel(KeyRegistry registry, PlayerActions playerActions)
    {
        this.setLayout(new GridLayout(0, 2));
        add(new JLabel("Left"));
        add(new SingleKeyRebindButton(registry, playerActions.getLeft()));
        add(new JLabel("Right"));
        add(new SingleKeyRebindButton(registry, playerActions.getRight()));
        add(new JLabel("Jump"));
        add(new SingleKeyRebindButton(registry, playerActions.getJump()));
        add(new JLabel("Shoot"));
        add(new SingleKeyRebindButton(registry, playerActions.getShoot()));
    }

}
