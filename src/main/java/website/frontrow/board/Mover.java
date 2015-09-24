package website.frontrow.board;

import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Collision;
import website.frontrow.util.CollisionHandler;
import website.frontrow.artificial.intelligence.ArtificialIntelligence;
import website.frontrow.game.GameConstants;
import website.frontrow.util.Point;

import java.util.Map;

/**
 * Created by larsstegman on 13-09-15.
 * A mover. Controls all the movement and collision logic. This class is abstract which means that
 * subclasses can make use of all logic and override some parts of it.
 */
public abstract class Mover
    extends Unit
        implements Logable
{
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
     * The sprites for each direction.
     */
    private Map<Direction, Sprite> sprites;

    private Direction previousDirection = Direction.RIGHT;

    /**
     * Creates a mover.
     * @param alive Whether the mover is alive.
     * @param location The current location of the mover.
     * @param motion The current motion of the mover.
     * @param sprites The sprites for this mover.
     */
    public Mover(boolean alive, Point location, Point motion, Map<Direction, Sprite> sprites)
    {
        super(alive, location);
        this.motion = motion;
        this.newMotion = new Point(0, 0);
        this.sprites = sprites;
    }

    /**
     * Returns the sprite for the mover.
     * @return the sprite.
     */
    public Sprite getSprite()
    {
        return sprites.get(getDirection());
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
        if(this.motion.getX() > 0)
        {
            previousDirection = Direction.RIGHT;
        }
        if(this.motion.getX() < 0)
        {
            previousDirection = Direction.LEFT;
        }
        return previousDirection;
    }

    /**
     * Makes the unit go left.
     */
    public void goLeft()
    {
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
        this.newMotion = new Point(0, 0);

        double x = this.motion.getX();
        
    	this.motion.setX(
    		Math.max(Math.min(x, GameConstants.MAX_X_SPEED),
    			-GameConstants.MAX_X_SPEED) * this.getSpeedMultiplier());
        
        Point movement = motion.divide(GameConstants.TICKS_PER_SEC);

        this.handler = new CollisionHandler(level);
        this.handler.checkUnitsAABB(this);
        this.location = handler.findNextPosition(this).getPoint();

        if (!movement.equals(new Point(0, 0)))
        {
            addToLog("[MOVER]\tMoved to " + this.location.toString());
        }
        applyGravity();
        
        ArtificialIntelligence artificialintelligence = new ArtificialIntelligence(level);
        artificialintelligence.aiMover();
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
