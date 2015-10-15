package website.frontrow.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import website.frontrow.Launcher;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.util.FileNameCollector;

public class ModeMenu
        extends JFrame
        implements Logable
{

    private final int width = 170;
    private final int height = 150;

    public ModeMenu()
    {
        super("Bubble Bobble");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        Dimension size = new Dimension(width, height);
        this.setSize(size);

        JLabel label1 = new JLabel("Choose A Mode:");
        add(label1, BorderLayout.PAGE_START);

        Map<String, Action> buttonMappings = new HashMap<>();
        buttonMappings.put("Single Player Mode", () ->
        {
            try
            {
                new Launcher().start(new FileNameCollector().obtain("level/"), 1);
                setVisible(false);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        buttonMappings.put("Multi Player Mode", () ->
        {
            try
            {
                new Launcher().start(new FileNameCollector().obtain("level/"), 2);
                setVisible(false);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        ButtonPanel panel = new ButtonPanel(buttonMappings);
        add(panel, BorderLayout.CENTER);

        addToLog("[MODEMENU]\t created successfully.");
    }

    /**
     * Log actions taken by ModeMenu.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
