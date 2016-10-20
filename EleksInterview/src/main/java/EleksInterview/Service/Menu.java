package EleksInterview.Service;

import EleksInterview.Entity.Drinks.*;
import EleksInterview.Entity.Ingredients.*;
import EleksInterview.Entity.Pizza.Pizza;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igoruch on 18.10.2016.
 */
public class Menu implements MenuFactory {

    public List<Ingredient> ingredientsList=new ArrayList<Ingredient>();
    public List<Drink> drinksList=new ArrayList<Drink>();
    public List<Pizza> pizzasList=new ArrayList<Pizza>();

    public Menu() {
        createPizzas();
        createIngrediantes();
        createDrinks();
    }

    public void createPizzas() {
        pizzasList.add(new Pizza("Margarita",new BigDecimal("60.00")));
        pizzasList.add(new Pizza("Assorti",new BigDecimal("65.50")));
        pizzasList.add(new Pizza("Havai",new BigDecimal("70.00")));
    }

    public void createIngrediantes() {
        ingredientsList.add(new Cheese("Cheese Brinza",new BigDecimal("15.50")));
        ingredientsList.add(new Cheese("Cheese Moonster",new BigDecimal("23.50")));
        ingredientsList.add(new Cheese("Cheese Rokfor",new BigDecimal("30.99")));
        ingredientsList.add(new Tomatoes("Tomatoes",new BigDecimal("7.50")));
        ingredientsList.add(new Ham("Ham",new BigDecimal("30.25")));
        ingredientsList.add(new Mushroom("White mushroom",new BigDecimal("40.55")));
    }

    public void createDrinks() {
        drinksList.add(new Beer("Bud",new BigDecimal("12.50")));
        drinksList.add(new Viski("Hennessy",new BigDecimal("230.50")));
        drinksList.add(new Tea("Lipton",new BigDecimal("2.50")));
        drinksList.add(new Coffee("Espresso",new BigDecimal("2.50")));
        drinksList.add(new Juice("Orange",new BigDecimal("2.50")));
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public List<Drink> getDrinksList() {
        return drinksList;
    }

    public List<Pizza> getPizzasList() {
        return pizzasList;
    }
}
