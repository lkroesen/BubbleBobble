package website.frontrow.ui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Created by larsstegman on 02-09-15.
 */
public class PlayingFieldPanel extends JPanel {
    private static final int BLOCK_SIZE = 16; //Pixels

    PlayingFieldPanel() {
        super();
        this.setBackground(Color.BLACK);

        // TODO: Will be dynamically created by using the board size.
        Dimension size = new Dimension(BLOCK_SIZE * 36, BLOCK_SIZE * 36);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
    }
}
