/*
    self-balancing trees
    needs to be balanced, a tree can't be O(log n) if all nodes are on one side, which becomes O(n)
    AVL -> rotates -> keeps balance
    check: height (left)-height(right) <= 1
    if height is more than 1 we have to rotate to shorter side
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

    right skew - when all nodes go right
    left skew - when all nodes go left
 */

public class AVL {
    private class AVLNode {
        AVLNode left;
        AVLNode right;
        int data;
        int height = 0;
        public AVLNode (int data) {
            this.data = data;
        }
        public String toString(){
            return "Value="+this.data;
        }
    }

    private AVLNode root;

    public void insert(int data){ // where to inser
        root = insert(root, data);
    }

    private AVLNode insert (AVLNode root, int data){ // logic, balancing
        if (root == null) {
            return new AVLNode(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        setHeight(root);

        return balance(root);
    }

    public void remove(int data) {
        root = remove(root, data);
    }
    
    private AVLNode remove(AVLNode root, int data) {
        if (root == null) return null;
    
        if (data < root.data) {
            root.left = remove(root.left, data);
        } else if (data > root.data) {
            root.right = remove(root.right, data);
        } else {
            // Node with one child or no child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
    
            // Node with two children: Get the in-order successor (smallest in the right subtree)
            AVLNode temp = findMin(root.right);
            root.data = temp.data;
            root.right = remove(root.right, temp.data);
        }
    
        setHeight(root);
        return balance(root);
    }
    
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private AVLNode balance (AVLNode root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.left) < 0){
                root.left = leftRotate(root.left);
            }
            System.out.println("left heavy");
            return rightRotate(root);
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.right) > 0){
                root.right = rightRotate(root.right);
            }
            System.out.println("right heavy");
            return leftRotate(root);
        }
        return root;
    }

    // helper functions
    private int height (AVLNode node) {
        return (node == null) ? -1 : node.height;
    }

    private boolean isRightHeavy(AVLNode node){
        return balanceFactor(node) < -1;
    }

    private boolean isLeftHeavy(AVLNode node){
        return balanceFactor(node) > 1;
    }

    /*  
        calculating the balance between the left and right tree
         > 1  ? left heavy
         < -1 ? right heavy
    */
    private int balanceFactor (AVLNode node) { 
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // rotation
    private AVLNode rightRotate (AVLNode root) {
        AVLNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        
        setHeight(root);
        setHeight(newRoot);
        
        return newRoot;
    }

    private AVLNode leftRotate (AVLNode root) {
        AVLNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        
        setHeight(root);
        setHeight(newRoot);
        
        return newRoot;
    }

    private void setHeight(AVLNode node){ // set the height
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void inOrder (AVLNode root) {
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

    public static void main(String[] args) {
        AVL avl  = new AVL();

        avl.insert(10);
        avl.insert(1);
        avl.insert(11);
        avl.insert(3);
        avl.insert(5);
        avl.insert(100);
        avl.insert(1000);
        avl.insert(104);

        avl.inOrder();
        System.out.println();
        avl.remove(100);
        avl.inOrder();
    }
}


