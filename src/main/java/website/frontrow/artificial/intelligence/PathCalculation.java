package website.frontrow.artificial.intelligence;

import java.util.ArrayList;

import website.frontrow.board.Mover;
import website.frontrow.board.Enemy;
import website.frontrow.level.Level;
import website.frontrow.util.Point;

/**
 * This class can calculate the best path for an AI to take 
 * given some info. //TODO: fix comment.
 * 
 * This method has no use for a constructor because it always returns a path.
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")

public class PathCalculation 
{
	private static ArrayList<Integer> goal;
	private static Point location;
	
	/**
	 * Method that returns a list of all relative positions of enemies and the player.
	 * On the X axis
	 * @param player the player unit.
	 * @param enemies list of all enemy units.
	 * @param level the current level.
	 * @return a list of all relative positions of enemies and the player, on the X axis.
	 */
	public static ArrayList<Integer> calculateXPath(
			Mover player, ArrayList<Enemy> enemies, Level level)
	{
		location = player.getLocation();
		goal = new ArrayList<Integer>();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			goal.add(0);
		}
			
		for(int i = 0; i < enemies.size(); i++)
		{
			if(moveOnXAxis(location, enemies.get(i).getLocation()) > 0)
			{
				goal.set(i, 1);
			}
			else if(moveOnXAxis(location, enemies.get(i).getLocation()) < 0)
			{
				goal.set(i, -1);
			}	
		}
		return goal;
	}
	
	/**
	 * Method that returns a list of all relative positions of enemies and the player.
	 * On the Y axis.
	 * @param player the player unit.
	 * @param enemies list of all enemy units.
	 * @param level the current level.
	 * @return a list of all relative positions of enemies and the player, on the Y axis.
	 */
	public static ArrayList<Integer> calculateYPath(
			Mover player, ArrayList<Enemy> enemies, Level level)
	{
		location = player.getLocation();
		
		goal = new ArrayList<Integer>();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			goal.add(0);
		}
		
		for(int i = 0; i < enemies.size(); i++)
		{
			if(moveOnYAxis(location, enemies.get(i).getLocation()) > 0)
			{
				goal.set(i, 1);
			}
			else if(moveOnYAxis(location, enemies.get(i).getLocation()) < 0)
			{
				goal.set(i, -1);
			}			
		}
		return goal;
	}
	
	/**
	 * Returns the x position of the enemy relative to the player.
	 * @param player the player unit.
	 * @param enemy an enemy unit.
	 * @return the x position of the enemy relative to the player.
	 */
	public static int moveOnXAxis(Point player, Point enemy)
	{
		double xPlayer = player.getX();
		double xEnemy = enemy.getX();
		
		return (int) Math.floor(xPlayer - xEnemy);
	}
	
	/**
	 * Returns the y position of the enemy relative to the player.
	 * @param player the player unit.
	 * @param enemy an enemy unit.
	 * @return the y position of the enemy relative to the player.
	 */
	//This is an offset to fix unneeded enemy jumping.
	@SuppressWarnings("checkstyle:magicnumber")
	public static int moveOnYAxis(Point player, Point enemy)
	{
		
		double yPlayer = player.getY() + 0.9;
		double yEnemy = enemy.getY();
		
		return (int) Math.floor(yPlayer - yEnemy);
	}
}
