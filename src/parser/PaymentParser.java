package parser;

import payment.Payment;
import stream.Reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * PaymentParser abstract class - mechanism for parsing tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public abstract class PaymentParser {
    private Reader reader;

    /**
     * PaymentParser constructor
     * @param filename path of file
     */
    public PaymentParser(String filename){
        reader = new Reader(filename);
    }

    /**
     * Get payment
     *
     * @return Payment
     * @throws PaymentMalformedException
     */
    public Payment getPayment() throws PaymentMalformedException {
        String line = null;
        try {
            line = reader.readLine();
            if(line != null) {
                //assumption, each line is a string in JSON format
                return parseLine(line);
            }
        }
        catch(ParseException e){
            System.err.print("Parser error of payment");
        }
        throw new PaymentMalformedException(String.format("%s not of valid format ",(line)));
    }

    //implemented by subclass
    protected abstract Payment parseLine(String line) throws PaymentMalformedException, ParseException ;

    /**
     * Has new payment
     *
     * @return true else false
     */
    public boolean hasPayment(){
        return reader.hasNext();
    }

    public void close(){
        reader.close();
    }

    /**
     * Static method to getDate
     * @param value from json
     * @return Calendar
     * @throws ParseException
     */
    public static Date getDate(String value) throws ParseException{
        value = value.trim();
        SimpleDateFormat formatter = new SimpleDateFormat(ParserConstants.DATE_FORMAT);
        return formatter.parse(value.substring(0, 20));
    }

}
