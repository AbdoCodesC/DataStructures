/*
    K -> V
    Lookup O(1)
    Remove O(1)
    Insert O(1)
    // Collision FIX: CHAINING using LL

    ::HashMap
    "
        A map (k):(v)
        A HashMap is a data structure that stores key-value pairs using a hash function. Think of it like a dictionary where you can quickly look up values using their associated keys. Here's a breakdown:
        Key Concepts:
            Key-Value Pairs:
                Each element consists of:
                    A unique key (like a word in a dictionary)
                    A value associated with that key (like the word's definition)
        Hash Function:
            Converts the key into a numeric value (hash code)
            Determines where to store the key-value pair in memory
            Should distribute values uniformly to minimize collisions
        Time Complexity:
            Average case: O(1) for insert, delete, and lookup operations
            Worst case: O(n) if there are many collisions

        Handling Collisions:
            When two keys hash to the same location, there are two main strategies:
                Chaining: Store multiple values at the same location using a linked list
                Open Addressing: Find the next available empty slot in the array

        we use a linked list to hold values

        private LinkedList<Entry>[] map = new LinkedList[size]; 
    "
 */

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashMap {
    private class Entry {
        int key;
        String value;
        public Entry (int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] map;
    public HashMap (int size) {
        this.map = new LinkedList[size];
    }

    public int hash(String key) { // using a string
        int hash = 0;
        for (char i: key.toCharArray()) {
            hash += i;
        }
        return Math.abs(hash) % map.length;
    }

    private int hash(int key){ // using a int key
        return Math.abs(key) % map.length;
    }

    public void put (int key, String value) {
        int index = hash(key);
        if (map[index] == null) map[index] = new LinkedList<>();

        LinkedList<Entry> bucket = map[index];
        
        for (var entry: bucket) { // check if needs to be overided
            if (entry.key == key) {
                entry.value = value;
                System.out.println("NEW");
                return;
            }
        }
        
        bucket.addLast(new Entry(key, value));
    }

    public String get (int key) {
        int index = hash(key);
        var bucket = map[index];
        if (bucket != null) {
            for (var entry : bucket) {
                if (entry.key == key) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove (int key) {
        int index = hash(key);
        var bucket = map[index];
        if (bucket == null) throw new NoSuchElementException();

        for (var entry : bucket) {
            if (entry.key == key) {
                bucket.remove(entry);
                return;
            }
        }

        throw new NoSuchElementException();
    }
}
