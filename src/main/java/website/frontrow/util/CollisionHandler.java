package website.frontrow.util;

import java.util.ArrayList;

import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.board.Mover;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;

/**
 * This Class is for handling collisions during the game.
 */
public class CollisionHandler
{
	private static final double PRECISION = 1/0.0001d;
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
     * Create an ArrayList with box values for a mover.
     * @param location
     * Input the location point of a mover
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
     * Check if a mover collides with another mover. Call collisionApplier if it does.
     * The idea is that we check the movers projected location against the boxed
     * locations of all other movers in a loop.
     * If any of the box corners of the moving mover falls within
     * the box of another mover there is a collision.
     * There is also a check to filter whether the mover is colliding with itself.
	 * @param loc
     * The location of the current mover
     * @param mov
     * The move the current mover wants to make
	 * @param mover The mover to check with.
	 */
    public void checkMoverCollision(Point loc, Point mov, Mover mover)
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
                        && other.get(k) != mover)
	    		{

					applyCollision(mover, other.get(k));
	    		}
	    	}
		}   	
    }

	/**
	 * Call the method that handles collisions.
	 * @param collider The mover that initiates the collision.
	 * @param colidee The mover that is being collided with.
	 */
	// TODO: Swap this out for something fancy that uses reflection. But this works fine for now.
	public void applyCollision(Unit collider, Unit colidee)
	{
		if(collider instanceof Player)
		{
			playerCollision((Player) collider, colidee);
		}
		else if (colidee instanceof Player)
		{
			playerCollision((Player) colidee, collider);
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
	 * Called when a player collides with another mover.
	 * @param player Player which is currently colliding.
	 * @param other The mover that was collided with.
	 */
	public void playerCollision(Player player, Unit other)
	{
		// TODO: Make the player lose a life or something else.
	}

	/**
	 * Called when a bubble collides with another mover.
	 * @param bubble Bubble which is currently colliding.
	 * @param other The mover that was collided with.
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
     * Checks if a movers projected movement makes it collide with a cell that is non-empty.
     * @param loc
     * The location of the current mover
     * @param mov
     * The move the current mover wants to make
     * @param mover
     * The mover that is moving
     * @return
     * Whether there has been a collision or not
     */
    public boolean checkCellCollision(Point loc, Point mov, Mover mover)
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
    		if(level.getCells().get(x, y).collides(mover.getMotion()))
    		{ 			
    			return true;
    		}
    	}
    	return false;
    }

	/**
	 * Check an AABB versus the level, to see if there are any collisions.
	 * @param aabb The aabb to check
	 * @param motion The motion to keep in mind
	 * @return True when there is a collision, false when not.
	 */
	public boolean checkLevelAABB(AABB aabb, Point motion)
	{
		// Find the cells we need to check.
		Grid<Cell> cells = level.getCells();
		int minx = (int) Math.max(Math.floor(aabb.getStart().getX()), 0);
		int miny = (int) Math.max(Math.floor(aabb.getStart().getY()), 0);
		int maxx = (int) Math.min(Math.ceil(aabb.getEnd().getX()), cells.getWidth());
		int maxy = (int) Math.min(Math.ceil(aabb.getEnd().getY()), cells.getHeight());
		// Check the cells.
		for(int y = miny; y < maxy; y++)
		{
			for(int x = minx; x < maxx; x++)
			{
				Point c = new Point(x, y);
				if(cells.get(x, y).collides(motion) && new AABB(c, c.add(cells.get(x, y).getAABB())).overlaps(aabb))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Find the next position for the given mover.
	 * @param mover Mover to get next position for.
	 * @return The next position of a given mover.
	 */
	public Collision findNextPosition(Mover mover)
	{
		Point wh = mover.getAABB();
		double stepsX = Math.abs(mover.getMotion().getX()) / wh.getX();
		double stepsY = Math.abs(mover.getMotion().getY()) / wh.getY();
		// Do a certain amount of checks. This should be relatively accurate in any case.
		// If not, make the two a higher number.
		int steps = (int) Math.ceil(Math.max(stepsX, stepsY) * 64);

		Point found = mover.getLocation();

		if(steps == 0) return new Collision(found, false);
		Point delta = mover.getMotion().divide(steps).divide(GameConstants.TICKS_PER_SEC);
		boolean collision = false;

		for(int i = 0; i <= steps; i++)
		{
			Point current = found.add(delta);

			if(checkLevelAABB(new AABB(current, current.add(wh)), mover.getMotion()))
			{
				mover.onWallCollision();
				collision = true;
				break;
			}

			found = current;
		}

		return new Collision(new Point(Math.round(found.getX()*PRECISION)/PRECISION,
						 Math.round(found.getY()*PRECISION)/PRECISION), collision);
	}
}
