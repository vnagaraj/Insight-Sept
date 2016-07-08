package graph;

import java.util.HashMap;
/**
 * MedianFinder class -To compute median of the nodes of the graph
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */

public class MedianFinder {

    private MaxMinPQ<Integer> minPQ;
    private MaxMinPQ<Integer> maxPQ;
    private VenmoGraph graph;

    public MedianFinder(VenmoGraph graph){
        minPQ = new MaxMinPQ<Integer>(false);
        maxPQ = new MaxMinPQ<Integer>(true);
        this.graph = graph;
    }

    /**
     * Get the median of the graph
     *
     * @return  the median
     */
    public double median(){
        addDegree(graph);
        return findMedian();
    }

    /**
     * add the degrees of all nodes to the Median Finder
     *
     * @param graph
     */
    private void addDegree(VenmoGraph graph){
        HashMap<String, Integer> map = graph.getActorDegreeMap();
        for (String key : map.keySet()) {
            addDegrees(map.get(key));
        }
    }

    /**
     * add the degree to the MedianFinder
     *
     * @param degree
     */
    private void addDegrees(int degree) {
        if (minPQ.getSize() == 0){
            minPQ.insert(degree);
            return;
        }
        if (maxPQ.getSize() == 0){
            int val = minPQ.getMaxMin();
            if (degree < val){
                //smaller value should go in maxPQ
                maxPQ.insert(degree);
            }
            else{
                //degree > val
                minPQ.deleteMaxMin();
                minPQ.insert(degree);
                maxPQ.insert(val);

            }
            return;
        }
        int max = minPQ.getMaxMin();
        if (degree > max){
            minPQ.insert(degree);
        }
        else{
            maxPQ.insert(degree);
        }
        resizeHeaps();

    }

    /**
     * Resize the heaps
     */
    private void resizeHeaps(){
        //resize incase difference in heaps size is greater than 1
        int size1 = minPQ.getSize();
        int size2 = maxPQ.getSize();
        if (size1 > size2 + 1){
            int val = minPQ.deleteMaxMin();
            maxPQ.insert(val);
        }
        else if (size2 > size1 + 1){
            int val = maxPQ.deleteMaxMin();
            minPQ.insert(val);
        }
    }

    /**
     * Helper to find median after degrees of nodes have been in MedianFinder
     * @return
     */
    private double findMedian() {
        int size1 = minPQ.getSize();
        int size2 = maxPQ.getSize();
        if (size1 == size2){
            return ((minPQ.getMaxMin()+ maxPQ.getMaxMin())/2.0);
        }
        else if (size1 > size2){
            return minPQ.getMaxMin();
        }
        else{
            return maxPQ.getMaxMin();
        }
    }
}
