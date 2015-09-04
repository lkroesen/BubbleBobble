package website.frontrow.util;

/**
 * A class that converts grid coordinates to screen coordinates.
 */
public class SpatialConverter {

    private int grid_width;
    private int grid_height;
    private double screen_height;
    private double screen_width;

    /**
     * Converts numbers to screen space.
     * @param screen_width Drawing panel width.
     * @param screen_height Drawing panel height.
     */
    public SpatialConverter(int grid_width, int grid_height, double screen_width, double screen_height)
    {
        this.grid_height = grid_height;
        this.grid_width = grid_width;
        this.screen_height = screen_height;
        this.screen_width = screen_width;
    }

    /**
     * Converts a point on the grid to a point on the screen.
     * @param point Point to convert.
     * @return Converted point.
     */
    public Point convertGridToScreen(Point point)
    {
        // Linearily interpolate the point from
        double x = (point.x / (double) grid_width) * screen_width;
        double y = (point.y / (double) grid_height) * screen_height;

        return new Point(x, y);
    }
}
