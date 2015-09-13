package website.frontrow.board;

import java.awt.Graphics;

import website.frontrow.level.Level;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.Point;

/**
 * A Unit, or Entity is something that is part of a level, but not restricted to grid cells.
 */
public abstract class Unit
    extends Mover
{

    /**
     * Amount of lives an entity has.
     */
    private boolean alive;


    /**
     * Constructor of the Unit Class.
     * @param alive Whether this entity is alive
     * @param location the current location of the unit.
     * @param motion The starting motion.
     */
    public Unit(boolean alive, Point location, Point motion)
    {
        super(motion, location);
        this.alive = alive;

    }

    /**
     * Get the status of the unit, whether it's dead or alive.
     * @return
     * Return true if the Unit is alive, false if it's dead.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Kills an enemy.
     */
    public void kill()
    {
        alive = false;
    }

    /**
     * Returns the sprite of the unit.
     * @return The sprite.
     */
    public abstract Sprite getSprite();
    
    /**
     * Draws the unit.
     * @param g The graphics context to draw in.
     * @param x The x coordinate to draw the unit at.
     * @param y The y coordinate to draw the unit at.
     * @param width The width to draw the unit with.
     * @param height The height to draw the unit with.
     */
    public void draw(Graphics g, int x, int y, int width, int height)
    {
    	Point location = this.getLocation();
    	int xCoordinate = (int) (location.getX() * width + x);
    	int yCoordinate = (int) (location.getY() * height + y);
    	getSprite().draw(g, xCoordinate, yCoordinate, width, height);
    }

    @Override
    public void tick(Level level)
    {
        super.tick(level);
    }

}
