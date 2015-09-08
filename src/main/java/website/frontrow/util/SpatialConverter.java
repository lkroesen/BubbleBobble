package website.frontrow.util;

/**
 * A class that converts grid coordinates to screen coordinates.
 */
public class SpatialConverter
{

    private int gridWidth;
    private int gridHeight;
    private double screenHeight;
    private double screenWidth;

    /**
     * Converts numbers to screen space.
     * @param screenWidth Drawing panel width.
     * @param screenHeight Drawing panel height.
     * @param gridHeight Height of the grid.
     * @param gridWidth Width of the grid.
     */
    public SpatialConverter(int gridWidth, int gridHeight,
                            double screenWidth, double screenHeight)
    {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    /**
     * Converts a point on the grid to a point on the screen.
     * @param point Point to convert.
     * @return Converted point.
     */
    public Point convertGridToScreen(Point point)
    {
        // Linearily interpolate the point from
        double x = (point.getX() / (double) gridWidth) * screenWidth;
        double y = (point.getY() / (double) gridHeight) * screenHeight;

        return new Point(x, y);
    }
}
