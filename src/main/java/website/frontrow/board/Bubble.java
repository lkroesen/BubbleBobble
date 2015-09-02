package website.frontrow.board;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Bubble extends Unit
{
    // A bubble can contain an enemy.
    private Enemy contains;

    /**
     * Constructor of the Bubble Unit.
     * @param nAlive
     * Input a byte to specify the amount of lives this unit has.
     */
    public Bubble(byte nAlive)
    {
        super(nAlive);
    }

    /**
     * Get the enemy that is contained within the bubble.
     * @return
     * Return the enemy that is in the bubble.
     */
    public Enemy getContains()
    {
        return contains;
    }

    /**
     * Set an enemy to be contained by a bubble.
     * @param contains
     * Sets the enemy as contained by the bubble.
     */
    public void setContains(Enemy contains)
    {
        this.contains = contains;
    }
}
