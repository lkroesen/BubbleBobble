package website.frontrow.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Map;

/**
 * The button panel which contains all the buttons in the side panel.
 */
public class ButtonPanel extends JPanel
{
    /**
     * Constructor of ButtonPanel, with string input.
     * @param buttonMapping a map with the button names and actions.
     * Input the names for the buttons
     */
    public ButtonPanel(Map<String, Action> buttonMapping)
    {
        for(Map.Entry<String, Action> entry : buttonMapping.entrySet())
        {
            JButton button = new JButton();
            button.setText(entry.getKey());
            button.setFocusable(false);
            button.addActionListener(e -> entry.getValue().doAction());
            add(button);
        }
    }
}
