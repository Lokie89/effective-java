package item28;

import item11.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lokie on 2020-07-22
 */
public class Main {
    public static void main(String[] args) {
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "타입이 달라 넣을 수 없다."; // ArrayStoreException

//        List<Object> objectList = new ArrayList<Long>(); // Compile Error
//        objectList.add("타입이 달라 넣을 수 없다."); // ArrayStoreException

//        List<String>[] test = new List<String>[3]; // Compile Error
        List<?>[] test = new ArrayList<?>[3]; // 가능
        List<?> wildList = new ArrayList<>();
//        wildList.add("test");
        List<?> wild = new ArrayList<>();
        shuffle(wild);

        List<String> strings = new ArrayList<>();
        strings.toArray();

        List stringList = new ArrayList<>();
        stringList.add("t");
        stringList.add("e");
        stringList.add("s");
        stringList.add("t");
        ChooserGenericArray stringChooserGenericArray = new ChooserGenericArray<>(stringList);
        System.out.println(stringChooserGenericArray.chooser());
    }

    static <T> void shuffle(List<T> list){

    }


}
