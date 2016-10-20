package EleksInterview.Entity.Drinks;

import java.math.BigDecimal;

/**
 * Created by Igoruch on 18.10.2016.
 */
public abstract class NoAlcoholDrink extends Drink {

    public NoAlcoholDrink(String name, BigDecimal price) {
        super(name, price);
    }
}
