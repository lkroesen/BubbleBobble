package website.frontrow.game;

/**
 * Contains all the constants which we use in the game.
 */
@SuppressWarnings("visibilitymodifier")
// These constants have to be accessible everywhere.
public abstract class GameConstants
{

    /**
     * The constant for the gravity in the game.
     */
    public static final double GRAVITY = -1.5;

    /**
     * The maximum x speed.
     */
    public static final int MAX_X_SPEED = 15;

    /**
     * The maximum y speed.
     */
    public static final int MAX_Y_SPEED = 20;

    /**
     * The step to take when moving to the left or right.
     */
    public static final int MOVE_STEP = 14;

    /**
     * The impulse of jumping.
     */
    public static final double JUMP_IMPULSE = 1.2;

    /**
     * The number of frames per second at which the game refreshes.
     */
    public static final int FRAME_REFRESH_RATE = 60;

    /**
     * The number of ticks per second that the game processes.
     */
    public static final int TICKS_PER_SEC = 60;
    
    
    /**
     * The multiplier for the enemy speed.
     */
    public static final double ENEMY_SPEED = 0.25;
    
    /**
     * Percentage of enemy movements that is randomized.
     */
    public static final float AI_RANDOMIZER = 0.25f;
    
    /**
     * Amount of ticks per enemy move update.
     */
    public static final int TICKS_PER_MOVE = 150;
}
