package EleksInterview.Service;

import EleksInterview.Entity.Check;
import EleksInterview.Entity.Drinks.Coffee;
import EleksInterview.Entity.Drinks.Drink;
import EleksInterview.Entity.Ingredients.Ingredient;
import EleksInterview.Entity.Pizza.Pizza;
import EleksInterview.Entity.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Igoruch on 19.10.2016.
 */
public class PriceAudit {
    BigDecimal price=new BigDecimal("00.00");
    public Check check;
    public Calendar calendar=Calendar.getInstance();
    private List<Pizza> orderPizza=new ArrayList<Pizza>();
    private List<Drink> orderDrink=new ArrayList<Drink>();
    private StringBuffer message=new StringBuffer();
    public User user;
    int dow;
    int weekOfMonth;
    int month;
    int doy;
    int dom;

    public void makeCheckBy(List<Pizza> orderPizza, List<Drink> orderDrink){
        this.orderPizza = orderPizza;
        this.orderDrink = orderDrink;
    }

    public void countPrice(){
         dow=calendar.get(Calendar.DAY_OF_WEEK);
         weekOfMonth=calendar.get(Calendar.WEEK_OF_MONTH);
         month=calendar.get(Calendar.MONTH);
         doy=calendar.get(Calendar.DAY_OF_YEAR);
        dom=calendar.get(Calendar.DAY_OF_MONTH);

        if (orderPizza!=null) {
            for (int i = 1; i <= orderPizza.size(); i++) {
                if (!(i % 2 == 0)) {
                    price = price.add(orderPizza.get(i - 1).getPrice());
                    for (Ingredient ingredient : orderPizza.get(i - 1).getAllIngrediants()) {
                        price = price.add(ingredient.getPrice());
                    }
                }
            }
        }
        message.append("Every second pizza free!\n");

        for (Drink drink:orderDrink){
            price=price.add(drink.getPrice());
        }
    }
    public void checkDiscounts(){
        mondayDiscount();
        verifyDiscount();
        getTipping();
        check.setScore(price);
        check.setMessage(message);
    }
    public void mondayDiscount(){
        if (dow==2&&orderDrink!=null){
            for (Drink drink:orderDrink){
                if (drink instanceof Coffee){
                    price=price.subtract(drink.getPrice());
                    message.append("Today is mondey, so you have free coffe!\n");
                }
            }
        }
    }
    public void verifyDiscount(){
        if (dow==6||dow==7||dow==1){
            price=price.add(price.multiply(new BigDecimal(0.05)));
            message.append("Every Friday,Saturday and Sunday our price grow up by 5%!\n");
        }
        if(month==7&&dom==24){
            message.append("Today is 24 August! Just today 50% discount!\n");
            price=price.multiply(new BigDecimal("0.5"));
        }
        else if(month==0&&doy==7){
            message.append("Merry Christmas!!! Just today 50% discount!\n");
            price=price.multiply(new BigDecimal("0.5"));
        }
        else if (doy==256){
            message.append("Programmer's Day!!!Uhooooooo =) Just today 50% discount!\n");
            price=price.multiply(new BigDecimal("0.5"));
        }
        else {
            if (user.getDiscount_code() == true) {
                price = price.multiply(new BigDecimal("0.8"));
                message.append("You have discount cod, so your discount is 10%. And it is :" + price.multiply(new BigDecimal("0.2")).setScale(2, BigDecimal.ROUND_HALF_UP) + " grn.\n");
            }
        }
    }

    public void getTipping(){
        if (month==8&&weekOfMonth==1&&dow==7){
            message.append("Today without tipping. It is first week of September!");
        }else {
            message.append("Thank you for the tip. Tip: "+ price.multiply(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_HALF_UP)+" grn\n");
            price=price.add(price.multiply(new BigDecimal("0.05")));
        }
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
