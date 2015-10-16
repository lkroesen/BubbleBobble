package website.frontrow.ui;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Map;

/**
 * The button panel which contains all the buttons in the side panel.
 */
public class ButtonPanel
        extends JPanel
        implements Logable
{
    /**
     * Constructor of ButtonPanel, with string input.
     * @param buttonMapping a map with the button names and actions.
     */
    public ButtonPanel(Map<String, Action> buttonMapping)
    {
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));

        for(Map.Entry<String, Action> entry : buttonMapping.entrySet())
        {
            JButton button = new JButton();
            button.setText(entry.getKey());
            button.setFocusable(false);
            button.addActionListener(e -> entry.getValue().doAction());
            add(button);
        }
        addToLog("[BP]\tButton Panel Created.");
    }

    /**
     * Log actions in ButtonPanel.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
