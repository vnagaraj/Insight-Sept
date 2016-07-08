package unittest;

import org.junit.Test;
import parser.JsonPaymentParser;
import parser.PaymentMalformedException;
import parser.PaymentParser;
import payment.Payment;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * PaymentParserTest class -Junit tests to validate the parsing of tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class PaymentParserTest {

    private static String FILE = null;

    @Test
    public void validatePayment()
    {
        FILE = UnitTestConstants.CURRENT_DIR + UnitTestConstants.UNITTEST_PATH + UnitTestConstants.PAYMENTS_PARSER_INPUT_FILE;
        PaymentParser parser = new JsonPaymentParser(FILE);
        ArrayList<Payment> payments = PaymentParserTest.getPayments(parser);
        //verifying paymentCount
        assertTrue(payments.size() == 2);
        Payment payment1 = payments.get(0);
        Payment payment2 = payments.get(1);
        long seconds = (payment2.getTimeStamp() - payment1.getTimeStamp())/1000;
        //verifying timeStamps from payments
        assertTrue(seconds == 40);
        //verifying actor from payments
        String expectedActor1 = "Jordan-Gruber";
        String expectedActor2 = "Maryann-Berry";
        assertTrue(expectedActor1.equals(payment1.getActor()));
        assertTrue(expectedActor2.equals(payment2.getActor()));
        //verifying targets from payments
        String expectedTarget1 = "Jamie-Korn";
        String expectedTarget2 = "Ying-Mo";
        assertTrue(expectedTarget1.equals(payment1.getTarget()));
        assertTrue(expectedTarget2.equals(payment2.getTarget()));
    }

    /**
     * Get list of tweets using the PaymentParser
     * @param paymentParser
     * @return list of payments
     */
    private static  ArrayList<Payment> getPayments(PaymentParser paymentParser){
        ArrayList<Payment> payments = new ArrayList<Payment>();
        while (paymentParser.hasPayment()){
            Payment payment = null;
            try {
                payment = paymentParser.getPayment();
            } catch (PaymentMalformedException e) {
                e.printStackTrace();
            }
            //System.out.println(payment);
            payments.add(payment);
        }
        return payments;
    }

}
