/*
    H <- N <- N <- N <- T
    InsertEnd O(1)
    RemoveStart O(1)
 */

import java.util.NoSuchElementException;

public class LinkedListQueue {
    private Node head;
    private Node tail;

    private static class Node {
        Node next;
        int data;
        public Node (int data) {
            this.data = data;
            this.next = null;
        }
    }
    public void insertEnd (int data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void removeStart () {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
            return;
        }
        head = head.next;
    }

    public void print(){
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data+"<");
            curr = curr.next;
        }
        System.out.println();
    }
}

