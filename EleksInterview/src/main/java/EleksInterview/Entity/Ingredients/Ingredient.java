package EleksInterview.Entity.Ingredients;

import java.math.BigDecimal;

/**
 * Created by Igoruch on 18.10.2016.
 */
public abstract class Ingredient {
    private String name;
    private BigDecimal price;

    public Ingredient(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
