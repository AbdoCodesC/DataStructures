/*
     -> head -> Node -> Node -> Node ->
    |                                  |
     <- tail <- Node <- Node <- Node <-
 */

import java.util.NoSuchElementException;

public class CircularLinkedList {
    private int count;
    private static class Node {
        int data;
        Node next;
        public Node(int data){
            this.data = data;
        }
    }

    private Node head;
    private Node tail;

    public void insertStart (int data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
            tail.next = head;
        }
        count++;
    }

    public void removeStart () {
        if (head == null) throw new NoSuchElementException();
        if (head == tail) {
            head = tail = null;
            count--;
            return;
        }
        head = head.next;
        tail.next = head;
        count--;
    }

    public void remove (int data) {
        if (head == null || head == tail || head.data == data) {
            removeStart();
            return;
        }
        Node curr = head;
        for (int i = 0; i < count; i++) {
            if (curr.next.data == data) {
                break;
            }
            curr = curr.next;
        }
        curr.next = curr.next.next;
        count--;
    }

    public int search (int data) {
        Node curr = head;
        for (int i = 0; i < count; i++) {
            if (curr.data == data) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }

    public void print () {
        Node curr = head;
        for (int i = 0; i < count; i++) {
            System.out.print(curr.data + ">");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insertStart(10);
        cll.insertStart(1);
        cll.insertStart(9);
        cll.print();
    }

}
