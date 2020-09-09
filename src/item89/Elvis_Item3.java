package item89;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author lokie on 2020/06/30
 */
public class Elvis_Item3 implements Serializable {
    public static final Elvis_Item3 INSTANCE = new Elvis_Item3();

    // transient 하지 않은 필드 추가
    private String[] favoriteSongs = {
            "Hound Dog", "Heartbreak Hotel"
    };

    public void printFavorites(){
        System.out.println(Arrays.toString(favoriteSongs));
    }

    private Elvis_Item3() {
    }

    public void leaveTheBuilding() {

    }

    // 인스턴스 통제를 위한 readResolve
    private Object readResolve() {
        return INSTANCE;
    }
}
