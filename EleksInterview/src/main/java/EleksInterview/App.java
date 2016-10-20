package EleksInterview;

import EleksInterview.Entity.Drinks.Drink;
import EleksInterview.Entity.Ingredients.Ingredient;
import EleksInterview.Entity.Ingredients.Tomatoes;
import EleksInterview.Entity.Pizza.Pizza;
import EleksInterview.Entity.User;
import EleksInterview.Service.Menu;
import EleksInterview.Service.Order;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext aap=new ClassPathXmlApplicationContext("config.xml");

        Menu menu=(Menu) aap.getBean("menu");
        Order order=(Order) aap.getBean("order");

        List<Pizza> list=new ArrayList<Pizza>();
        List<Drink> list1=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        pizza.AddIngridient(menu.getIngredientsList().get(0));
        pizza.AddIngridient(menu.getIngredientsList().get(1));
        pizza.AddIngridient(menu.getIngredientsList().get(2));
        pizza.AddIngridient(menu.getIngredientsList().get(3));

        Pizza pizza1=menu.getPizzasList().get(1);
        Drink drink=menu.getDrinksList().get(3);
        list.add(pizza);
        list.add(pizza1);
        list1.add(drink);
        order.MakeAnOrder(list,list1);
        order.PrintCheck();
    }
}
