package graph;
/**
 * MaxMinPriorityQueue class -Data structure to compute the maxPQ/minPQ
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */

class MaxMinPQ<key extends Comparable<key>>{
    private key[] array;
    private int size ;
    private boolean isMax; //min or max priority queue

    MaxMinPQ(boolean isMax){
        array = (key[])new Comparable[2];
        this.isMax = isMax;
    }

    /**
     * Get size of priority queue
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * Insert key into priority queue
     * @param key
     */
    public void insert(key key){
        if (size + 1 == array.length){// due to 1 based indexing
            resize((size+1) * 2);
        }
        array[++size] = key;
        this.swim(size);
    }

    /**
     * Delete key from priority queue
     * @return
     */
    public key deleteMaxMin(){
        key val = array[1];
        //exchange with last value
        array[1] = array[size];
        array[size--] = null ;// to avoid loitering
        sink(1);
        return val;
    }

    /**
     * Get max value for max priority queue
     min value for min priority queue
     *
     *
     */
    public key getMaxMin(){
        return this.array[1];
    }

    /**
     * sink operation used while deleting element from priority queue
     * @param index
     */
    private void sink(int index){
        int child = 2 * index;
        while (child <= size){
            child = computeMinMaxChild(child);
            if (compare(array[index],array[child])){
                //exchange element with parent
                key temp = array[child];
                array[child] = array[index];
                array[ index] = temp;
            }
            index = child;
            child = 2 * index;
        }
    }

    /**
     * swim operation used while inserting element into priority queue
     * @param index
     */
    private void swim(int index){
        int parent = index/2;
        while (parent >= 1){
            if (compare(array[parent],array[index])){
                //exchange element with parent
                key temp = array[parent];
                array[parent] = array[index];
                array[ index] = temp;
            }
            index = parent;
            parent = index/2;
        }
    }

    /**
     * resize array to given length
     * @param length
     */
    private void resize(int length){
        key[] temp = (key[])new Comparable[length];
        for (int i=0; i < array.length; i++){
            temp[i] = array[i];
        }
        array = temp;
    }

    /**
     * compute the min or max child based on isMax
     * @param child
     * @return
     */
    private int computeMinMaxChild(int child){
        int index = -1;
        if (child + 1 > size){
            return child;
        }
        int child1 = child +1;
        if (this.array[child].compareTo(this.array[child1]) < 0){
            if (this.isMax)
                index = child1;
            else
                index = child;
        }
        else{
            if (this.isMax)
                index = child;
            else
                index = child1;
        }
        return index;
    }



    /*
    Compares the 1st element with 2nd element
    Returns true if 1st element is smaller for max PQ
    Returns false if 1st element is larger for min PQ
     */
    private boolean compare(key element1, key element2){
        if (this.isMax){
            if (element1.compareTo(element2) < 0){
                return true;
            }
        }
        else{
            if (element1.compareTo(element2) > 0){
                return true;
            }
        }
        return false;
    }
}