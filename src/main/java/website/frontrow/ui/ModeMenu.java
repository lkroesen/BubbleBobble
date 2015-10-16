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

/**
 * A menu allowing the user to choose between a
 * singleplayer and a multiplayer game.
 */
public class ModeMenu
        extends JFrame
        implements Logable
{
    private static final int WIDTH = 170;
    private static final int HEIGHT = 150;

    /**
     * Create a Mode Menu.
     */
    public ModeMenu()
    {
        super("Bubble Bobble");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setSize(size);

        JLabel label1 = new JLabel("Choose A Mode:");
        add(label1, BorderLayout.PAGE_START);

        ButtonPanel panel = new ButtonPanel(createButtonMappings());
        add(panel, BorderLayout.CENTER);

        addToLog("[MODEMENU]\t created successfully.");
    }

    /**
     * Create button mappings for the gamemodes.
     * @return The button mappings.
     */
    private Map<String, Action> createButtonMappings()
    {
        Map<String, Action> buttonMappings = new HashMap<>();
        buttonMappings.put("Single Player Mode", () ->
        {
            try
            {
                new Launcher().start(FileNameCollector.getInstance().obtain("level/"), 1);
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
                new Launcher().start(FileNameCollector.getInstance().obtain("level/"), 2);
                setVisible(false);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        return buttonMappings;
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
