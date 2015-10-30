package website.frontrow.game;

import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.music.MusicPlayer;
import website.frontrow.util.keymap.KeyAction;
import website.frontrow.util.keymap.SingleKeyAction;

/**
 * Utility Actions. These actions influence the Game or the ui settings.
 */
public class UtilActions
{
    private SingleKeyAction volumeUp;
    private SingleKeyAction volumeDown;
    private SingleKeyAction dumpLog;
    private SingleKeyAction toggleLog;

    /**
     * Create util actions.
     */
    public UtilActions()
    {
        volumeUp = new SingleKeyAction(()-> MusicPlayer.getInstance().volumeAdjust(1.0d), false);
        volumeDown = new SingleKeyAction(()-> MusicPlayer.getInstance().volumeAdjust(-1.0d), false);
        dumpLog = new SingleKeyAction(()-> DumpLog.getInstance().createDump(), true);
        toggleLog = new SingleKeyAction(() -> Log.togglePrinting(), true);
    }

    /**
     * Get the action for volume up.
     * @return KeyAction for volume up.
     */
    public SingleKeyAction getVolumeUp()
    {
        return volumeUp;
    }

    /**
     * Get the action for volume down.
     * @return KeyAction for volume down.
     */
    public SingleKeyAction getVolumeDown()
    {
        return volumeDown;
    }

    /**
     * Get the action for dumping a log.
     * @return KeyAction for dumping a log.
     */
    public SingleKeyAction getDumpLog()
    {
        return dumpLog;
    }

    /**
     * Get the action for toggling the printing.
     * @return KeyAction for toggling printing the log.
     */
    public SingleKeyAction getToggleLog()
    {
        return toggleLog;
    }
}
