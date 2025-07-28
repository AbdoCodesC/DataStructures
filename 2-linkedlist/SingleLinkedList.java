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

    private static class Node {
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

    public int getSize(){
        return count;
    }

    public void insertFirst (int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        
        if (tail == null) {
           tail = node;
        }
        
        count++;
    }

    // do not use: func insertLast will override
    public void insertLastWNoTail (int data) {
        if (head == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
        System.out.println(curr.data);
        count++;
    }

    public void insertLast (int data) {
        if (head == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        tail.next = node;
        tail = node;
        count++;
    }

    public void insert (int index, int data) {
        if (index < 0 || index > count) throw new IndexOutOfBoundsException();
        if (index == 0) {
            insertFirst(data);
            return;
        }
        if (index == count) {
            insertLast(data);
            return;
        }

        Node curr = head;
        for (int i = 1; i < index; i++) { // we use 1 since we alr have curr as head!
            curr = curr.next;
        }
        // BOTH WAYS WORK
//        Node node = new Node(data);
//        node.next = curr.next;
//        curr.next = node;
        curr.next = new Node(data, curr.next);
        count++;
    }
    /// /////////////////////////////////////////////////////////
    public void print(){
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data+" > ");
            curr = curr.next;
        }
        System.out.println("NULL");
    }
    /// /////////////////////////////////////////////////////////
    public void removeFirst () {
        if (head == null) throw new NoSuchElementException();

        if (head == tail) head = tail = null;
        else head = head.next;

        count--;
    }

    public void removeLast(){
        if (head == null || head == tail) {
            removeFirst();
            return;
        }

//        Node curr = head;
//        while (curr.next != tail) curr = curr.next;
        tail = get(count-2); // -2 to get the one before the last node
        tail.next = null;
        count--;
    }

    public void removeAt(int index) {
        if (index < 0 || index > count) throw new IndexOutOfBoundsException();
        if (index == 0) {
          removeFirst();
          return;
        }
        if (index == count) {
            removeLast();
            return;
        }
//        Node curr = head;
//        for (int i = 1; i < index; i++) {
//            curr = curr.next;
//        }
        Node del = get(index-1);
        del.next = del.next.next;

        count--;
    }
    /// ///////////////////////////////////////////////////////////////////
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

    private Node get (int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(6);
        list.insert(1, 100);
        list.insertLast(10);
        list.insertLast(18);
        list.print();
        list.removeAt(4);
        list.print();

    }
}
