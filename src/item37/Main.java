package item37;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lokie on 2020-07-30
 */
public class Main {
    public static void main(String[] args) {
        Plant[] garden = new Plant[3];
        garden[0] = new Plant("나는 장미", Plant.LifeCycle.ANNUAL);
        garden[1] = new Plant("나는 할미꽃", Plant.LifeCycle.BIENNIAL);
        garden[2] = new Plant("나는 잔디", Plant.LifeCycle.BIENNIAL);


//        Set<Plant>[] plantsByLifeCycle =
//                new Set[Plant.LifeCycle.values().length];
//
//        for (int i = 0; i < plantsByLifeCycle.length; i++) {
//            plantsByLifeCycle[i] = new HashSet<>();
//        }
//
//        for (Plant p : garden) {
//            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
//        }
//
//        for (int i = 0; i < plantsByLifeCycle.length; i++) {
//            System.out.printf("%s : %s%n",
//                    Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
//        }

        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle =
                new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

        for (Plant p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }

        System.out.println(plantsByLifeCycle);


        System.out.println(Arrays.stream(garden)
                .collect(Collectors.groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(Plant.LifeCycle.class),
                        Collectors.toSet())));

    }
}
