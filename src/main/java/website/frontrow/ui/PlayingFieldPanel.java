package website.frontrow.ui;

import website.frontrow.sprite.ImageStore;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Created by larsstegman on 02-09-15.
 *
 */
public class PlayingFieldPanel extends JPanel
{

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private static final int BLOCK_SIZE = 16; //Pixels

    private static final int BORDER_WIDTH = 20; //Pixels

    /**
     * Creates a playing field panel in which the game is drawn.
     * The panel is enclosed by a border.
     */
    public PlayingFieldPanel()
    {
        super();
        this.setBackground(Color.BLACK);
        this.setBorder(
                BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH,
                        BORDER_WIDTH, BORDER_WIDTH, new ImageStore().getBorderImage())
        );

        // TODO: Will be dynamically created by using the board size.
        Dimension size = new Dimension(BLOCK_SIZE * 36, BLOCK_SIZE * 36);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
    }

    @Override
    public void paint(Graphics graphics)
    {
        //For each square in the playing field draw the sprite
        //For each occupant of the square draw the sprite.
        super.paint(graphics);
        draw(graphics, BORDER_WIDTH, BORDER_WIDTH,
                getSize().width - 2 * BORDER_WIDTH, getSize().height - 2 * BORDER_WIDTH);
    }

    /**
     * Draw the square and the occupants.
     * @param graphics The graphics context.
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height the height
     */
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {
        // TODO: Replace this with drawing the playing field.
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(x, y,
                width, height);
    }
}