/*
    Lookup O(n)
    Insert O(1)
    Remove O(1)

    node(head) -> node -> node -> node(tail) -> null
         v          v       v         v
 */

import java.util.NoSuchElementException;

public class SingleLinkedList {
    private int count;
    private Node head;
    private Node tail;

    public SingleLinkedList() {
        this.count = 0;
    }

    private class Node {
        Node next;
        int data;
        public Node (int data) {
            this.data = data;
            this.next = null;
        }
        public Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void insertStart (int data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            count++;
            return;
        }
        node.next = head;
        head = node;
        count++;
    }

    public void insertEnd (int data) {
        if (head == null) {
            insertStart(data);
            return;
        }
        Node node = new Node(data);
        tail.next = node;
        tail = node;
        count++;
    }

    public void insertAt (int index, int data) {
        if (index < 0 || index > count) throw new IndexOutOfBoundsException();
        if (index == 0) {
            insertStart(data);
            return;
        }
        if (index == count) {
            insertEnd(data);
            return;
        }
        Node curr = head;
        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }
        // BOTH WAYS WORK
//        Node node = new Node(data);
//        node.next = curr.next;
//        curr.next = node;
        curr.next = new Node(data, curr.next);
        count++;
    }

    public void print(){
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data+">");
            curr = curr.next;
        }
        System.out.println("NULL");
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
        count--;
    }

    public void removeEnd(){
        if (head == null || head == tail) {
            removeStart();
            return;
        }
        Node curr = head;
        while (curr.next != tail) {
           curr = curr.next;
        }
        curr.next = null;
        tail = curr;
        count--;
    }

    public void removeAt(int index) {
        if (index < 0 || index > count) throw new IndexOutOfBoundsException();
        if (index == 0) {
          removeStart();
          return;
        }
        if (index == count) {
            removeEnd();
            return;
        }
        Node curr = head;
        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        count--;
    }

    public int search (int data) {
        int i = 0;
        Node curr = head;
        while (curr != null) {
            if (curr.data == data) {
                return i;
            }
            i++;
            curr = curr.next;
        }
        return -1;
    }
}
