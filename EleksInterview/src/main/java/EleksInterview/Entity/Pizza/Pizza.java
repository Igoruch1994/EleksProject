package EleksInterview.Entity.Pizza;

import EleksInterview.Entity.Ingredients.Ingredient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igoruch on 18.10.2016.
 */
public  class Pizza {
    private String name;
    private BigDecimal price;
    private List<Ingredient> allIngrediants=new ArrayList<Ingredient>();

    public Pizza(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public  void AddIngridient(Ingredient ingredient){
        allIngrediants.add(ingredient);
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

    public List<Ingredient> getAllIngrediants() {
        return allIngrediants;
    }

    public void setAllIngrediants(List<Ingredient> allIngrediants) {
        this.allIngrediants = allIngrediants;
    }
}
