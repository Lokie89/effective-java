package item20;

import java.applet.AudioClip;

public interface Singer {
    AudioClip sing(Song s);
    default void singSomeThing(){
        System.out.println("SING!!");
    }
}
