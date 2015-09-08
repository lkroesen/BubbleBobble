package website.frontrow.level;

import website.frontrow.board.Unit;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Square
{
    private Unit occupied;

    /**
     * Constructor of the Square Class.
     */
    public Square()
    {

    }

    /**
     * Get the Unit that is occupying the square.
     * @return
     * Returns a Unit that occupies the square.
     */
    public Unit getOccupied()
    {
        return occupied;
    }

    /**
     * Set the Unit that occupies the square.
     * @param occupied
     * Input a Unit to occupy the square.
     */
    public void setOccupied(Unit occupied)
    {
        this.occupied = occupied;
    }
}
