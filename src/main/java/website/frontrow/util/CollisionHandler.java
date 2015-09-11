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
    private static final double LOC_OFFSET = 0.99d;
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
     * Create an ArrayList with box values for a unit.
     * @param location
     * Input the location point of a Unit
     * @return
     * Return an ArrayList of all box values.
     */
    public ArrayList<Point> buildBox(Point location)
    {   	
    	ArrayList<Point> aabb = new ArrayList<>();
        aabb.add(new Point(location.getX(), location.getY()));
        aabb.add(new Point(location.getX(), location.getY() + LOC_OFFSET));
        aabb.add(new Point(location.getX() + LOC_OFFSET, location.getY()));
        aabb.add(new Point(location.getX() + LOC_OFFSET, location.getY() + LOC_OFFSET));
        return aabb;
    }

    /**
     * Check if a unit collides with another unit. Call collisionApplier if it does.
     * The idea is that we check the units projected location against the boxed
     * locations of all other units in a loop.
     * If any of the box corners of the moving unit falls within
     * the box of another unit there is a collision.
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
    	
    	for(int k = 0; k < other.size(); k++)
        {
	    	for(int i = 0; i < aabbPlayer.size(); i++)
	    	{
	    		double currentX = aabbPlayer.get(i).getX();
	    		double currentY = aabbPlayer.get(i).getY();
	    		double otherX = other.get(k).getLocation().getX();
	    		double otherY = other.get(k).getLocation().getY();
	    		if      (currentX >= otherX
                        && currentY >= otherY
                        && currentX < (otherX + 1) && currentY < (otherY + 1)
                        && other.get(k) != unit)
	    		{

					applyCollision(unit, other.get(k));
	    		}
	    	}
		}   	
    }

	/**
	 * Call the method that handles collisions.
	 */
	// TODO: Swap this out for something fancy that uses reflection. But this works fine for now.
	public void applyCollision(Unit collider, Unit colidee)
	{
		if(collider instanceof Player)
		{
			playerCollision((Player)collider, colidee);
		}
		else if (colidee instanceof Player)
		{
			playerCollision((Player)colidee, collider);
		}

		if(collider instanceof Bubble)
		{
			bubbleCollision((Bubble) collider, colidee);
		}
		else if(colidee instanceof Bubble)
		{
			bubbleCollision((Bubble) colidee, collider);
		}
	}

	/**
	 * Called when a player collides with another unit.
	 * @param other The unit that was collided with.
	 */
	public void playerCollision(Player player, Unit other)
	{
		// TODO: Make the player lose a life or something else.
	}

	/**
	 * Called when a bubble collides with another unit.
	 * @param other The unit that was collided with.
	 */
	public void bubbleCollision(Bubble bubble, Unit other)
	{
		if(other instanceof Enemy)
		{
			bubble.capture((Enemy) other);
			// Kill the bubble for now.
			bubble.kill();
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
    		if(level.getCells() == null || level.getCells().get(x, y) == null)
            {
    			return true;
    		}			
    		if(level.getCells().get(x, y).collides(unit))
    		{ 			
    			return true;
    		}
    	}
    	return false;
    }
}
