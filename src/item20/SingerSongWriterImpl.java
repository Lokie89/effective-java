package item20;

import java.applet.AudioClip;

public class SingerSongWriterImpl implements SingerSongWriter{
    @Override
    public AudioClip strum() {
        return null;
    }

    @Override
    public void actSensitive() {

    }

    @Override
    public AudioClip sing(Song s) {
        return null;
    }

    @Override
    public Song compose(int chartPosition) {
        return null;
    }

    public static void main(String[] args) {
        Singer singer = new SingerSongWriterImpl();
        singer.singSomeThing();
    }
}
