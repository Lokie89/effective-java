package item2;

/**
 * @author lokie on 2020/06/29
 */
public class Main {
    NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
            .calories(100).sodium(35).carbohydrate(27).build();

    NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
            .addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
    Calzone calzone = new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();
}
