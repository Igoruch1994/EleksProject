package EleksInterview;

import EleksInterview.Entity.Drinks.Drink;
import EleksInterview.Entity.Pizza.Pizza;
import EleksInterview.Entity.User;
import EleksInterview.Service.Menu;
import EleksInterview.Service.PriceAudit;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Igoruch on 07.01.2017.
 */
public class PriceAuditTest {

    Menu menu=new Menu();
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
        priceAudit.calendar.set(2016, Calendar.OCTOBER,19);
        priceAudit.countPrice();
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
        priceAudit.calendar.set(2016, Calendar.OCTOBER,19);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("60.00"));
    }

    @Test
    public void TodayIsFridayPriceGrowUpByFivePercentage(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        User user=new User();
        user.setDiscount_code(false);
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016, Calendar.OCTOBER,21);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.setUser(user);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("63.00"));
    }
    @Test
    public void TodayIsSaturdayPriceGrowUpByFivePercentageAndDiscountCode() {
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        User user=new User();
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016, Calendar.OCTOBER,21);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.setUser(user);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("50.40"));
    }

    @Test
    public void TodayIsChristmasAndDiscount(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016,Calendar.JANUARY,7);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("30.00"));
    }

    @Test
    public void TodayIsIndependenceDay(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016,Calendar.AUGUST,24);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("30.00"));
    }

    @Test
    public void ProgrammerDayDiscount(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016,Calendar.SEPTEMBER,12);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("30.00"));
    }

    @Test
    public void ChekOnlyDicountCode(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        User user=new User();
        priceAudit.setUser(user);
        priceAudit.calendar.set(2016,Calendar.OCTOBER,19);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.verifyDiscount();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("48.00"));
    }

    @Test
    public void tippingTest(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016,Calendar.OCTOBER,19);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.getTipping();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("63.00"));
    }

    @Test
    public void FirstSaturdayOfWeekTest(){
        List<Pizza> orderPizza=new ArrayList<Pizza>();
        List<Drink> orderDrink=new ArrayList<Drink>();
        Pizza pizza=menu.getPizzasList().get(0);
        orderPizza.add(pizza);
        PriceAudit priceAudit=new PriceAudit();
        priceAudit.calendar.set(2016,Calendar.SEPTEMBER,3);
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.getTipping();
        Assert.assertEquals(priceAudit.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP),new BigDecimal("60.00"));
    }

    @Test
    public void CreateDrinkIngredientAndPizzaTest(){
        menu.createDrinks();
        menu.getIngredientsList();
        menu.createPizzas();
        boolean test=false;
        if (menu.getIngredientsList()!=null&&menu.getPizzasList()!=null&&menu.getDrinksList()!=null){
            test=true;
        }
        Assert.assertTrue(test);
    }

}
