package unittest;

import org.junit.Test;
import payment.Payment;
import java.util.Calendar;


import static org.junit.Assert.assertTrue;

/**
 * PaymentTest - Junit tests to validate the payment
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class PaymentTest {

    @Test
    public void validatePayment()
    {
        Payment payment1 = new Payment("John", Calendar.getInstance().getTimeInMillis(), "Jack");
        Payment payment2 = new Payment("Jill", Calendar.getInstance().getTimeInMillis(),"James");
        assertTrue(payment1.compareTo(payment2) == -1);
    }

}
