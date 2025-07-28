import java.util.LinkedList;

class HashSet {
    private static final int SIZE = 1000; // Default bucket size
    private LinkedList <Integer> [] buckets;

    public HashSet() {
        buckets = new LinkedList [SIZE]; // Create an array of linked lists
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>(); // Initialize each bucket
        }
    }

    private int hash(int key) {
        return key % SIZE; // Simple hash function
    }

    public void add(int key) { // if does not contain it alr, add it
        int index = hash(key);
        if (!buckets[index].contains(key)) {
            buckets[index].add(key);
        }
    }

    public void remove(int key) {
        int index = hash(key);
        buckets[index].remove((Integer) key);
    }

    public boolean contains(int key) {
        int index = hash(key);
        return buckets[index].contains(key);
    }

    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add(10);
        set.add(20);
        set.add(1001); // Collides with 10 (since 1001 % 1000 == 1)

        System.out.println(set.contains(10));  // true
        System.out.println(set.contains(20));  // true
        System.out.println(set.contains(1001)); // true
        System.out.println(set.contains(30));  // false

        set.remove(10);
        System.out.println(set.contains(10));  // false
    }
}
