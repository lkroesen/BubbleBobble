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
    @SuppressWarnings("checkstyle:visibilitymodifier")
    /* Due to intended implementations this will be public. */
    private static boolean noAudio = true;

    /**
     * See if there's an audio device on the computer.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    /* Magicnumbers are due to directly numbering the arrays. */
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
                addToLog("[AD]\tAudio device detected");
                break;
            }
        }
        addToLog("[AD]\t[ERROR]\tNo audio devices on this machine!");
    }

    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Get whether we can play audio on this device.
     * @return Returns true or false.
     */
    public static boolean isNoAudio()
    {
        return noAudio;
    }
}
