/*
    K -> V
    Lookup O(1)
    Remove O(1)
    Insert O(1)
    // Collision: CHAINING using LL
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

    private int hash(int key){
        return Math.abs(key) % map.length;
    }

    public void put (int key, String value) {
        int index = hash(key);
        if (map[index] == null) map[index] = new LinkedList<>();

        var bucket = map[index];
        for (var entry: bucket) {
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
