package website.frontrow.music;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;

/**
 * Class for detecting audio devices.
 */
public class AudioDetector
        implements Logable
{
    public static boolean noAudio = true;

    /**
     * Get the AudioDevice currently in use on the computer.
     * @return The Audio Device used.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public AudioDetector()
    {
        Line.Info[] audioDevicesList = new Line.Info[4];
        audioDevicesList[0] = Port.Info.SPEAKER;
        audioDevicesList[1] = Port.Info.HEADPHONE;
        audioDevicesList[2] = Port.Info.COMPACT_DISC;
        audioDevicesList[3] = Port.Info.LINE_OUT;

        for (int c = 0; c < audioDevicesList.length; c++)
        {
            if (AudioSystem.isLineSupported(audioDevicesList[c]))
            {
                noAudio = false;
                break;
            }
        }
    }

    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
}
