class Main {
    public static void main(String[] args) throws Exception {
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

//        CircularLinkedList list = new CircularLinkedList();
//        list.insert(1);
//        list.insert(2);
//        list.insert(3);
//        list.print();
//
//        list.removeStart();
//        list.removeStart();
//        list.removeStart();
//        list.remove(3);
//        list.remove(1);
//        list.remove(2);

//        System.out.println(list.search(22));
//        list.print();

//        Stack stack = new Stack(5);
//        System.out.println(stack);
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//
//        System.out.println(stack);
//        System.out.println(stack.pop());
//        System.out.println(stack);
//        System.out.println(stack.pop());
//        System.out.println(stack);
//        QueueUsingTwoStack queue = new QueueUsingTwoStack();
//        queue.enqueue(1);
//        queue.enqueue(2);
//        queue.enqueue(3);
//        queue.enqueue(7);
//        queue.enqueue(10);
//        queue.dequeue();

//        PriorityQueue queue = new PriorityQueue(5);
//        queue.insert(10);
//        queue.insert(2);
//        queue.insert(100);
//        queue.insert(-7);
//        queue.insert(3);
//        System.out.println(queue);
//        while (!queue.isEmpty()) {
//            System.out.println(queue.remove());
//        }

//        LinkedListQueue queue = new LinkedListQueue();
//        queue.insertEnd(10);
//        queue.insertEnd(7);
//        queue.insertEnd(180);
//        queue.insertEnd(16);
//        queue.print();
//        queue.removeStart();
//        queue.print();

//        HashMap map = new HashMap(5);
//        map.put(0, "Abdo");
//        map.put(1, "Sara");
//        map.put(1, "Gurr");
//        map.remove(10);
//        System.out.println(map.get(1));

//        BinarySearchTree tree = new BinarySearchTree();
//        tree.insert(7);
//        tree.insert(4);
//        tree.insert(9);
//        tree.insert(1);
//        tree.insert(6);
//        tree.insert(8);
//        tree.insert(10);
//        tree.insert(-3);
//        System.out.println(tree.isBST());
//        tree.swap();
//        System.out.println(tree.isBST());
//            System.out.println(tree.getNodesAtDist(0));
//        System.out.println(tree.levelOrder());

            //        BinarySearchTree tree2 = new BinarySearchTree();
//        tree2.insert(7);
//        tree2.insert(4);
//        tree2.insert(9);
//        tree2.insert(1);
//        tree2.insert(6);
//        tree2.insert(8);
//        tree2.insert(10);
//        tree2.insert(-3);
//        System.out.println(tree.equals(null));


//        tree.preOrder();
//        System.out.println();
//        tree.inOrder();
//        System.out.println();
//        tree.postOrder();
//        System.out.println();
//        System.out.println(tree.height());
//        System.out.println("\nDone!");
//        System.out.println(tree.min());

        AVL tree = new AVL();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.insert(100);

        tree.inOrder();
        System.out.println("done");
    }

}