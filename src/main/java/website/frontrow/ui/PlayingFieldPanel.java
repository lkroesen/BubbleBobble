package website.frontrow.ui;

import javax.swing.JPanel;

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

    PlayingFieldPanel()
    {
        super();
        this.setBackground(Color.BLACK);

        // TODO: Will be dynamically created by using the board size.
        Dimension size = new Dimension(BLOCK_SIZE * 36, BLOCK_SIZE * 36);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
    }

    @Override
    public void paint(Graphics graphics)
    {
        draw(graphics, getSize()); // Add the board to draw.

    }

    /**
     * Draw the board.
     * @param graphics the graphics context.
     * @param size The size of the window.
     */
    public void draw(Graphics graphics, Dimension size)
    {
        //For each square in the playing field draw the sprite
        //For each occupant of the square draw the sprite.
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, size.width, size.height);
    }

    /**
     * Draw the square and the occcupants.
     * @param graphics The graphics context.
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height the height
     */
    public void draw(Graphics graphics, int x, int y, int width, int height)
    {

    }
}