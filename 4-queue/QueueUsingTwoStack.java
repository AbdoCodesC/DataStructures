import java.util.EmptyStackException;
import java.util.Stack;

public class QueueUsingTwoStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void enqueue (int data) { // 1 2 3
        stack1.push(data);
    }

    public int dequeue () {
        if (stack1.isEmpty()) {
            throw new EmptyStackException();
        }

        if (stack2.empty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek () {
        if (stack2.empty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
}
