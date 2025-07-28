/*
    pop O(1)
    push O(1)
    peek O(1)
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private final int [] stack ;
    private int count = 0;

    public Stack (int size) {
        this.stack = new int [size];
    }

    public void push (int data) throws Exception {
        if (count == stack.length){
            throw new StackOverflowError();
        }
        stack[count++] = data;
    }

    public int pop () {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[--count];
    }

    public int peek () {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[count-1];
    }

    @Override
    public String toString(){
        return Arrays.toString(Arrays.copyOfRange(stack, 0, count));
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);

    }
}
