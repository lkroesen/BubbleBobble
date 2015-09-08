package website.frontrow.level;

import website.frontrow.board.Unit;

/**
 * Something that fits in a single grid cell.
 */
public enum Cell
{
    /**
     * An empty cell.
     */
    EMPTY,
    /**
     * A wall.
     */
    WALL
    {
        @Override
        public boolean collides(Unit unit)
        {
            return true;
        }
    },
    /**
     * A platform.
     */
    PLATFORM
    {
        @Override
        public boolean collides(Unit unit)
        {
            return unit.getMotion().getY() > 0;
        }
    };

    /**
     * Would this type of cell collide with the unit in its current state.
     * @param unit The unit to use.
     * @return Whether we need to run collision code or not.
     */
    public boolean collides(Unit unit)
    {
        return false;
    }
}
