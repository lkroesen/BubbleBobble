package website.frontrow.ui;

import javax.swing.*;

/**
 * Created by Saki on 9/2/2015.
 */
public class ButtonPanel extends JPanel
{
    private String[] buttonName;
    private JButton[] button;

    /**
     * Constructor of ButtonPanel, with string input
     * @param in
     * Input the names for the buttons
     */
    public ButtonPanel(String[] in)
    {
        setButtonName(in);
        button = new JButton[buttonName.length];

        for (int c = 0; c < in.length; c++)
        {
            button[c] = new JButton(in[c]);
            // TODO: Action Listener for Buttons
            //button[c].addActionListener(new ActionListener());
            add(button[c]);
        }
    }

    /**
     * Returns the Strings of the button in an array
     * @return
     * Returns a String array
     */
    public String[] getButtonName()
    {
        return buttonName;
    }

    /**
     * Input an array of strings with the names for the buttons
     * @param in
     * Input a string array
     */
    public void setButtonName(String[] in)
    {
        buttonName = new String[in.length];
        for (int c = 0; c < in.length; c++)
        {
            buttonName[c] = in[c];
        }
    }
}
