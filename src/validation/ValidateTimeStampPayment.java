package validation;

import payment.Payment;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * ValidateTimeStampPayment class -validates the given payment to check if it falls in the designated window
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class ValidateTimeStampPayment {
    private long maxTimeStamp;
    private PriorityQueue<Payment> minPQ ;
    private boolean checkPriorityQueue;
    private static final long WINDOW_GAP = 60000;
    private ArrayList<Payment> removePayments;

    /**
     * ValidateTimeStampPayment constructor - initializes the priority queue and list of payments to be removed
     */
    public ValidateTimeStampPayment(){
        minPQ = new PriorityQueue<Payment>();
        removePayments = new ArrayList<Payment>();
    }

    /**
     * Validates time stamp of payment
     * @param payment to validate
     * @return payment if time stamp falls within the 60 second window
     */
    public Payment validateTimeStamp(Payment payment){
        //check timestamp
        updateMaxTimestamp(payment);
        if (!exceededWindow(payment)){
            //check 60 second window
            checkPriorityQueue();
            minPQ.add(payment);
            return payment;
         }
         return null;
    }

    /**
     * list of Payments to be removed from graph,
     * list generated through invocation of validateTimeStamp
     * @return Payment array
     */
    public Payment[] removePaymentsFromGraph(){
        Payment[] removePaymentsArr = new Payment[removePayments.size()];
        removePaymentsArr = removePayments.toArray(removePaymentsArr);
        removePayments = new ArrayList<Payment>();
        return removePaymentsArr;
    }

    /**
     * Check if payment has exceeded 60 second window
     * @param payment instance
     * @return true if exceeded
     */
    private boolean exceededWindow(Payment payment){
        long milliSecondDifference = maxTimeStamp - payment.getTimeStamp();
        if (milliSecondDifference > WINDOW_GAP){
            return true;
        }
        return false;
    }

    /**
     * Update max timestamp of all payments seen so far
     * @param payment instance
     */
    private void updateMaxTimestamp(Payment payment){
        long currentTimestamp = payment.getTimeStamp();
        if (currentTimestamp > maxTimeStamp){
            maxTimeStamp = currentTimestamp;
            checkPriorityQueue = true;
        }
    }

    /**
     * Check priority queue - called when max timestamp is updated
     */
    private void checkPriorityQueue(){
        if (checkPriorityQueue){
            Payment payment = minPQ.peek();
            while (payment != null){
                if (this.exceededWindow(payment)){
                    //remove payment from heap
                    minPQ.remove(payment);
                    removePayments.add(payment);
                    payment = minPQ.peek();
                }
                else{
                    break;
                }
            }
        }
        checkPriorityQueue = false;
    }
}
