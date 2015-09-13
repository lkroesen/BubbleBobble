package website.frontrow.board;

import website.frontrow.Game;
import website.frontrow.level.Level;
import website.frontrow.util.CollisionHandler;
import website.frontrow.util.Point;

/**
 * Created by larsstegman on 13-09-15.
 * A mover. Controls all the movement and collision logic. This class is abstract which means that
 * subclasses can make use of all logic and override some parts of it.
 */
public abstract class Mover
{
    /**
     * The maximum x speed.
     */
    public static final int MAX_X_SPEED = 60;

    /**
     * The maximum y speed.
     */
    public static final int MAX_Y_SPEED = 10;


    private static final int MOVE_MOD = 7;


    private static final int GRAVITY_MOD = 16;

    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Direction direction;

    /**
     * Current location of an entity.
     */
    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Point location;

    /**
     * Current direction of motion.
     */
    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Point motion;

    /**
     * The direction that is to be added on the next tick.
     */
    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Point newMotion;

    /**
     * The collision handler to handle the collisions.
     */
    private CollisionHandler handler;

    /**
     * Creates a mover.
     * @param motion The current motion of the mover.
     * @param location The current location of the mover.
     */
    public Mover(Point motion, Point location)
    {
        this.motion = motion;
        this.direction = Direction.RIGHT;
        this.newMotion = new Point(0, 0);
        this.location = location;
    }

    /**
     * Get the location of a Unit.
     * @return Location of a unit.
     */
    public Point getLocation()
    {
        return location;
    }

    /**
     * Set the location of a unit.
     * Warning: Does not care about walls.
     * @param location New location of a unit.
     */
    public void setLocation(Point location)
    {
        this.location = location;
    }

    /**
     * Set direction of motion.
     * @return The direction of motion of this unit.
     */
    public Point getMotion()
    {
        return motion;
    }

    /**
     * Set direction of motion.
     * @param motion The new direction of motion of this unit.
     */
    public void setMotion(Point motion)
    {
        this.motion = motion;
    }

    /**
     * Get the direction the Unit is facing.
     * @return
     * Returns a Direction value.
     */
    public Direction getDirection()
    {
    	return this.direction;
    }

    /**
     * Set the direction the Unit is facing.
     * Checks whether the direction is a movement along the x-axis
     * and adjusts the 'faceLeft' value accordingly
     * @param dir
     * Sets the Direction value.
     */
    public void setDirection(Direction dir)
    {
    	this.direction = dir;
    }

    /**
     * Makes the unit go left.
     */
    public void goLeft()
    {
        // The horizontal orientation must immediately be changed, so the current horizontal motion
        // is set to 0.
        this.motion.setX(0);
        this.newMotion = new Point(-MOVE_MOD, 0);
    }

    /**
     * Makes the unit go right.
     */
    public void goRight()
    {
        this.motion.setX(0);
        this.newMotion = new Point(MOVE_MOD, 0);
    }

    /**
     * Update the direction variable according to the current motion variable.
     */
    private void updateDirection()
    {
        if (motion.getX() > 0)
        {
            this.setDirection(Direction.RIGHT);
        }
        else if (motion.getX() < 0)
        {
            this.setDirection(Direction.LEFT);
        }
        // Keep current direction if motion is zero.
    }

    /**
     * Makes the unit jump.
     */
    public void jump()
    {
        if(this.motion.getY() == 0)
        {
            this.motion.setY(0);
            this.newMotion = new Point(this.newMotion.getX(), -GRAVITY_MOD * MAX_Y_SPEED);
        }
    }

    /**
     * Called when this unit collides with a wall.
     */
    public void onWallCollision()
    {
    }

    /**
     * Ticks a mover in a given level context.
     * @param level Level context, for collision checking.
     */
    public void tick(Level level)
    {
        this.motion = this.motion.add(newMotion);
        updateDirection();
        this.newMotion = new Point(0, 0);

        double x = this.motion.getX();
        this.motion.setX(Math.max(Math.min(x, MAX_X_SPEED), -MAX_X_SPEED));

        Point movement = motion.divide(Game.TICKS_PER_SEC);

        this.handler = new CollisionHandler(level);
        this.handler.checkMoverCollision(location, movement, this);
        //TODO: Improve the way cell collisions are handled.
        if(!this.handler.checkCellCollision(location, movement, this))
        {
            this.location = this.location.add(movement);
        }
        else
        {
            this.onWallCollision();
        }

        applyGravity();

    }

    /**
     * Applies the gravity to the unit.
     */
    protected void applyGravity()
    {
        this.motion.setY(Math.max(MAX_Y_SPEED, this.motion.getY() - Game.GRAVITY));
        Point movement = motion.divide(Game.TICKS_PER_SEC);

        if(!this.handler.checkCellCollision(location, movement, this))
        {
            this.location = this.location.add(movement);
        }
        else
        {
            this.motion.setY(0);
        }
    }
}
