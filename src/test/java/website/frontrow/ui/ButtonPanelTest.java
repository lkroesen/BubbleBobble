package website.frontrow.ui;

import org.junit.Test;

import javax.swing.JButton;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test the ButtonPanel.
 */
public class ButtonPanelTest
{
    /**
     * Test to see if the getter works properly.
     */
    @Test
    public void testGetButtonName()
    {
        String[] aStr = {"Test", "ing", "Buttons"};
        ButtonPanel bp = new ButtonPanel(aStr);
        assertArrayEquals(bp.getButtonName(), aStr);
    }

    /**
     * See if an error is thrown when null is inserted.
     */
    @Test(expected = AssertionError.class)
    public void testConstructorNull()
    {
        ButtonPanel bp = new ButtonPanel(null);
    }

    /**
     * Test when new buttons are made that they're not null.
     */
    @Test
    public void testGetButton()
    {
        String[] aStr = {"Test", "ing", "Buttons"};
        ButtonPanel bp = new ButtonPanel(aStr);
        JButton[] aButton = bp.getArButton();

        assertNotNull(aButton);
    }
}