package website.frontrow.board;

import java.awt.Graphics;

import website.frontrow.level.Level;
import website.frontrow.sprite.Sprite;
import website.frontrow.util.AABB;
import website.frontrow.util.Point;

/**
 * A Unit, or Entity is something that is part of a level, but not restricted to grid cells.
 */
public abstract class Unit
{
    /**
     * Amount of lives an entity has.
     */
    private boolean alive;

    /**
     * Current location of an entity.
     */
    @SuppressWarnings("visibilitymodifier") // subclasses have to have access to this variable
    protected Point location;

    /**
     * Constructor of the Unit Class.
     * @param alive Whether this entity is alive
     * @param location the current location of the unit.
     */
    public Unit(boolean alive, Point location)
    {
        this.alive = alive;
        this.location = location;
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
     * Set alive.
     * @param alive the value.
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    /**
     * Returns the sprite of the unit.
     * @return The sprite.
     */
    public abstract Sprite getSprite();


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

    /**
     * Do the logic for this tick.
     * @param level The level in which the unit has to perform its logic.
     */
    public void tick(Level level)
    {

    }

    /**
     * Get the width and height for the bounding box.
     * @return Width and height of AABB
     */
    public Point getAABBDimensions()
    {
        return new Point(1, 1);
    }

    /**
     * Get the current AABB.
     * @return The current AABB.
     */
    public AABB getAABB()
    {
        return new AABB(getLocation(), getLocation().add(getAABBDimensions()));
    }

}
