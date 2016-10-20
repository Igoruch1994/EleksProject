package EleksInterview;

import EleksInterview.Entity.Drinks.Drink;
import EleksInterview.Entity.Pizza.Pizza;
import EleksInterview.Entity.User;
import EleksInterview.Service.Menu;
import EleksInterview.Service.Order;
import EleksInterview.Service.PriceAudit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Igoruch on 07.01.2017.
 */
public class PriceAuditTest {
    AbstractApplicationContext aap=new ClassPathXmlApplicationContext("config.xml");

    Menu menu=(Menu) aap.getBean("menu");
    @Test
    public void todayIsMondayTest(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        orderDrink.add(menu.getDrinksList().get(3));
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.calendar.set(2016, Calendar.OCTOBER,17);
        priceAudit.countPrice();
        priceAudit.mondayDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("00.00"));
    }

    @Test
    public void addSomePizzaAndDrinkAndCountPrice(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        orderDrink.add(menu.getDrinksList().get(3));
        Pizza pizza=menu.getPizzasList().get(0);
        pizza.AddIngridient(menu.getIngredientsList().get(0));
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.calendar.set(2016, Calendar.OCTOBER,19);
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("78.00"));
    }

    @Test
    public void AddTwoPizzaAndChekIsSecondPizzaFree(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        Pizza pizza1=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        orderPizza.add(pizza1);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("60.00"));
    }

}
