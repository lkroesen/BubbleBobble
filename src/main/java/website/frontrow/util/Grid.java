package website.frontrow.util;

import java.util.ArrayList;

/**
 * Class that contains a X by X grid of a certain type.
 */
public class Grid<E>
{
    private int width;
    private int height;

    private ArrayList<E> items;


    /**
     * Create an empty width x height grid.
     * @param width     The width of the grid.
     * @param height    The height of the grid.
     */
    public Grid(int width, int height)
    {
        ArrayList<E> list = new ArrayList<E>(width*height);
        for (int i = 0; i < width*height; i++)
        {
            list.add(null);
        }
        this.setup(list, width, height);
    }

    /**
     * Attempt to turn an ArrayList into a width x height grid.
     * @param width     The width of the grid.
     * @param height    The height of the grid.
     */
    public Grid(ArrayList<E> items, int width, int height)
    {
        if(items.size() != width * height) throw new RuntimeException("ArrayList is of invalid items");
        this.setup(new ArrayList<E>(items), width, height);
    }

    /**
     * Set the variables.
     * @param items Set the item list.
     * @param width Set the width.
     * @param height Set the height.
     */
    private void setup(ArrayList<E> items, int width, int height)
    {
        this.items = items;
        this.width = width;
        this.height = height;
    }

    /**
     * Get the width of the grid.
     * @return The width of the grid.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Get the height of the grid.
     * @return The height of the grid.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Get item at position (x, y).
     * @param x Horizontal position
     * @param y Vertical position
     * @return The item at (x, y).
     */
    public E get(int x, int y)
    {
        if (x >= width || x < 0) throw new ArrayIndexOutOfBoundsException("Horizontal position is out of bounds.");
        if (y >= height || y < 0) throw new ArrayIndexOutOfBoundsException("Vertical position is out of bounds.");

        return items.get(x + y * width);
    }

    /**
     * Set item at position (x, y).
     * @param x Horizontal position.
     * @param y Vertical position.
     * @param item The item to place at (x, y).
     */
    public void set(int x, int y, E item)
    {
        if (x >= width || x < 0) throw new ArrayIndexOutOfBoundsException("Horizontal position is out of bounds.");
        if (y >= height || y < 0) throw new ArrayIndexOutOfBoundsException("Vertical position is out of bounds.");

        items.set(x + y * width, item);
    }

}
