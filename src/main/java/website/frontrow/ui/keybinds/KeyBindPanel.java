package website.frontrow.ui.keybinds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import website.frontrow.keybindings.ActionType;
import website.frontrow.keybindings.KeyBinds;
import website.frontrow.keybindings.KeyBindsObserver;
import website.frontrow.logger.Log;

/**
 * Describes the object to rebind the keys for and the corresponding keys.
 */
public class KeyBindPanel
    extends JPanel
    implements ActionListener,
               KeyListener,
               KeyBindsObserver
{

    private String actionListenedTo;

    private JButton jumpButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton shootButton;

    private int playerIndex;

    /**
     * Creates a panel which displays buttons to rebind keys for a player.
     * @param playerIndex The index for this player. Zero-indexed.
     */
    public KeyBindPanel(int playerIndex)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Player " + (playerIndex + 1)));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        this.add(new JLabel("Jump"));
        jumpButton = createButton(ActionType.JUMP, playerIndex, "BIND_JUMP");
        this.add(jumpButton);

        this.add(new JLabel("Left"));
        leftButton = createButton(ActionType.LEFT, playerIndex, "BIND_LEFT");
        this.add(leftButton);

        this.add(new JLabel("Right"));
        rightButton = createButton(ActionType.RIGHT, playerIndex, "BIND_RIGHT");
        this.add(rightButton);

        this.add(new JLabel("Shoot"));
        shootButton = createButton(ActionType.SHOOT, playerIndex, "BIND_SHOOT");
        this.add(shootButton);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setVisible(true);
        this.playerIndex = playerIndex;
        KeyBinds.addObserver(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.actionListenedTo = e.getActionCommand();
        Log.add("[KBP]\tListening to: " + this.actionListenedTo);
        this.grabFocus();
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() != KeyEvent.VK_ESCAPE && !actionListenedTo.equals(""))
        {
            System.out.println("I'm here!");
            switch (actionListenedTo)
            {
                case "BIND_JUMP":
                    KeyBinds.setKeyCodeFor(ActionType.JUMP, playerIndex, e.getKeyCode());
                    break;
                case "BIND_LEFT":
                    KeyBinds.setKeyCodeFor(ActionType.LEFT, playerIndex, e.getKeyCode());
                    break;
                case "BIND_RIGHT":
                    KeyBinds.setKeyCodeFor(ActionType.RIGHT, playerIndex, e.getKeyCode());
                    break;
                case "BIND_SHOOT":
                    KeyBinds.setKeyCodeFor(ActionType.SHOOT, playerIndex, e.getKeyCode());
                    break;
                default:
                    break;
            }
            Log.add("[KBP]\tChanged binding for " + actionListenedTo + ", player " + playerIndex
                    + " to " + KeyEvent.getKeyText(e.getKeyCode()));
        }
        actionListenedTo = "";
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    /**
     * Updates the buttons.
     */
    private void update()
    {
        jumpButton.setText(
                KeyEvent.getKeyText(KeyBinds.getKeyCodeFor(ActionType.JUMP, playerIndex)));
        rightButton.setText(
                KeyEvent.getKeyText(KeyBinds.getKeyCodeFor(ActionType.RIGHT, playerIndex)));
        leftButton.setText(
                KeyEvent.getKeyText(KeyBinds.getKeyCodeFor(ActionType.LEFT, playerIndex)));
        shootButton.setText(
                KeyEvent.getKeyText(KeyBinds.getKeyCodeFor(ActionType.SHOOT, playerIndex)));
    }

    /**
     * Is called when a key binding has changed.
     */
    @Override
    public void keyBindingChanged()
    {
        update();
    }

    private JButton createButton(ActionType action, int playerIndex, String command)
    {
        JButton button = new JButton(
                KeyEvent.getKeyText(KeyBinds.getKeyCodeFor(action, playerIndex)));
        button.addActionListener(this);
        button.setActionCommand(command);
        button.setFocusable(false);

        return button;
    }
}
