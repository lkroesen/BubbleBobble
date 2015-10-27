package website.frontrow.game;

import website.frontrow.logger.DumpLog;
import website.frontrow.music.MusicPlayer;
import website.frontrow.util.keymap.KeyAction;
import website.frontrow.util.keymap.SingleKeyAction;

/**
 * Utility Actions.
 */
public class UtilActions
{
    private KeyAction volumeUp;
    private KeyAction volumeDown;
    private KeyAction dumpLog;

    /**
     * Create utilactions.
     */
    public UtilActions()
    {
        volumeUp = new SingleKeyAction(()-> MusicPlayer.getInstance().volumeAdjust(1.0d), false);
        volumeDown = new SingleKeyAction(()-> MusicPlayer.getInstance().volumeAdjust(-1.0d), false);
        dumpLog = new SingleKeyAction(()-> DumpLog.getInstance().createDump(), true);
    }

    /**
     * Get the action for volume up.
     * @return KeyAction for volume up.
     */
    public KeyAction getVolumeUp()
    {
        return volumeUp;
    }

    /**
     * Get the action for volume down.
     * @return KeyAction for volume down.
     */
    public KeyAction getVolumeDown()
    {
        return volumeDown;
    }

    /**
     * Get the action for dumping a log.
     * @return KeyAction for dumping a log.
     */
    public KeyAction getDumpLog()
    {
        return dumpLog;
    }

}
