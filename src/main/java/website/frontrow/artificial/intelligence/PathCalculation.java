package website.frontrow.artificial.intelligence;

import website.frontrow.board.Mover;
import website.frontrow.board.Enemy;
import website.frontrow.util.Point;

/**
 * This class can calculate the best path for an AI to take 
 * given the player information, a list of enemies and the current level.
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
/* This method has no use for a constructor because it always returns a path. */
public abstract class PathCalculation 
{
	private static final double Y_OFFSET = 0.9;

	private static Point location;

	/**
	 * Method that returns a list of all relative positions of enemies and the player.
	 * On the X axis
	 * @param player The player unit.
	 * @param enemy The enemy unit.
	 * @return A list of all relative positions of enemies and the player, on the X axis.
	 */
	public static Integer calculateXPath(Mover player, Enemy enemy)
	{
		location = player.getLocation();	
		int goal = 0;
		
			if(moveOnXAxis(location, enemy.getLocation()) > 0)
			{
				goal = 1;
			}
			else if(moveOnXAxis(location, enemy.getLocation()) < 0)
			{
				goal = -1;
			}			

		return goal;
	}
	
	/**
	 * Method that returns a list of all relative positions of enemies and the player.
	 * On the Y axis.
	 * @param player the player unit.
	 * @param enemy the enemy unit.
	 * @return a list of all relative positions of enemies and the player, on the Y axis.
	 */
	public static Integer calculateYPath(Mover player, Enemy enemy)
	{
		location = player.getLocation();	
		int goal = 0;
		
			if(moveOnYAxis(location, enemy.getLocation()) > 0)
			{
				goal = 1;
			}
			else if(moveOnYAxis(location, enemy.getLocation()) < 0)
			{
				goal = -1;
			}			

		return goal;
	}
	
	/**
	 * Returns the x position of the enemy relative to the player.
	 * @param player The player unit.
	 * @param enemy An enemy unit.
	 * @return The x position of the enemy relative to the player.
	 */
	public static int moveOnXAxis(Point player, Point enemy)
	{
		double xPlayer = player.getX();
		double xEnemy = enemy.getX();
		
		return (int) Math.floor(xPlayer - xEnemy);
	}
	
	/**
	 * Returns the y position of the enemy relative to the player.
	 * @param player The player unit.
	 * @param enemy An enemy unit.
	 * @return The y position of the enemy relative to the player.
	 */
	public static int moveOnYAxis(Point player, Point enemy)
	{
		double yPlayer = player.getY() + Y_OFFSET;
		double yEnemy = enemy.getY();
		
		return (int) Math.floor(yPlayer - yEnemy);
	}
}