package EleksInterview.Service;

import EleksInterview.Entity.Check;
import EleksInterview.Entity.Drinks.Drink;
import EleksInterview.Entity.Ingredients.Ingredient;
import EleksInterview.Entity.Pizza.Pizza;
import EleksInterview.Entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igoruch on 18.10.2016.
 */
public class Order {
    public List<Pizza> orderPizza=new ArrayList<Pizza>();
    public List<Drink> orderDrink=new ArrayList<Drink>();
    public PriceAudit priceAudit;
    User user;
    Check check;

    public void MakeAnOrder(List<Pizza> pizzaList,List<Drink> drinkList){
        this.orderDrink=drinkList;
        this.orderPizza=pizzaList;
        priceAudit.makeCheckBy(orderPizza,orderDrink);
        priceAudit.countPrice();
        priceAudit.checkDiscounts();
    }
    public void PrintCheck(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Dear "+user.getName()+" !");
        System.out.println("Your check!");
        System.out.println("You made the order:");
        for (Pizza pizza:orderPizza){
            if (priceAudit.dow==6||priceAudit.dow==7||priceAudit.dow==1) {
                System.out.println("Pizza : " + pizza.getName()+"  "+ pizza.getPrice().add(pizza.getPrice().multiply(new BigDecimal(0.05))).setScale(2, BigDecimal.ROUND_HALF_UP)+" grn.");
            }else System.out.println("Pizza : " + pizza.getName() +"  "+ pizza.getPrice()+" grn.");
            for (Ingredient ingredient:pizza.getAllIngrediants()){
                if (priceAudit.dow==6||priceAudit.dow==7||priceAudit.dow==1) {
                    System.out.println("       Add ingredient : " + ingredient.getName()+"  "+ingredient.getPrice().add(ingredient.getPrice().multiply(new BigDecimal(0.05))).setScale(2, BigDecimal.ROUND_HALF_UP)+" grn.");
                }
                else System.out.println("       Add ingredient : " + ingredient.getName()+"  "+ingredient.getPrice()+" grn.");
            }
        }
        if(orderDrink!=null) {
            for (Drink drink : orderDrink) {
                if (priceAudit.dow==6||priceAudit.dow==7||priceAudit.dow==1) {
                    System.out.println("Drink : " + drink.getName() + "  " +drink.getPrice().add(drink.getPrice().multiply(new BigDecimal(0.05))).setScale(2, BigDecimal.ROUND_HALF_UP)+" grn.");
                }
                else System.out.println("Drink : " + drink.getName() + "  " +drink.getPrice()+" grn.");
            }
        }
        System.out.println(check.getMessage());
        System.out.println("Your score : "+ check.getScore().setScale(2, BigDecimal.ROUND_HALF_UP)+" grn");
        System.out.println("\n________________________________________________________________________________");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public void setPriceAudit(PriceAudit priceAudit) {
        this.priceAudit = priceAudit;
    }
}
