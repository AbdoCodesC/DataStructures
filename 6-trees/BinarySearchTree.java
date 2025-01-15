/*
    Lookup O(log n)
    Delete O(log n)
    Insert O(log n)
    Traverse Depth O(log n)
 */

import java.util.ArrayList;

public class BinarySearchTree {
    private class Node {
        Node right;
        Node left;
        int data;
        public Node (int data) {
            this.data = data;
        }
        public Node (Node left, int data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }
        @Override
        public String toString(){
            return "Node="+this.data;
        }
    }
    private Node root;

    public void insert (int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node curr = root;
        while (true) {
            if (data < curr.data) {
                if (curr.left == null) {
                    curr.left = node;
                    return;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = node;
                    return;
                }
                curr = curr.right;
            }
        }
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node root, int data) {
        if (root == null) {
            return null;
        }

        // Find the node to delete
        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest value in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    public boolean find (int data) {
        Node curr = root;
        while (curr != null) {
            if (data < curr.data) {
                curr = curr.left;
            } else if (data > curr.data) {
                curr = curr.right;
            } else {
                return true;
            }
        }

        return false;
    }

    // Depth: pre, in, post
    private void preOrder (Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    // root -> left -> right
    public void preOrder () {
        preOrder(root);
    }
    private void inOrder (Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }
    // left -> root -> right
    public void inOrder () {
        inOrder(root);
    }
    private void postOrder (Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");

    }
    // left -> right -> root
    public void postOrder () {
        postOrder(root);
    }

    private boolean isLeaf(Node root){
        return root.left == null && root.right == null;
    }

    public int height () {
        return height(root);
    }
    private int height (Node root) {
        if (root == null) return -1;
        if (isLeaf(root)) return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int min () {
        if (root == null) throw new IllegalStateException();

        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null) return true;
        if (first != null && second != null) {
            return first.data == second.data && equals(first.left, second.left)
                    && equals(first.right, second.right);
        }
        return false;
    }
    public boolean equals (BinarySearchTree other) {
        if (other == null) return false;
        return equals(root, other.root);
    }

    public boolean isBST () {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBST (Node root, int min, int max) {
        if (root == null) return true;

        if (root.data < min || root.data > max) {
            return false;
        }

        return isBST(root.left, min, root.data - 1) && isBST(root.right, root.data + 1, max);
    }

    public void swap () {
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public ArrayList<Integer> getNodesAtDist(int distance){
        ArrayList<Integer> list = new ArrayList<>();
        getNodesAtDist(root, distance, list);
        return list;
    }
    private void getNodesAtDist(Node root, int distance, ArrayList<Integer> list){
        if (root == null) return;
        if (distance == 0) {
            list.add(root.data);
            return;
        }
        getNodesAtDist(root.left, distance-1, list);
        getNodesAtDist(root.right, distance-1, list);
    }

    // Breadth: level
    public ArrayList<Integer> levelOrder () {
        ArrayList <Integer> list = new ArrayList<>();
        for (int i = 0; i <= height(); i++) {
            list.addAll(getNodesAtDist(i));
        }
        return list;
    }
}
