/*
    insert O(log n)
    delete O(log n)
 */

public class HeapPriorityQueue {
    private final Heap heap = new Heap();

    public void enqueue (int data) {
        heap.insert(data);
    }

    public int dequeue () {
        return heap.remove();
    }

    public boolean isEmpty () {
        return heap.isEmpty();
    }
}
