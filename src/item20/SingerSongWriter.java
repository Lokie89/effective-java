package item20;

import java.applet.AudioClip;

public interface SingerSongWriter extends Singer, SongWriter {
    AudioClip strum();
    void actSensitive();
}
