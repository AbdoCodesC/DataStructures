public class DoublyCircularLinkedList {
    private int count;
    private class Node {
        int data;
        Node next;
        Node prev;
        public Node(int data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
}
