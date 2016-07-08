package parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ParserConstants class - constants to be used for the PaymentParser
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class ParserConstants {
    public static final Charset ENCODING = StandardCharsets.UTF_8;
    //only package level visibility
    static final String TIMESTAMP_KEY = "created_time";
    static final String ACTOR_KEY = "actor";
    static final String TARGET_KEY = "target";
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
}
