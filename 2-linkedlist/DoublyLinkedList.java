/*

 */

import java.util.Scanner;

public class DoublyLinkedList {
    private int count;
    private Node head;
    private Node tail;
    private class Node {
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

    public void insertStart (int data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            count++;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
        count++;
    }

    public void insertEnd (int data) {
        Node node = new Node(data);
        if (head == null) {
            insertStart(data);
            return;
        }
        tail.next = node;
        node.prev = tail;
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
        count++;
    }

    public void print(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1 for normal, else for reversed.");
        int i = scan.nextInt();
        if (i == 1) {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data+">");
                curr = curr.next;
            }
        } else {
            Node curr = tail;
            while (curr != null) {
                System.out.print(curr.data+"<");
                curr = curr.prev;
            }
        }
        System.out.println("NULL");
    }


}
