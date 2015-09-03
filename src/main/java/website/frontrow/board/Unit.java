package website.frontrow.board;

import website.frontrow.util.CollisionHandler;
import website.frontrow.level.Square;

/**
 * Created by lkroesen on 9/2/2015.
 */
public class Unit
{
    private byte lives;
    private Square location;
    private Direction direction;
    private boolean faceLeft;

    /**
     * Constructor of the Unit Class.
     * @param nAlive
     * Input the amount of lives the Unit has.
     * @param direction
     * Set the direction the unit is going in.
     * @param faceLeft
     * A unit always starts facing the right.
     */
    public Unit(byte nAlive)
    {
        setLives(nAlive);
        this.direction = Direction.RIGHT;
        setFace(false);
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
     * Gets the direction the Unit is facing.
     * @Return 
     * Returns a bool: true for left, false for right.
     */
    public boolean faceLeft(){
    	return faceLeft;
    }
    
    /**
     * Set the direction the Unit is facing.
     * @param dir
     * Set the boolean value for the direction the Unit faces.
     */
    public void setFace(Boolean dir){
    	this.faceLeft = dir;
    }
    
    /**
     * Get the direction the Unit is facing.
     * @return
     * Returns a Direction value.
     */
    public Direction getDirection(){
    	return this.direction;
    }
    
    /**
     * Set the direction the Unit is facing.
     * Checks whether the direction is a movement along the x-axis 
     * and adjusts the 'faceLeft' value accordingly
     * @param dir
     * Sets the Direction value.
     */
    public void setDirection(Direction dir){
    	this.direction = dir;
    	
    	if(dir == Direction.RIGHT){
    		setFace(false);
    	}
    	else if(dir == Direction.LEFT){
    		setFace(true);
    	}
    }

    /**
     * Get the square the unit is currently located.
     * @return
     * Returns the square with the location.
     */
    public Square getSquare(){
    	return location;
    }
    
    /**
     * Class Under Construction, Moves the unit to the desired space.
     * @param des
     * Input a square with the location to move towards.
     */
    private void moveTo(Square des)
    {
    	Square loc = this.getSquare();
    	//should we call checkCollision here?
    	
    }
}
