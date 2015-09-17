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
    public static final double GRAVITY = -3;

    /**
     * The maximum x speed.
     */
    public static final int MAX_X_SPEED = 60;

    /**
     * The maximum y speed.
     */
    public static final int MAX_Y_SPEED = 10;

    /**
     * The step to take when moving to the left or right.
     */
    public static final int MOVE_STEP = 7;

    /**
     * The impulse of jumping.
     */
    public static final int GRAVITY_MOD = 16;

    /**
     * The number of frames per second at which the game refreshes.
     */
    public static final int FRAME_REFRESH_RATE = 60;

    /**
     * The number of ticks per second that the game processes.
     */
    public static final int TICKS_PER_SEC = 60;
}
