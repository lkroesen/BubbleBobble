package website.frontrow.util;

import java.util.ArrayList;

import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Level;

/**
 * Created by lkroesen on 02/09/2015.
 */
public class CollisionHandler
{

    private Level level;

    
    /**
     * Input a grid to read from.
     * @param level The level to handle the collisions for.
     * Input the grid to use.
     */
    public CollisionHandler(Level level)
    {
        this.level = level;
    }
    
    /**
     * Create an ArrayList with box values for a unit
     * @param location
     * Input the location point of a Unit
     * @return
     * Return an ArrayList of all box values.
     */
    public ArrayList<Point> buildBox(Point location)
    {   	
    	ArrayList<Point> aabb = new ArrayList<>();
        aabb.add(new Point(location.getX(), location.getY()));
        aabb.add(new Point(location.getX(), location.getY() + 0.99));
        aabb.add(new Point(location.getX() + 0.99, location.getY()));
        aabb.add(new Point(location.getX() + 0.99, location.getY() + 0.99));
        return aabb;
    }

    /**
     * Check if a unit collides with another unit. Call collisionApplier if it does.
     * The idea is that we check the units projected location against the boxed locations of all other units in a loop.
     * If any of the box corners of the moving unit falls within the box of another unit there is a collision.
     * There is also a check to filter whether the unit is colliding with itself.
     * @param loc
     * The location of the current unit
     * @param mov
     * The move the current unit wants to make
     * @param unit
     * The unit that is moving
     */
    public void checkUnitCollision(Point loc, Point mov, Unit unit)
    {
    	loc.add(mov);
    	ArrayList<Point> aabbPlayer = buildBox(loc);
    	ArrayList<Unit> other = this.level.getUnits();
    	
    	for(int k = 0; k < other.size(); k ++){   		  		
	    	for(int i = 0; i < aabbPlayer.size(); i++)
	    	{
	    		double currentX = aabbPlayer.get(i).getX();
	    		double currentY = aabbPlayer.get(i).getY();
	    		double otherX = other.get(k).getLocation().getX();
	    		double otherY = other.get(k).getLocation().getY();
	    		if( currentX >= otherX && currentY >= otherY && currentX < (otherX + 1) && currentY < (otherY + 1) && other.get(k) != unit)
	    		{ 
	    			collisionApplier(other.get(k), unit);
	    		}
	    	}
		}   	
    } 
    
    /**
     * Checks if a units projected movement makes it collide with a cell that is non-empty.
     * @param loc
     * The location of the current unit
     * @param mov
     * The move the current unit wants to make
     * @param unit
     * The unit that is moving
     * @return
     * Whether there has been a collision or not
     */
    public boolean checkCellCollision(Point loc, Point mov, Unit unit)
    {
    	ArrayList<Point> aabb = buildBox(loc);
    
    	for(int i = 0; i < aabb.size(); i++)
    	{
    		aabb.set(i, aabb.get(i).add(mov));
    		int x = (int) aabb.get(i).getX();
    		int y = (int) aabb.get(i).getY();
    		if(level.getCells() == null || level.getCells().get(x,y) == null){
    			return true;
    		}			
    		if(level.getCells().get(x,y).collides(unit))
    		{ 			
    			return true;
    		}
    	}
    	return false;
    }



    /**
     * This method applies the effect that is caused by the collision of 2 Units.
     * <ul>
     *     <li>Player moves onto an Enemy : Lose 1 life</li>
     *     <li>Enemy moves onto a Player : Lose 1 life</li>
     *     <li>Player hits a bubble : Player Jumps</li>
     *     <li>Bubble hits a wall : Bubble loses 1 "life"</li>
     *     <li>Bubble contains an enemy and gets hit by the player from the top
     *          : Enemy lose 1 life</li>
     * </ul>
     * @param uCurrent
     * Input the Unit that initiated the move.
     * @param uOther
     * Input the Unit that gets moved onto.
     * TODO: Overhaul this method to avoid instanceof.
     */
    private void collisionApplier(Unit uCurrent, Unit uOther)
    {
        if (uCurrent instanceof Player)
        {
            if (uOther instanceof Enemy)
            {
                // Meep
            }
            else if (uOther instanceof Bubble)
            {
                // TODO Perform Jump Action
                if (((Bubble) uOther).getContains() instanceof Enemy)
                {

                }
            }
            // TODO Player hits wall? Wall as Unit?
        }
        else if (uCurrent instanceof Enemy)
        {
            if (uOther instanceof Player)
            {
                // Watch out with this, because if the enemy and player moved to the same spot
                // at the same time, that would cause an instakill
                // TODO: Add Invincibility frames.
                // BOOP
            }
        }
        else if (uCurrent instanceof Bubble)
        {
            if (uOther instanceof Player)
            {

            }
        }
    }
}
