/*
 Min Heap
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class HeapII {

    private final int [] heap;
    private int count = 0;

    public HeapII(int size) {
        this.heap = new int[size];
    }

    public HeapII () {
        this.heap = new int[10];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void insert(int data) {
        if (isFull()) throw new StackOverflowError();
        heap[count] = data;
        bubbleUp(count);  // Pass the index
        count++;
    }

    public int remove() {
        if (isEmpty()) throw new EmptyStackException();
        int root = heap[0];
        heap[0] = heap[count - 1];
        count--;
        if (count > 0) {
            bubbleDown(0);  // Pass the index
        }
        return root;
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (heap[index] > heap[parentIndex]) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int smallest = index;
            int left = leftChild(index);
            int right = rightChild(index);

            if (left < count && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < count && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == heap.length;
    }

    public int [] heapSort(int[] nums) {
        int [] sorted = new int[nums.length];

        for (int num : nums) {
            insert(num);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            sorted[i] = remove();
        }

        return sorted;
    }

    public int min () {
        if (isEmpty()) throw new EmptyStackException();
        return heap[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 0, count));
    }
}
