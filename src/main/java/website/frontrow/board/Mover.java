package website.frontrow.board;

import website.frontrow.board.behaviour.DefaultGravityBehaviour;
import website.frontrow.board.behaviour.GravityBehaviour;
import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.CollisionComputer;
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
     * Prime number used for hashing.
     */
    private static final int PRIME = 31;

    /**
     * Current direction of motion.
     */
    private Point motion;

    /**
     * The direction that is to be added on the next tick.
     */
    private Point newMotion = new Point(0, 0);

    /**
     * The sprites for each direction.
     */
    private Map<Direction, Sprite> sprites;

    private Direction previousDirection = Direction.RIGHT;

    /**
     * The gravity behaviour for this mover.
     */
    private GravityBehaviour gravity;

    /**
     * Creates a mover.
     * @param alive Whether the mover is alive.
     * @param location The current location of the mover.
     * @param motion The current motion of the mover.
     * @param sprites The sprites for this mover.
     */
    public Mover(boolean alive, Point location, Point motion, Map<Direction, Sprite> sprites)
    {
        this(alive, location, motion, sprites, new DefaultGravityBehaviour());
    }

    /**
     * Creates a mover with the specified gravity behaviour.
     * @param alive Whether the mover is alive.
     * @param location The current location of the mover.
     * @param motion The current motion of the mover.
     * @param sprites The sprites for this mover.
     * @param gravity The gravity behaviour for this mover.
     */
    public Mover(boolean alive, Point location, Point motion, Map<Direction, Sprite> sprites,
                 GravityBehaviour gravity)
    {
        super(alive, location);
        this.motion = motion;
        this.sprites = sprites;
        this.gravity = gravity;
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
     * Returns the list of sprites for this mover.
     * @return The sprites.
     */
    public Map<Direction, Sprite> getSprites()
    {
        return sprites;
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
     * @return The current direction value.
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
        this.previousDirection = Direction.LEFT;
        this.newMotion.setX(-GameConstants.MOVE_STEP);
    }

    /**
     * Makes the unit go right.
     */
    public void goRight()
    {
        this.previousDirection = Direction.RIGHT;
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

        CollisionComputer handler = level.getCollisionComputer();
        handler.checkUnitsAABB(this, level.getCollisionHandler());

        this.location = handler.findNextPosition(this).getPoint();

        this.gravity.apply(this, handler);
        
        ArtificialIntelligence artificialintelligence = new ArtificialIntelligence(level);
        artificialintelligence.aiMover();
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Mover)
        {
            Mover that = (Mover) other;
            return super.equals(other) && this.motion.equals(that.motion);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode() + PRIME * motion.hashCode();
    }

    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
