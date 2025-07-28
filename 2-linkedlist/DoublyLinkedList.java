/*
    search {middle} O(n)
    insert {start, end} becomes O(1)
    remove {start, end} O(1)
 */

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DoublyLinkedList {
    private int count;
    private Node head;
    private Node tail;
    private static class Node {
        Node next;
        Node prev;
        int data;
        public Node (int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        public Node (Node prev, int data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    /////////////--insert--///////////
    public void insertStart (int data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            node.prev = null;
            assert head.prev != null;
            head.prev = node;
            head = node;
        }

        count++;
    }

    public void insertEnd (int data) {
        Node node = new Node(data);
        if (head == null) {
            insertStart(data);
            return;
        }
        /* w/ out tail
        Node last = head;
        while (last.next != null) last = last.next;
        node.next = null;
        last.next = node;
        node.prev = last;
        */
        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
        count++;
    }

    public void insertAt (int index, int data) {
        if (index < 0 || index > count) throw new IndexOutOfBoundsException();
        if (head == null || index == 0) {
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
        Node node = new Node(data);
        node.next = curr.next;
        node.prev = curr;
        curr.next.prev = node;
        curr.next = node;

        // BOTH WAYS WORK!
//        Node node = new Node(curr, data, curr.next);
//        curr.next.prev = node;
//        curr.next = node;
        count++;
    }

    public void print() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1 for normal, else for reversed.");
        int i = scan.nextInt();
        if (i == 1) {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data+" > ");
                curr = curr.next;
            }
        } else {
            Node curr = tail;
            while (curr != null) {
                System.out.print(curr.data+" < ");
                curr = curr.prev;
            }
        }
    }
    //////////--remove--//////////////
    public void removeStart(){
        if (head == null) throw new NoSuchElementException();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        count--;
    }

    public void removeEnd(){
        if (head == null || head == tail) {
            removeStart();
            return;
        }
        tail = tail.prev;
        tail.next = null;
        count--;
    }

    public void removeAt(int index) {
        if (index == 0 || head == null) {
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
        curr.next.next.prev = curr;
        curr.next = curr.next.next;
        count--;
    }
    /// /////////////////////
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

    public static void main(String[] args) {
        DoublyLinkedList ll = new DoublyLinkedList();
        ll.insertStart(10);
        ll.insertStart(1);
        ll.insertStart(1100);
        ll.insertAt(2, 99);
        ll.insertEnd(87);
        ll.print();

    }


}
