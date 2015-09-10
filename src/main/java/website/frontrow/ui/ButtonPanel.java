package website.frontrow.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Saki on 9/2/2015.
 */
public class ButtonPanel extends JPanel
{
    private String[] buttonName;
    private JButton[] arButton;

    /**
     * Constructor of ButtonPanel, with string input.
     * @param in
     * Input the names for the buttons
     */
    public ButtonPanel(String[] in)
    {
        assert in != null;

        setButtonName(in);
        arButton = new JButton[buttonName.length];

        for (int c = 0; c < in.length; c++)
        {
            arButton[c] = new JButton(in[c]);
            // TODO: Action Listener for Buttons
            //arButton[c].addActionListener(new ActionListener());
            add(arButton[c]);
        }
    }

    /**
     * Returns the Strings of the arButton in an array.
     * @return
     * Returns a String array
     */
    public String[] getButtonName()
    {
        return buttonName;
    }

    /**
     * Input an array of strings with the names for the buttons.
     * This method is only intended to be used by the constructor.
     * @param in
     * Input a string array
     */
    private void setButtonName(String[] in)
    {
        buttonName = new String[in.length];
        for (int c = 0; c < in.length; c++)
        {
            buttonName[c] = in[c];
        }
    }

    /**
     * Get all the JButtons.
     * @return
     * Returns an array of JButton
     */
    public JButton[] getArButton()
    {
        return arButton;
    }
}
