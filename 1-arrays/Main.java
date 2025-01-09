class Main {
    public static void main(String[] args) {
//        Array arr = new Array(5);
//        arr.insertMiddle(0, 1);
//        arr.insertMiddle(1, 2);
//        arr.insertMiddle(2, 3);
//        arr.insertMiddle(3, 10);
//        arr.insertMiddle(4, 99);
//        arr.insertMiddle(5, 100);
//        arr.insertBegin(10);
//        arr.insertEnd(1000);
//
//        System.out.println(arr.display());
//        arr.removeFirst();
//        System.out.println(arr.display());
////        arr.removeEnd();
////        arr.display();
////        arr.remove(5);
////        arr.display();
////        System.out.println(arr.search(99));

//        SingleLinkedList list = new SingleLinkedList();
//        list.insertStart(1);
//        list.insertStart(45);
//        list.insertStart(18);
//        list.insertEnd(100);
//        list.insertEnd(67);
//        list.print();
//        list.insertAt(2, 0);
//        list.print();
//        list.removeStart();
//        list.removeEnd();
//        list.print();
//        list.removeAt(0);
//        list.removeAt(2);
//        list.print();
//        System.out.println(list.search(0)
//        DoublyLinkedList list = new DoublyLinkedList();
//        list.insertStart(100);
//        list.insertStart(15);
//        list.insertStart(0);
//        list.insertEnd(9);
//        list.insertEnd(81);
//        list.insertAt(2, 1000);
//        list.print();
////        list.removeStart();
////        list.print();
////        list.removeEnd();
////        list.removeAt(4);
////        list.removeAt(3);
////        list.removeAt(2);
////        list.removeAt(1);
////        list.removeAt(0);
////        list.removeAt(0);
//        System.out.println(list.search(81));

        CircularLinkedList list = new CircularLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.print();

//        list.removeStart();
//        list.removeStart();
//        list.removeStart();
        list.remove(3);
        list.remove(1);
        list.remove(2);

//        System.out.println(list.search(22));
        list.print();
    }
}