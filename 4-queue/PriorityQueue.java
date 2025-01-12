/*
    using array, not binary tree
    Insert O(n)
    Remove O(1)
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class PriorityQueue {
    private final int [] queue;
    private int count = 0;
    public PriorityQueue (int size) {
        this.queue = new int [size];
    }

    public void insert (int data) { // O(n)
        if (count == queue.length) throw new StackOverflowError();

        // shift items
        int i;
        for (i = count-1; i >= 0; i--) {
            if (data < queue[i]) {
                queue[i+1] = queue[i];
            } else {
                break;
            }
        }

        queue[i+1] = data;
        count++;
    }

    public int remove () {
        if (isEmpty()) throw new EmptyStackException();
        return queue[--count];
    }

    public boolean isEmpty () {
        return count == 0;
    }

    @Override
    public String toString(){
        return Arrays.toString(Arrays.copyOfRange(queue, 0, count));
    }


}
