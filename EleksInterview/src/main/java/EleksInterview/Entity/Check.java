package EleksInterview.Entity;

import java.math.BigDecimal;

/**
 * Created by Igoruch on 18.10.2016.
 */
public class Check {

   private BigDecimal score;
   private StringBuffer message;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public StringBuffer getMessage() {
        return message;
    }

    public void setMessage(StringBuffer message) {
        this.message = message;
    }
}
