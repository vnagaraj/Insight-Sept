package unittest;

import graph.MedianFinder;
import graph.VenmoGraph;
import org.junit.Test;
import parser.PaymentParser;
import payment.Payment;
import validation.ValidateTimeStampPayment;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

/**
 * VenmoGraphTest class -Junit tests to validate the timestamp of payments to be sent to graph for processing
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class VenmoGraphTest {

    @Test
    public void validateVenmoGraph()
    {

        VenmoGraph graph = new VenmoGraph();
        ValidateTimeStampPayment valTimeStamp = new ValidateTimeStampPayment();
        double median = 0.00;
        try {
            median = median("Jordan-Gruber", "Jamie-Korn","2016-04-07T03:33:19Z", graph, valTimeStamp);
            assertTrue(median == 1.00);
            median= median("Maryann-Berry", "Jamie-Korn", "2016-04-07T03:33:19Z",  graph, valTimeStamp);
            assertTrue(median == 1.00);
            median= median("Ying-Mo", "Maryann-Berry", "2016-04-07T03:33:19Z",  graph, valTimeStamp);
            assertTrue(median == 1.50);
            median= median("Jamie-Korn", "Ying-Mo", "2016-04-07T03:34:18Z",  graph, valTimeStamp);
            assertTrue(median == 2.00);
            median= median("Maryann-Berry", "Maddie-Franklin", "2016-04-07T03:34:58Z",  graph, valTimeStamp);
            assertTrue(median == 1.00);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private double median(String actor, String target, String timeStamp, VenmoGraph graph,
                          ValidateTimeStampPayment valTimeStamp) throws ParseException{
        Payment payment = new Payment(actor, PaymentParser.getDate(timeStamp).getTime(), target);
        payment = valTimeStamp.validateTimeStamp(payment);
        if (payment != null){
            Payment[] removePayments = valTimeStamp.removePaymentsFromGraph();
            graph.updateEdges(payment, removePayments);
        }
        return new MedianFinder(graph).median();
    }
}
