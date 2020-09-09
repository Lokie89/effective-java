package item89;

import java.util.Arrays;

public enum ElvisEnum {
    INSTANCE,
    ;

    private String[] favoriteSongs = {
            "Hound Dog", "Heartbreak Hotel"
    };

    public void printFavorites(){
        System.out.println(Arrays.toString(favoriteSongs));
    }
}
