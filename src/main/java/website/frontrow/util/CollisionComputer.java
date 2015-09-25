package website.frontrow.util;

import java.util.ArrayList;

import website.frontrow.board.Unit;
import website.frontrow.board.Mover;
import website.frontrow.game.GameConstants;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

/**
 * Computes collisions during the game.
 */
public class CollisionComputer
	implements Logable
{
	@SuppressWarnings("checkstyle:magicnumber")
	private static final double PRECISION = 0.0001d;
	private static final double ONE_DEV_PRECISION = 1 / PRECISION;
	private static final int SAMPLING = 64;
    private Level level;

    
    /**
     * Input a grid to read from.
     * @param level The level to handle the collisions for.
     * Input the grid to use.
     */
    public CollisionComputer(Level level)
    {
        this.level = level;
    }

    /**
     * Check if a mover collides with another mover. Call collisionApplier if it does.
     * The idea is that we check the movers projected location against the boxed
     * locations of all other movers in a loop.
     * If any of the box corners of the moving mover falls within
     * the box of another mover there is a collision.
     * There is also a check to filter whether the mover is colliding with itself.
	 * @param user The unit to check for.
	 * @param handler The handler to call handle collisions in.
	 */
    public void checkUnitsAABB(Unit user, CollisionHandler handler)
	{
		ArrayList<Unit> units = this.level.getUnits();
		AABB me = user.getAABB();
		for (Unit other : units) {
			if (other != user && other.getAABB().overlaps(me)) {
				handler.applyCollision(user, other);
			}
		}
	}

	/**
	 * Log actions from CollisionComputer.
	 * @param action Input a String that is the action performed.
	 */
	@Override
	public void addToLog(String action)
	{
		Log.add(action);
	}

	/**
	 * Check an AABB versus the level, to see if there are any collisions.
	 * @param aabb The aabb to check
	 * @param motion The motion to keep in mind
	 * @return True when there is a collision, false when not.
	 */
	public Cell checkLevelAABB(AABB aabb, Point motion)
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
				Cell cell = cells.get(x, y);
				Point aabbo = cell.getAABBOffset();
				AABB tile = new AABB(c.add(aabbo), c.add(aabbo).add(cell.getAABBDimensions()));
				if (cell.collides(motion) && aabb.overlaps(tile))
				{
					return cell;
				}
			}
		}

		return Cell.EMPTY;
	}

	/**
	 * Sweep from start until steps until you get a collision.
	 * @param start Starting position of the sweep.
	 * @param delta Delta between each step.
	 * @param widthHeight Width and height of the unit to sweep.
	 * @param steps Amount of steps to do.
	 * @param level Recursion loop cap.
	 * @return Collision after sweep
	 */
	private Collision sweep(Mover mover, Point start,
							Point delta, Point widthHeight, Point motion, int steps, int level)
	{
		if(level >= MAX_DEPTH)
		{
			return new Collision(start, true);
		}
		Point found = start;
		for(int i = 0; i <= steps; i++)
		{
			Point current = found.add(delta);
			Cell cell = checkLevelAABB(new AABB(current, current.add(widthHeight)), motion);
			if(cell != Cell.EMPTY)
			{
				if(cell == Cell.WALL)
				{
					mover.onWallCollision();
				}
				return sweep(
						mover, found, delta.divide(steps), widthHeight, motion, steps, level + 1);
			}

			found = current;
		}
		return new Collision(found, level != 0);
	}

	/**
	 * Find the next position for the given mover.
	 * @param mover Mover to get next position for.
	 * @return The next position of a given mover.
	 */
	public Collision findNextPosition(Mover mover)
	{
		Point wh = mover.getAABBDimensions();
		int steps = getSteps(mover, wh);

		if(steps == 0)
		{
			return new Collision(mover.getLocation(), false);
		}
		Point delta = mover.getMotion().divide(steps).divide(GameConstants.TICKS_PER_SEC);

		Collision collision = sweep(mover, mover.getLocation(), delta, mover.getAABBDimensions(),
				mover.getMotion(), steps, 0);

		double newXLocation
				= Math.round(collision.getPoint().getX() * ONE_DEV_PRECISION) * PRECISION;
		double newYLocation
				= Math.round(collision.getPoint().getY() * ONE_DEV_PRECISION) * PRECISION;

		Point newLocation = new Point(newXLocation, newYLocation);

		newLocation = checkLevelBounds(mover, newLocation);
		return new Collision(newLocation, collision.isCollided());
	}

	/**
	 * Get the amount of steps for a mover.
	 * @param mover Mover to compute for.
	 * @param wh Width and height of movers AABB.
	 * @return amount of steps.
	 */
	private int getSteps(Mover mover, Point wh)
	{
		double stepsX = Math.abs(mover.getMotion().getX()) / wh.getX();
		double stepsY = Math.abs(mover.getMotion().getY()) / wh.getY();

		return (int) Math.ceil(Math.max(stepsX, stepsY) * SAMPLING);
	}

	// 8 is the max recusion depth.
	@SuppressWarnings("checkstyle:magicnumber")
	private static final int MAX_DEPTH = 8;

	/**
	 * Checks whether the the mover is outside the bounds
	 * of the level and gives the correct new location.
     *
     * When the player goes through the bottom it appears at the top of the level.
     * When the player goes through the top of the level it appears at the bottom of the level.
     *
     * If the player tries to go through the sides of the level, nothing happens.
	 * @param mover the mover to check the level bounds for.
	 * @param newLocation the new location to check.
	 * @return The new location based on the bounds of the level.
	 */
	public Point checkLevelBounds(Mover mover, Point newLocation)
	{
        AABB boundingBox = mover.getAABB();
        double lowerLimit = this.level.getCells().getHeight() - boundingBox.getYRange().length();
        double upperLimit = 0;

        double leftLimit = 0;
        double rightLimit = this.level.getCells().getWidth() - boundingBox.getXRange().length();

        double newXLocation = Math.min(Math.max(leftLimit, newLocation.getX()), rightLimit);
        double newYLocation = newLocation.getY();

        if(newYLocation > lowerLimit)
        {
            newYLocation = upperLimit;
			addToLog("[CH]\tA mover went through the bottom of the level.");
        }
        if(newYLocation < upperLimit)
        {
            newYLocation = lowerLimit;
			addToLog("[CH]\tA mover went through the top of the level.");
        }

		return new Point(newXLocation, newYLocation);
	}

}
