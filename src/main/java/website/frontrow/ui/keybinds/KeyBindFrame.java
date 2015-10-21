package website.frontrow.ui.keybinds;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This window allows the user to rebind keys.
 */
public class KeyBindFrame
    extends JFrame
{
    JPanel panel;

    public KeyBindFrame()
    {
        super();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) dimension.getWidth()/2 - getSize().width,
                (int) dimension.getHeight()/2 - getSize().height);

        panel = new JPanel(new GridLayout(3,1));
        panel.add(new KeyBindPanel(0));
        add(panel);
        pack();
        setVisible(true);
    }
}
