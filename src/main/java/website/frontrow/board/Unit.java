package website.frontrow.board;

import website.frontrow.level.Square;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Unit
{
    private byte lives;
    private Square[][] location;

    /**
     * Constructor of the Unit Class.
     * @param nAlive
     * Input the amount of lives the Unit has.
     */
    public Unit(byte nAlive)
    {
        setLives(nAlive);
    }

    /**
     * Get the status of the unit, wheter it's dead or alive.
     * @return
     * Return true if the Unit is alive, false if it's dead.
     */
    public boolean getAlive()
    {
        return lives > 0;
    }

    /**
     * Get the amount of lives the Unit currently has.
     * @return
     * Return a byte with the amount of lives.
     */
    public byte getLives()
    {
        return lives;
    }

    /**
     * Set the amount of lives the Unit has.
     * Checks if the unit already has 0 lives.
     * @param lives
     * Input a byte with the number of lives for the unit.
     */
    public void setLives(byte lives)
    {
        if (lives >= 0)
        {
            this.lives = lives;
        }
        else
        {
            this.lives = 0;
        }
    }

    /**
     * Unit loses one life, if the unit is at 0, doesn't lose anything.
     */
    public void loseLife()
    {
        if (lives > 0)
        {
            --lives;
        }
    }

    /**
     * Awards the Unit with one extra life.
     */
    public void gainLife()
    {
        ++lives;
    }

    /**
     * Class Under Construction, Moves the unit to the desired space.
     * @param loc
     * Input a square[][] with the location to move towards.
     */
    private void moveTo(Square[][] loc)
    {

    }
}
