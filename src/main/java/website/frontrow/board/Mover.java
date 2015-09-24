package website.frontrow.board;

import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.util.Collision;
import website.frontrow.util.CollisionHandler;
import website.frontrow.game.GameConstants;
import website.frontrow.util.Point;

/**
 * Created by larsstegman on 13-09-15.
 * A mover. Controls all the movement and collision logic. This class is abstract which means that
 * subclasses can make use of all logic and override some parts of it.
 */
public abstract class Mover
    extends Unit
        implements Logable
{
    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Direction direction;

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
     * @param alive Whether the mover is alive.
     * @param location The current location of the mover.
     * @param motion The current motion of the mover.
     */
    public Mover(boolean alive, Point location, Point motion)
    {
        super(alive, location);
        this.motion = motion;
        this.direction = Direction.RIGHT;
        this.newMotion = new Point(0, 0);
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
        this.newMotion.setX(-GameConstants.MOVE_STEP);
    }

    /**
     * Makes the unit go right.
     */
    public void goRight()
    {
        this.newMotion.setX(GameConstants.MOVE_STEP);
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
            this.newMotion.setY(-GameConstants.JUMP_IMPULSE * GameConstants.MAX_Y_SPEED);
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
        this.motion.setX(
                Math.max(Math.min(x, GameConstants.MAX_X_SPEED),
                        -GameConstants.MAX_X_SPEED));

        Point movement = motion.divide(GameConstants.TICKS_PER_SEC);

        if (!movement.toString().equals("Point(0.0, 0.0)"))
        {
            addToLog("[MOVER]\tMoved to " + movement.toString());
        }

        this.handler = level.getCollisionHandler();
        this.handler.checkUnitsAABB(this, level.getRealCollisionHandler());
        this.location = handler.findNextPosition(this).getPoint();

        applyGravity();
    }

    /**
     * Applies the gravity to the unit.
     */
    protected void applyGravity()
    {
        this.motion.setY(this.motion.getY() - GameConstants.GRAVITY);

        Collision c = this.handler.findNextPosition(this);
        if(c.isCollided())
        {
            this.motion.setY(0);
        }

    }

    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
