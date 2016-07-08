package graph;

import payment.Payment;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * VenmoGraph class - builds a graph comprising of actors from incoming payments and computes average degree
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class VenmoGraph {

    private HashMap<String, LinkedHashMap<String,Integer>> actorEdgesMap; //adj list representation of graph mapping actor to a linklist of edges and  and the no of connections per edge
    private HashMap<String, Integer> actorDegreeMap; // map to keep track of degrees per actor


    /**
     * VenmoGraph constructor - initializes actorEdgesMap
     */
    public VenmoGraph(){
        actorEdgesMap = new HashMap<String, LinkedHashMap<String, Integer>>();
        actorDegreeMap = new HashMap<String, Integer>();
    }

    /**
     * Get the actor degree map
     *
     * @return  the actorDegreeMap
     */
    public HashMap<String, Integer> getActorDegreeMap(){
        return actorDegreeMap;
    }

    /**
     * Updated the edges on the graph
     * @param payment add actor,target nodes of the incoming payment to be added to graph
     * @param removePayments remove actor, target nodes of payments to be removed from graph
     */
    public void updateEdges(Payment payment, Payment[] removePayments){
        String actor = payment.getActor();
        String target = payment.getTarget();
        //iterate through list to create edges between the actor and target
        addEdge(actor, target);
        addEdge(target, actor);
        //iterate through tweets to remove edges for discarded tweets
        removeEdges(removePayments);
    }


    /**
     * Adds edge to the graph between nodes actor and target
     * @param actor node of graph
     * @param target node of graph
     */
    private void addEdge(String actor, String target){
        if (!actorEdgesMap.containsKey(actor)){
            //actor node not present
            LinkedHashMap<String,Integer> value = new LinkedHashMap<String, Integer>();
            value.put(target,1);
            actorEdgesMap.put(actor, value);
            actorDegreeMap.put(actor, 1);
        }
        else{
            //actor node present
            LinkedHashMap<String, Integer> value = actorEdgesMap.get(actor);
            //check if target present in linkedlist
            if (!value.containsKey(target)){
                value.put(target, 1);
                actorDegreeMap.put(actor, actorDegreeMap.get(actor) +1);
            }
            //else add count of no of edges , useful for deletion
            else{
                int count = value.get(target);
                value.put(target, count+1);
            }
        }
    }

    /**
     * Remove edges in the graph from the array of payments
     * @param removePayments array of payments
     */
    private void removeEdges(Payment[] removePayments){
        for (Payment payment: removePayments){
            String actor = payment.getActor();
            String target = payment.getTarget();
            removeEdge(actor, target);
            removeEdge(target, actor);
        }
    }

    /**
     * Remove edge between nodes actor and target
     * @param actor node
     * @param target node
     */
    private void removeEdge(String actor, String target){
        if (actorEdgesMap.containsKey(actor)){
            //actor node not present
            //target node present
            LinkedHashMap<String, Integer> value = actorEdgesMap.get(actor);
            //check if target present in linkedlist
            if (value.containsKey(target)){
                int count = value.get(target);
                if (count == 1){
                    //remove edge
                    value.remove(target);
                }
                else{
                    value.put(target, count -1);
                }
                actorDegreeMap.put(actor, actorDegreeMap.get(actor)-1);
                if (value.size() == 0){
                    //delete the node as node has no edges
                    actorEdgesMap.remove(actor);
                    //remove actor
                    actorDegreeMap.remove(actor);
                }
            }
        }
    }

}
