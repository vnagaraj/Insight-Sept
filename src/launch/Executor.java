package launch;

import graph.MedianFinder;
import graph.VenmoGraph;
import parser.PaymentParser;
import parser.JsonPaymentParser;
import parser.PaymentMalformedException;
import stream.Writer;
import payment.Payment;
import validation.ValidateTimeStampPayment;

/**
 * Executor class - class which invokes others classes to execute the program
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class Executor {

    private static Executor instance = null;

    /**
     * Get instance of Executor
     * Uses singleton design pattern (only 1 instance of this class is required)
     * @param inputFilePath path of file comprising of json tweets
     * @param outputFilePath path to outfile for computing rolling average
     * @return instance of the Executor
     */
    public static Executor getInstance(String inputFilePath, String outputFilePath) {
        if(instance == null) {
            instance = new Executor(inputFilePath, outputFilePath);
        }
        return instance;
    }

    /**
     * Private constructor for Executor
     * @param inputFilePath path of file comprising of json tweets
     * @param outputFilePath path to outfile for computing rolling average
     */
    private Executor(String inputFilePath, String outputFilePath){
        execute(inputFilePath, outputFilePath);
    }

    /**
     * Method which computes the rolling median and writes to file path
     * specified in outPutFilePath
     *
     * @param inputFilePath path of file comprising of json tweets
     * @param outPutFilePath outputFilePath path to outfile for computing rolling average
     */
    private void execute(String inputFilePath, String outPutFilePath){
        PaymentParser parser = new JsonPaymentParser(inputFilePath);
        VenmoGraph graph = new VenmoGraph();
        ValidateTimeStampPayment valPayment = new ValidateTimeStampPayment();
        double rollingMedian = 0;
        while (parser.hasPayment()){
            try {
                Payment payment = parser.getPayment();
                //System.out.println(payment);
                payment = valPayment.validateTimeStamp(payment);
                if (payment != null){
                    //payment within 60 sec time gap, send it to graph for processing
                    Payment[] removePayments = valPayment.removePaymentsFromGraph();
                    graph.updateEdges(payment, removePayments);
                    rollingMedian = new MedianFinder(graph).median();
                    //System.out.println(outPutFilePath);
                }
                new Writer(outPutFilePath).write(rollingMedian);
            }
            catch(PaymentMalformedException e){
                //ignoring tweet
                //System.err.print("Malformed tweet");
            }
        }
        parser.close();
    }

    /**
     * Method which launches the Java application
     * Requires 2 parameters at command line
     *
     * @param args inputFilePath, outPutFilePath
     */
    public static void main(String[] args){
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        Executor.getInstance(inputFilePath, outputFilePath);

    }
}
