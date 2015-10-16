package website.frontrow.keybindings;

import website.frontrow.board.Player;
import website.frontrow.logger.DumpLog;
import website.frontrow.logger.Log;
import website.frontrow.music.MusicPlayer;
import website.frontrow.ui.PlayerAction;

/**
 * The actions which can be performed with key presses.
 */
public enum JBubbleBobbleKeyAction
    implements PlayerAction
{
    JUMP(p -> p.jump()),
    RIGHT(p -> p.goRight()),
    LEFT(p -> p.goLeft()),
    SHOOT(p -> p.shoot()),
    PRINT_LOG(p -> Log.togglePrinting()),
    DUMP_LOG(p -> new DumpLog()),
    VOLUME_UP(p -> MusicPlayer.getInstance().volumeAdjust(1.0d)),
    VOLUME_DOWN(p -> MusicPlayer.getInstance().volumeAdjust(-1.0d));

    private PlayerAction action;

    JBubbleBobbleKeyAction(PlayerAction action)
    {
        this.action = action;
    }

    @Override
    public void doAction(Player player)
    {
        action.doAction(player);
    }
}
