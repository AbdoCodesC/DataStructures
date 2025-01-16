/*
    self-balancing trees
    needs to be balanced, a tree can't be O(log n) if all nodes are on one side, which becomes O(n)
    AVL -> rotates -> keeps balance
    check: height (left)-height(right) <= 1
    Rotations:
    left (LL)
        1
           2        ->       2
               3         1       3
    right (RR)
               3
           2        ->       2
       1                 1       3
    left-right (LR)
               10                10
           5        ->       7         ->       7
               7         5                  5       10
    right-left (RL)
           5             5
                10  ->        7        ->       7
           7                      10        5       10
 */

public class AVL {
    private class Node {
        Node left;
        int data;
        Node right;
        int height = 0;
        public Node (int data) {
            this.data = data;
        }
        public String toString(){
            return "Value="+this.data;
        }
    }

    private Node root;

    private Node insert (Node root, int data){
        if (root == null) {
            return new Node(data);
        };
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        setHeight(root);

        return balance(root);
    }

    private Node balance (Node root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.left) < 0){
                root.left = leftRotate();
            }
            return rightRotate();
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.right) > 0){
                root.right = rightRotate();
            }
            return leftRotate();
        }
        return root;
    }

    public void insert(int data){
        root = insert(root, data);
    }

    private int height (Node node) {
        return (node == null) ? -1 : node.height;
    }

    private boolean isRightHeavy(Node node){
        return balanceFactor(node) < -1;
    }

    private boolean isLeftHeavy(Node node){
        return balanceFactor(node) > 1;
    }

    private int balanceFactor (Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate () {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }

    private Node leftRotate () {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }

    private void setHeight(Node node){
        node.height = Math.max(height(node.left), height(node.right)) + 1;
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
}


