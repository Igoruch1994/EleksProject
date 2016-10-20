package EleksInterview.Entity.Drinks;

import java.math.BigDecimal;

/**
 * Created by Igoruch on 18.10.2016.
 */
public abstract class AlcoholDrink extends Drink {

    public AlcoholDrink(String name, BigDecimal price) {
        super(name, price);
    }
}
