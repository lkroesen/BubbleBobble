package website.frontrow.util;

import java.util.ArrayList;
import java.util.LinkedList;

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
	// Precision is a constant.
	@SuppressWarnings("checkstyle:magicnumber")
	private static final double PRECISION = 0.0001d;
	private static final double ONE_DEV_PRECISION = 1 / PRECISION;
	private static final int SAMPLING = 64;
	private static final Point ZERO = new Point(0, 0);
	//private static final Range Y_ANGLE_RANGE = new Range(Math.PI/4, Math.PI/4*2);
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
		for (Unit other : units)
		{
			if (other != user && other.getAABB().overlaps(me))
			{
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
	public CellCollision checkLevelAABB(AABB aabb, Point motion)
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
					return new CellCollision(new Point(x, y), cell);
				}
			}
		}

		return new CellCollision(new Point(0, 0), Cell.EMPTY);
	}

	// ParameterNumber: Due to the method being recursive,
	// some local variables need to be transferred in case
	// of the end case being reached.
	// Also to avoid having to recompute values.
	@SuppressWarnings("checkstyle:parameternumber")
	/**
	 * Sweep from start until steps until you get a collision.
	 * @param start Starting position of the sweep.
	 * @param delta Delta between each step.
	 * @param widthHeight Width and height of the unit to sweep.
	 * @param steps Amount of steps to do.
	 * @param level Recursion loop cap.
	 * @return Collision after sweep
	 */
	private Collision sweep(Point start, Point lastCell, Point delta, Point widthHeight,
							Point motion, int steps, int level)
	{
		Point lastCollision = start.add(delta.multiply(steps));
		if(level >= MAX_DEPTH)
		{
			return new Collision(start, lastCollision, true, lastCell);
		}

		Point found = start;
		for(int i = 0; i <= steps; i++)
		{
			Point current = found.add(delta);
			CellCollision cell =
					checkLevelAABB(new AABB(current, current.add(widthHeight)), motion);
			if(cell.getType() != Cell.EMPTY)
			{
				return sweep(found, cell.getLocation(),
						delta.divide(steps), widthHeight, motion, steps, level + 1);
			}

			found = current;
		}
		return new Collision(found, lastCollision, level != 0, lastCell);
	}

	/**
	 * Compute the new motion.
	 * @param position Position during the collision
	 * @param motion Motion during the collision
	 * @param cell Cell you collided with.
	 * @return The new motion.
	 */
	// 45 degree angles are not that magical.
	@SuppressWarnings("checkstyle:magicnumber")
	private Point computeNewMotion(Point position, Point motion, Point cell)
	{
		// Compute the angle of the collision.
		double diffangle = Math.abs(position.subtract(cell).angle());
		if(Math.PI / 4 < diffangle && diffangle < Math.PI / 4 * 3)
		{
			return new Point(motion.getX(), 0);
		}
		else
		{
			return new Point(0, motion.getY());
		}
	}

	// Special cap to avoid edge case infinite loops.
	@SuppressWarnings("checkstyle:magicnumber")
	private static final int CAP = 15;


	/**
	 * Find the next position of the given mover.
	 * @param mover The mover to find the next position for.
	 * @return The next position of the given mover.
	 */
	// Thanks to the braces, comments and whitespace,
	// this method is a bit longer than it really is.
	// Splitting the method may be possible,
	// but needs to be looked into rather than being fixed quickly.
	@SuppressWarnings("checkstyle:methodlength")
	public CollisionSummary findNextPosition(Mover mover)
	{
		double part = 1d;
		Point location = mover.getLocation();
		Point motion = mover.getMotion().divide(GameConstants.TICKS_PER_SEC);
		Boolean collided = false;
		LinkedList<Cell> cells = new LinkedList<>();
		// In some cases, the computation of the new motion vector is incorrect
		// (and may need tweaking) This is to avoid infinite loops in this edgecase.
		int edgecase = CAP;
		while(part > 0 && !motion.equals(ZERO) && edgecase-- > 0)
		{
			Collision collision = findWithMotion(mover, location, motion, part);
			// Compute new part.
			double partused = collision.getPoint().subtract(location).length()
					/ mover.getMotion().length();
			part -= partused;
			location = collision.getPoint();
			// Change motion on collision.
			if(collision.isCollided())
			{
				collided = true;
				motion = computeNewMotion(collision.getCurrent(),
						motion, collision.getCell());
				Point cellPosition = collision.getCell();
				cells.add(level.getCells().get(
						(int) cellPosition.getX(), (int) cellPosition.getY()));
			}
			else
			{
				// No collision occurred. Collision value should be fine.
				// However, due to bounds check the unit may have gotten warped.
				break;
			}
		}

		return new CollisionSummary(location,
				motion.multiply(GameConstants.TICKS_PER_SEC), collided, cells);
	}

	/**
	 * Find the next position for the given mover.
	 * @param mover Mover to get next position for.
	 * @param start The starting point.
	 * @param motion The motion.
	 * @param part The percentage of motion of this unit left in this tick.
	 * @return The next position of a given mover.
	 */
	public Collision findWithMotion(Mover mover, Point start, Point motion, double part)
	{
		Point widthHeight = mover.getAABBDimensions();
		int steps = getSteps(mover, part, widthHeight);

		if(steps == 0)
		{
			return new Collision(start, motion, false, null);
		}
		Point delta = motion.multiply(part).divide(steps);

		Collision collision = sweep(start, null, delta, mover.getAABBDimensions(),
				mover.getMotion(), steps, 0);

		double newXLocation
				= Math.round(collision.getPoint().getX() * ONE_DEV_PRECISION) * PRECISION;
		double newYLocation
				= Math.round(collision.getPoint().getY() * ONE_DEV_PRECISION) * PRECISION;

		Point newLocation = new Point(newXLocation, newYLocation);

		newLocation = checkLevelBounds(mover, newLocation);
		return new Collision(newLocation, collision.getCurrent(), collision.isCollided(),
				collision.getCell());
	}

	/**
	 * Get the amount of steps for a mover.
	 * @param mover Mover to compute for.
	 * @param widthHeight Width and height of movers AABB.
	 * @return amount of steps.
	 */
	private int getSteps(Mover mover, double delta, Point widthHeight)
	{
		double stepsX = Math.abs(mover.getMotion().getX() * delta) / widthHeight.getX();
		double stepsY = Math.abs(mover.getMotion().getY() * delta) / widthHeight.getY();

		return (int) Math.ceil(Math.max(stepsX, stepsY) * SAMPLING);
	}

	// 8 is the max recusion depth.
	@SuppressWarnings("checkstyle:magicnumber")
	private static final int MAX_DEPTH = 3;

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

/**
 * A collision with a cell.
 */
class CellCollision
{
	private final Point location;
	private final Cell type;

	/**
	 * Construct a CellCollision.
	 * @param location Cell location.
	 * @param type Cell type.
	 */
	CellCollision(Point location, Cell type)
	{
		this.location = location;
		this.type = type;
	}

	/**
	 * Get the location of the cell that caused a collision.
	 * @return The cells' location.
	 */
	public Point getLocation()
	{
		return location;
	}

	/**
	 * Get the type of the cell that caused a collision.
	 * @return The cells' type.
	 */
	public Cell getType()
	{
		return type;
	}

}