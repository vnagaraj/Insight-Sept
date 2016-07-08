package payment;


/**
 * Immutable Payment class - constructed from payment json
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public final class Payment implements Comparable<Payment>{
    private final String actor;
    private final long timestamp; //in milliseconds
    private final String target;


    /**
     * Payment constructor
     * @param actor actor field from payment
     * @param timestamp timestamp of payment used for validation
     * @param target target field from payment
     */
    public Payment(String actor, long timestamp, String target){
        this.actor = actor;
        this.timestamp = timestamp;
        this.target = target;
    }

    /**
     * Get the actor
     * @return actor
     */
    public String getActor(){
        return actor;
    }

    /**
     * Get the timestamp of payment in milliseconds
     * @return timestamp
     */
    public long getTimeStamp(){
        return this.timestamp;
    }

    /**
     * Get the target
     * @return target
     */
    public String getTarget(){
        return target;
    }

    /**
     * String representation of the class for debugging
     * @return String
     */
    public String toString(){
        return String.format("actor:%s ,timestamp:%d, target:%s", actor, timestamp, target);
    }

    /**
     * Overriding equals to make sure payments with same timestamp are treated differently when using
     * Java collections
     * @param o other instance
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Payment or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Payment)) {
            return false;
        }

        // typecast o to Payment so that we can compare data members
        Payment c = (Payment) o;
        //compare indiv members
        if (!this.actor.equals(c.actor)){
            return false;
        }
        if (this.timestamp != c.timestamp){
            return false;
        }
        if (!this.target.equals(c.target)){
            return false;
        }
        return true;
    }

    /**
     *
     * @return hashCode value of Payment
     */
    @Override
    public int hashCode(){
        return Long.hashCode(this.timestamp) * this.actor.hashCode() * this.target.hashCode();
    }

    /**
     * Compare payments based on timestamp when used in priority queue
     * @param that other payment
     * @return int based on comparison
     */
    @Override
    public int compareTo(Payment that) {
        if (this.timestamp< that.timestamp){
            return -1;
        }

        if (this.timestamp > that.timestamp){
            return 1;
        }
        return 0;
    }

}
