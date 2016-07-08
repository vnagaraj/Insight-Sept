package parser;

/**
 * PaymentMalformedException  class- for malformed tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class PaymentMalformedException extends Exception
{
    public PaymentMalformedException(String s)
    {
        super(String.format("Payment not of valid format"));
    }
}
