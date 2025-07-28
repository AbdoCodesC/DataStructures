/*
    enqueue O(1)
    dequeue O(1)
    peek O(1)
    isEmpty O(1)
    isFull O(1)
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class CircularDeque {
    private final int [] queue;
    private int FRONT = 0;
    private int REAR = 0;
    private int count = 0;

    public CircularDeque (int size){
        this.queue = new int [size];
    }

    public void enqueue(int data){
        if (isFull()){
            throw new StackOverflowError();
        }
        queue[REAR] = data;
        REAR = (REAR+1) % queue.length;
        count++;
    }

    public int dequeue () {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = queue[FRONT];
        queue[FRONT] = 0;
        FRONT = (FRONT+1) % queue.length;
        count--;
        return data;
    }

    public int peek(){
        return queue[FRONT];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull () {
        return count == queue.length;
    }

    @Override
    public String toString(){
        return Arrays.toString(queue);
    }
}
