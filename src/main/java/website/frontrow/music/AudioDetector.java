package website.frontrow.music;

import website.frontrow.logger.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;

/**
 * Class for detecting audio devices.
 */
public final class AudioDetector
{
    private boolean noAudio = true;

    private static final AudioDetector INSTANCE = new AudioDetector();

    /**
     * See if there's an audio device on the computer.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    /* Magicnumbers are due to directly numbering the arrays. */
    private AudioDetector()
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
                setNoAudio(false);
                Log.add("[AD]\tAudio device detected");
                break;
            }
        }
        Log.add("[AD]\t[ERROR]\tNo audio devices on this machine!");
    }

    /**
     * Set the value of noAudio.
     * @param noAudio Input the value to be set.
     */
    private void setNoAudio(boolean noAudio)
    {
        this.noAudio = noAudio;
    }

    /**
     * Get whether we can play audio on this device.
     * @return Returns true or false.
     */
    public boolean isNoAudio()
    {
        return noAudio;
    }

    /**
     * Get the instance of AudioDetector.
     * @return Returns the instance.
     */
    public static AudioDetector getInstance()
    {
        return INSTANCE;
    }
}
