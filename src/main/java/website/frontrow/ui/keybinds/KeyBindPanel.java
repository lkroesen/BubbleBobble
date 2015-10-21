package website.frontrow.ui.keybinds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import website.frontrow.keybindings.KeyBinds;

/**
 * Describes the object to rebind the keys for and the corresponding keys.
 */
public class KeyBindPanel
    extends JPanel
    implements ActionListener,
               KeyListener
{

    private String actionListenedTo;

    private JButton jumpButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton shootButton;

    public KeyBindPanel(int playerIndex)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Player " + (playerIndex + 1) ));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        this.add(new JLabel("Jump"));
        jumpButton = new JButton(KeyEvent.getKeyText(KeyBinds.player1Jump));
        jumpButton.addActionListener(this);
        jumpButton.setActionCommand("BIND_JUMP");
        jumpButton.setFocusable(false);
        this.add(jumpButton);

        this.add(new JLabel("Left"));
        leftButton = new JButton(KeyEvent.getKeyText(KeyBinds.player1GoLeft));
        leftButton.addActionListener(this);
        leftButton.setActionCommand("BIND_LEFT");
        leftButton.setFocusable(false);
        this.add(leftButton);

        this.add(new JLabel("Right"));
        rightButton = new JButton(KeyEvent.getKeyText(KeyBinds.player1GoRight));
        rightButton.addActionListener(this);
        rightButton.setActionCommand("BIND_RIGHT");
        rightButton.setFocusable(false);
        this.add(rightButton);

        this.add(new JLabel("Shoot"));
        shootButton = new JButton(KeyEvent.getKeyText(KeyBinds.player1Shoot));
        shootButton.addActionListener(this);
        shootButton.setActionCommand("BIND_SHOOT");
        shootButton.setFocusable(false);
        this.add(shootButton);

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setVisible(true);
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
        System.out.println("Listening to: " + this.actionListenedTo);
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
        if(e.getKeyCode() != KeyEvent.VK_ESCAPE)
        {
            switch (actionListenedTo)
            {
                case "BIND_JUMP":
                    KeyBinds.player1Jump = e.getKeyCode();
                    break;
                case "BIND_LEFT":
                    KeyBinds.player1GoLeft = e.getKeyCode();
                    break;
                case "BIND_RIGHT":
                    KeyBinds.player1GoRight = e.getKeyCode();
                    break;
                case "BIND_SHOOT":
                    KeyBinds.player1Shoot = e.getKeyCode();
                    break;
                default:
                    break;
            }
            System.out.println("Changed binding for " + actionListenedTo + " to "
                    + KeyEvent.getKeyText(e.getKeyCode()));
        }
        actionListenedTo = "";
        update();
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

    private void update()
    {
        jumpButton.setText(KeyEvent.getKeyText(KeyBinds.player1Jump));
        rightButton.setText(KeyEvent.getKeyText(KeyBinds.player1GoRight));
        leftButton.setText(KeyEvent.getKeyText(KeyBinds.player1GoLeft));
        shootButton.setText(KeyEvent.getKeyText(KeyBinds.player1Shoot));
    }
}
