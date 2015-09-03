package website.frontrow.board;

import website.frontrow.util.Point;

/**
 * A Unit, or Entity is something that is part of a level, but not restricted to grid cells.
 */
public class Unit
{
    /**
     * Amount of lives an entity has
     */
    private boolean alive;

    /**
     * Current location of an entity.
     */
    private Point location;
    /**
     * Current direction of motion.
     */
    private Point motion;

    /**
     * Constructor of the Unit Class.
     * @param alive Whether this entity is alive
     * Input the amount of lives the Unit has.
     */
    public Unit(boolean alive, Point location, Point motion)
    {
        this.alive = alive;
        this.location = location;
        this.motion = motion;
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
     * Get the location of a Unit.
     * @return Location of a unit.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Set the location of a unit.
     * Warning: Does not care about walls.
     * @param location New location of a unit.
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Set direction of motion.
     * @return The direction of motion of this unit.
     */
    public Point getMotion() {
        return motion;
    }

    /**
     * Set direction of motion.
     * @param motion The new direction of motion of this unit.
     */
    public void setMotion(Point motion) {
        this.motion = motion;
    }


}
