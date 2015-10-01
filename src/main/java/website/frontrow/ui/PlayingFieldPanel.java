package website.frontrow.ui;

import website.frontrow.game.Game;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.ImageStore;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * A JPanel in which the playing field is going to be drawn.
 * @see javax.swing.JPanel
 */
public class PlayingFieldPanel
        extends JPanel
        implements Logable
{
    private static final int BLOCK_SIZE = 32; //Pixels

    private static final int BORDER_WIDTH = 20; //Pixels

    private Game game;

    /**
     * Creates a playing field panel in which the game is drawn.
     * The panel is enclosed by a border.
     * @param game The game to display in the playing field.
     */
    public PlayingFieldPanel(Game game)
    {
        super();
        addToLog("[PFP]\tBLOCK_SIZE: " + BLOCK_SIZE + " BORDER_WIDTH: " + BORDER_WIDTH);
        this.setBackground(Color.BLACK);
        this.setBorder(createBorder());

        Dimension size = new Dimension(
                BLOCK_SIZE * game.getLevel().getCells().getWidth() + 2 * BORDER_WIDTH,
                BLOCK_SIZE * game.getLevel().getCells().getHeight() + 2 * BORDER_WIDTH
        );
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.game = game;
        addToLog("[PFP]\tPlaying Field Panel created successfully.");
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        game.getLevel().draw(graphics, BORDER_WIDTH, BORDER_WIDTH,
                getSize().width - 2 * BORDER_WIDTH, getSize().height - 2 * BORDER_WIDTH);
    }

    /**
     * Creates the border for the panel.
     * @return The border.
     */
    private Border createBorder()
    {
        ImageIcon borderImage = new ImageStore().getImageIcon("/sprites/block.jpg");
        return BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH,
                BORDER_WIDTH, BORDER_WIDTH, borderImage);
    }


    /**
     * Log actions in PlayingFieldPanel.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}