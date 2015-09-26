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
    public static final double ENEMY_SPEED_MULTIPLIER = 0.25;

    /**
     * The multiplier for the player speed.
     */
    public static final double PLAYER_SPEED_MULTIPLIER = 1.0;

    /**
     * The multiplier for the bubble speed.
     */
    public static final double BUBBLE_SPEED_MULTIPLIER = 1.0;

    /**
     * Percentage of enemy movements that is randomized.
     */
    public static final float AI_RANDOMIZE = 0.20f;

    /**
     * Amount of ticks per enemy move update.
     */
    public static final int TICKS_PER_MOVE = 150;
}