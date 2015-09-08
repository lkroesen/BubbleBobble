package website.frontrow.board;

/**
 * Created by toverklift on 9/3/2015.
 */

public enum Direction
{
	
	/**
	 * Directions:
	 * The left digit governs the x coordinate,
	 * The right digit governs the y coordinate.
	 */
	
	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0);
	
	/**
	 * Relative x coordinate on the grid, with the top left corner as origin.
	 */
	private int deltaX;	
	
	/**
	 * Relative y coordinate on the grid, with the top left corner as origin.
	 */
	private int deltaY;
	
	/**
	 * Set a new Direction.
	 * @param x
	 * Relative x coordinate on the grid, with the top left corner as origin.
	 * @param y
	 * Relative y coordinate on the grid, with the top left corner as origin.
	 */
	Direction(int x, int y)
	{
		this.deltaX = x;
		this.deltaY = y;
	}
	
	/**
	 * Get the x coordinate on the grid, with the top left corner as origin.
	 * @return
	 * Return the value of deltaX.
	 */
	public int getDeltaX()
	{
		return this.deltaX;
	}
	
	/**
	 * Get the y coordinate on the grid, with the top left corner as origin.
	 * @return
	 * Return the value of deltaY.
	 */
	public int getDeltaY()
	{
		return this.deltaY;
	}
}
