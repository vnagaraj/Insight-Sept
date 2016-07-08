package unittest;

import org.junit.Test;
import parser.PaymentParser;
import payment.Payment;
import validation.ValidateTimeStampPayment;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * ValidatePaymentTest class -Junit tests to validate the timestamp of payments to be sent to graph for processing
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class ValidateTimeStampPaymentTest {

    @Test
    public void validateTimeStampPaymentTest()
    {

        ValidateTimeStampPayment validatePayment = new ValidateTimeStampPayment();;
        try {
            //create 3 payments with timestamp1
            //create  1 payment with timestamp2
            //timestamp1 and timestamp2 in 60 second range
            //create 1 payment with timestamp3 with timestamp3-timestamp1 >60 seconds
            //causes 3 payments to be evicted
            Payment payment1 = validatePaymentData(UnitTestConstants.TIME_STAMP_1, validatePayment);
            assertTrue(payment1 != null);
            assertTrue(Arrays.equals(validatePayment.removePaymentsFromGraph(),new Payment[0]));
            Payment payment2 = validatePaymentData(UnitTestConstants.TIME_STAMP_1, validatePayment);
            assertTrue(payment2 != null);
            assertTrue(Arrays.equals(validatePayment.removePaymentsFromGraph(),new Payment[0]));
            Payment payment3 = validatePaymentData(UnitTestConstants.TIME_STAMP_1, validatePayment);
            assertTrue(payment3 != null);
            assertTrue(Arrays.equals(validatePayment.removePaymentsFromGraph(),new Payment[0]));
            Payment payment4 = validatePaymentData(UnitTestConstants.TIME_STAMP_2, validatePayment);
            assertTrue(payment4 != null);
            assertTrue(Arrays.equals(validatePayment.removePaymentsFromGraph(),new Payment[0]));
            Payment payment5 = validatePaymentData(UnitTestConstants.TIME_STAMP_3, validatePayment);
            assertTrue(payment5 != null);
            //timestamp3 causing  payments with timestamp1 to be evicted since it falls outside 60 sec window
            Payment[] removalPaymentList = validatePayment.removePaymentsFromGraph();
            assertTrue(removalPaymentList.length == 3);
            assertTrue(contains(removalPaymentList, payment1));
            assertTrue(contains(removalPaymentList, payment2));
            assertTrue(contains(removalPaymentList, payment3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Payment validatePaymentData(String timeStamp, ValidateTimeStampPayment validatePayment) throws ParseException{
        Payment payment = new Payment(null, PaymentParser.getDate(timeStamp).getTime(),null);
        return validatePayment.validateTimeStamp(payment);
    }

    private boolean contains(Payment[] payments, Payment payment){
        for (Payment pay: payments){
            if (pay == payment){
                return true;
            }
        }
        return false;
    }
}
