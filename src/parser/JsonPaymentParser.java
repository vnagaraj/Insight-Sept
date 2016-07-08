package parser;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import payment.Payment;

import java.text.ParseException;
import java.util.Date;



/**
 * JsonParser class - uses (../lib/gson-2.6.2.jar)library for implementation of the PaymentParser
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class JsonPaymentParser extends PaymentParser {

    /**
     * Constructor for JsonPaymentParser- calls parent constructor
     *
     * @param filename path of file
     */
    public JsonPaymentParser(String filename){
        super(filename);
    }

    /**
     * JsonParser to convert the json document to a map,
     * creating a Payment instance
     * @param line of the text file
     * @return Payment
     * @throws ParseException
     * @throws PaymentMalformedException
     */
    protected Payment parseLine(String line) throws ParseException, PaymentMalformedException {
        if (line == null || line.trim().isEmpty()){
            throw new PaymentMalformedException("Payment is empty");
        }
        JsonElement root = new JsonParser().parse(line);
        if (root == null){
            throw new PaymentMalformedException("Payment is malformed");
        }
        JsonObject jsonObject = root.getAsJsonObject();
        Date date= getDate(jsonObject);
        if (date == null){
            throw new PaymentMalformedException("Not able to extract timestamp from payment");
        }
        String actor = getActor(jsonObject);
        if (actor == null){
            throw new PaymentMalformedException("Not able to extract actor from payment");
        }
        String target = getTarget(jsonObject);
        if (target == null){
            throw new PaymentMalformedException("Not able to extract actor from payment");
        }
        return new Payment(actor, date.getTime(), target);
    }

    /**
     * Get date object associated with "created_at" from the json Payment
     *
     * @param  jsonObject instance
     * @return Date instance
     * @throws ParseException
     */
    private Date getDate(JsonObject jsonObject) throws ParseException{
        String value = jsonObject.get(ParserConstants.TIMESTAMP_KEY).getAsString();
        return PaymentParser.getDate(value);
    }

    /**
     * Get actor attribute from the Payment
     *
     * @param  jsonObject instance
     * @return long
     */
    private String getActor(JsonObject jsonObject){
        return jsonObject.get(ParserConstants.ACTOR_KEY).getAsString();
    }

    /**
     * Get target attribute from the Payment
     *
     * @param  jsonObject instance
     * @return list of hashtags
     */
    private String getTarget(JsonObject jsonObject){
        return jsonObject.get(ParserConstants.TARGET_KEY).getAsString();
    }
}
