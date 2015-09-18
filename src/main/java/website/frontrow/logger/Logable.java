package website.frontrow.logger;

/**
 * Interface for logging.
 */
public interface Logable
{
    /**
     * Every class that implements Logable, must add actions to the log.
     * @param action Input a String that is the action performed.
     */
    void addToLog(String action);
}
