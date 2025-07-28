public class RedBlackTree <k,v> {
    Node <k,v> root;
    int size;
    
    public class Node <k,v> {
        Node <k,v> left, right, parent;
        k key;
        v value;
        boolean isLeftChild, black;
        public Node (k key, v value) {
            this.key = key;
            this.value = value;
            left = right = parent = null;
            black = false; // means red
            isLeftChild = false;
        }
    }

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public void add(k key, v value) {
        Node <k,v> node = new Node<>(key, value);
        if (root == null) {
            root = node;
            root.black = true;
            size++;
            return;
        }

        add(root, node);
        size++;
    }

    private void add(Node<k,v> parent, Node<k,v> newNode) {
        if (((Comparable<k>) newNode.key).compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
            } else {
                add(parent.right, newNode);
            }
        } else {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = true;
            } else {
                add(parent.left, newNode);
            }
        }
        
        checkColor(newNode);
    }

    private void checkColor(Node <k,v> node) {
        if (node == root) {
            node.black = true;
            return;
        }

        if (!node.black && !node.parent.black) {
            correctTree(node);
        }

        checkColor(node.parent);
    }

    private void correctTree(Node <k,v> node) {
        if (node.parent.isLeftChild) {
            // aunt should be node.parent.parent.right
            if (node.parent.parent.right == null || node.parent.parent.right.black) {
                // aunt is black, so rotate
                rotate(node);
                return;
            }

            if (node.parent.parent.right != null) {
                node.parent.parent.right.black = true;
            }
           
            node.parent.parent.black = false;
            node.parent.black = true;
            return;
        }
        //aunt is grandparent.left
        else {
            if (node.parent.parent.left == null || node.parent.parent.left.black) {
                // aunt is black, so rotate
                rotate(node);
                return;
            }

            if (node.parent.parent.left != null) {
                node.parent.parent.left.black = true;
            }
           
            node.parent.parent.black = false;
            node.parent.black = true;
            return;
        }
    }

    private void rotate(Node<k,v> node){
        if (node.isLeftChild) {
            if (node.parent.isLeftChild) {
                rightRotate(node.parent.parent);
                node.black = false;
                node.parent.black = true;
                if (node.parent.right != null){
                    node.parent.right.black = false;
                }
                return;
            }
            rightLeftRotate(node.parent.parent);
            node.black = true;
            node.right.black = false;
            node.left.black = false;
            
            return;
        } else {
            if (!node.parent.isLeftChild) {
                leftRotate(node.parent.parent);
                node.black = false;
                node.parent.black = true;
                if (node.parent.left != null){
                    node.parent.left.black = false;
                }
                return;
            }
            leftRightRotate(node.parent.parent);
            node.black = true;
            node.left.black = false;
            node.right.black = false;
            
            return;
        }
    }

    private void leftRotate(Node<k,v> node) {
        Node<k,v> rightChild = node.right;
        node.right = rightChild.left;
        
        if (rightChild.left != null) {
            rightChild.left.parent = node;
            rightChild.left.isLeftChild = false;
        }
        
        rightChild.parent = node.parent;
        
        if (node.parent == null) {
            root = rightChild;
        } else if (node.isLeftChild) {
            node.parent.left = rightChild;
            rightChild.isLeftChild = true;
        } else {
            node.parent.right = rightChild;
            rightChild.isLeftChild = false;
        }
        
        rightChild.left = node;
        node.parent = rightChild;
        node.isLeftChild = true;
    }
    
    private void rightRotate(Node<k,v> node) {
        Node<k,v> leftChild = node.left;
        node.left = leftChild.right;
        
        if (leftChild.right != null) {
            leftChild.right.parent = node;
            leftChild.right.isLeftChild = true;
        }
        
        leftChild.parent = node.parent;
        
        if (node.parent == null) {
            root = leftChild;
        } else if (node.isLeftChild) {
            node.parent.left = leftChild;
            leftChild.isLeftChild = true;
        } else {
            node.parent.right = leftChild;
            leftChild.isLeftChild = false;
        }
        
        leftChild.right = node;
        node.parent = leftChild;
        node.isLeftChild = false;
    }
    
    private void leftRightRotate(Node<k,v> node) {
        leftRotate(node.left);
        rightRotate(node);
    }
    
    private void rightLeftRotate(Node<k,v> node) {
        rightRotate(node.right);
        leftRotate(node);
    }
    
    // Get value by key
    public v get(k key) {
        Node<k,v> node = findNode(key);
        return node != null ? node.value : null;
    }
    
    private Node<k,v> findNode(k key) {
        Node<k,v> current = root;
        while (current != null) {
            int cmp = ((Comparable<k>) key).compareTo(current.key);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
    
    // Check if the tree contains a key
    public boolean contains(k key) {
        return findNode(key) != null;
    }
    
    // Get the size of the tree
    public int size() {
        return size;
    }
    
    // Check if the tree is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Inorder traversal for debugging
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node<k,v> node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.key + ":" + node.value + 
                             (node.black ? "(B) " : "(R) "));
            inorderHelper(node.right);
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        // Add some key-value pairs
        tree.add(7, "Seven");
        tree.add(3, "Three");
        tree.add(18, "Eighteen");
        tree.add(10, "Ten");
        tree.add(22, "Twenty-Two");
        tree.add(8, "Eight");
        tree.add(11, "Eleven");
        
        // Display the tree
        System.out.println("Inorder traversal of Red-Black Tree:");
        tree.inorderTraversal();
        
        // Retrieve some values
        System.out.println("Value for key 10: " + tree.get(10));
        System.out.println("Value for key 15: " + tree.get(15));
        
        // Check if keys exist
        System.out.println("Contains key 8: " + tree.contains(8));
        System.out.println("Contains key 15: " + tree.contains(15));
        
        // Display the size
        System.out.println("Tree size: " + tree.size());
    }
}

// /*
// Red-black tree is a binary search tree that satisfies the following properties:
//     1. Every node is either red or black.
//     2. The root is black
//     3. Every external node is black.
//     4. Both children of a red node are black.
//     5. All paths from a node to its descendant external nodes have the same number of black internal nodes.
//     6. all nulls are black

//     black uncle we rotate
//     red uncle we color flip
//  */

//  public class RedBlackTree {
//     private static final boolean RED = true;
//     private static final boolean BLACK = false;

//     private class Node {
//         int data;
//         Node left, right, parent;
//         boolean color; // true for red, false for black

//         Node(int data) {
//             this.data = data;
//             this.color = RED; // New nodes are always red
//             this.left = null;
//             this.right = null;
//             this.parent = null;
//         }
//     }

//     private Node root;

//     public RedBlackTree() { // initializing the root
//         root = null;
//     }

//     // Insert operation
//     public void insert(int data) {
//         Node node = new Node(data);

//         // Standard BST insert
//         if (root == null) {
//             root = node;
//         } else {
//             Node parent = null;
//             Node current = root;

//             while (current != null) {
//                 parent = current;
//                 if (node.data < current.data) {
//                     current = current.left;
//                 } else {
//                     current = current.right;
//                 }
//             }

//             node.parent = parent;

//             if (node.data < parent.data) {
//                 parent.left = node;
//             } else {
//                 parent.right = node;
//             }
//         }

//         // Fix Red-Black tree violations
//         fixViolation(node);
//     }

//     // Fix Red-Black Tree violations after insertion
//     private void fixViolation(Node node) {
//         Node parent = null;
//         Node grandParent = null;

//         while (node != root && node.color == RED && node.parent.color == RED) {
//             parent = node.parent;
//             grandParent = parent.parent;

//             if (parent == grandParent.left) {
//                 Node uncle = grandParent.right;

//                 // Case 1: Uncle is red
//                 if (uncle != null && uncle.color == RED) {
//                     grandParent.color = RED;
//                     parent.color = BLACK;
//                     uncle.color = BLACK;
//                     node = grandParent;
//                 } else {
//                     // Case 2: node is right child
//                     if (node == parent.right) {
//                         rotateLeft(parent);
//                         node = parent;
//                         parent = node.parent;
//                     }

//                     // Case 3: node is left child
//                     rotateRight(grandParent);
//                     boolean tempColor = parent.color;
//                     parent.color = grandParent.color;
//                     grandParent.color = tempColor;
//                     node = parent;
//                 }
//             } else {
//                 Node uncle = grandParent.left;

//                 // Case 1: Uncle is red
//                 if (uncle != null && uncle.color == RED) {
//                     grandParent.color = RED;
//                     parent.color = BLACK;
//                     uncle.color = BLACK;
//                     node = grandParent;
//                 } else {
//                     // Case 2: node is left child
//                     if (node == parent.left) {
//                         rotateRight(parent);
//                         node = parent;
//                         parent = node.parent;
//                     }

//                     // Case 3: node is right child
//                     rotateLeft(grandParent);
//                     boolean tempColor = parent.color;
//                     parent.color = grandParent.color;
//                     grandParent.color = tempColor;
//                     node = parent;
//                 }
//             }
//         }

//         root.color = BLACK;
//     }

//     // Left rotation
//     private void rotateLeft(Node node) {
//         Node rightChild = node.right;
//         node.right = rightChild.left;

//         if (rightChild.left != null) {
//             rightChild.left.parent = node;
//         }

//         rightChild.parent = node.parent;

//         if (node.parent == null) {
//             root = rightChild;
//         } else if (node == node.parent.left) {
//             node.parent.left = rightChild;
//         } else {
//             node.parent.right = rightChild;
//         }

//         rightChild.left = node;
//         node.parent = rightChild;
//     }

//     // Right rotation
//     private void rotateRight(Node node) {
//         Node leftChild = node.left;
//         node.left = leftChild.right;

//         if (leftChild.right != null) {
//             leftChild.right.parent = node;
//         }

//         leftChild.parent = node.parent;

//         if (node.parent == null) {
//             root = leftChild;
//         } else if (node == node.parent.right) {
//             node.parent.right = leftChild;
//         } else {
//             node.parent.left = leftChild;
//         }

//         leftChild.right = node;
//         node.parent = leftChild;
//     }

//     // Inorder traversal
//     public void inorderTraversal() {
//         inorderHelper(root);
//         System.out.println();
//     }

//     private void inorderHelper(Node node) {
//         if (node != null) {
//             inorderHelper(node.left);
//             System.out.print(node.data + 
//                     (node.color == RED ? "(R) " : "(B) "));
//             inorderHelper(node.right);
//         }
//     }

//     // Search operation
//     public boolean search(int data) {
//         return searchHelper(root, data);
//     }

//     private boolean searchHelper(Node node, int data) {
//         if (node == null) {
//             return false;
//         }

//         if (data == node.data) {
//             return true;
//         }

//         if (data < node.data) {
//             return searchHelper(node.left, data);
//         }

//         return searchHelper(node.right, data);
//     }

//     // Main method with example usage
//     public static void main(String[] args) {
//         RedBlackTree tree = new RedBlackTree();

//         // Insert some values
//         tree.insert(7);
//         tree.insert(3);
//         tree.insert(18);
//         tree.insert(10);
//         tree.insert(22);
//         tree.insert(8);
//         tree.insert(11);

//         System.out.println("Inorder traversal of Red-Black Tree:");
//         tree.inorderTraversal();

//         // Search for values
//         System.out.println("Searching for 10: " + tree.search(10));
//         System.out.println("Searching for 15: " + tree.search(15));
//     }
// }










// /*

// Red-black tree is a binary search tree that satisfies the following properties:
//     1. Every node is either red or black.
//     2. The root is black
//     3. Every external node is black.
//     4. Both children of a red node are black.
//     5. All paths from a node to its descendant external nodes have the same number of black internal nodes.
//  */

// public class RedBlackTree<T extends Comparable<T>> {
//     private static final boolean RED = true;
//     private static final boolean BLACK = false;

//     private class Node {
//         T data;
//         Node left, right, parent;
//         boolean color; // true for red, false for black

//         Node(T data) {
//             this.data = data;
//             this.color = RED; // New nodes are always red
//             this.left = null;
//             this.right = null;
//             this.parent = null;
//         }
//     }

//     private Node root;

//     public RedBlackTree () { // initializing the root
//         root = null;
//     }

//     // Insert operation
//     public void insert(T data) {
//         Node node = new Node(data);

//         // Standard BST insert
//         if (root == null) {
//             root = node;
//         } else {
//             Node parent = null;
//             Node current = root;

//             while (current != null) {
//                 parent = current;
//                 if (node.data.compareTo(current.data) < 0) {
//                     current = current.left;
//                 } else {
//                     current = current.right;
//                 }
//             }

//             node.parent = parent;

//             if (node.data.compareTo(parent.data) < 0) {
//                 parent.left = node;
//             } else {
//                 parent.right = node;
//             }
//         }

//         // Fix Red-Black tree violations
//         fixViolation(node);
//     }

//     // Fix Red-Black Tree violations after insertion
//     private void fixViolation(Node node) {
//         Node parent = null;
//         Node grandParent = null;

//         while (node != root && node.color == RED && node.parent.color == RED) {
//             parent = node.parent;
//             grandParent = parent.parent;

//             if (parent == grandParent.left) {
//                 Node uncle = grandParent.right;

//                 // Case 1: Uncle is red
//                 if (uncle != null && uncle.color == RED) {
//                     grandParent.color = RED;
//                     parent.color = BLACK;
//                     uncle.color = BLACK;
//                     node = grandParent;
//                 } else {
//                     // Case 2: node is right child
//                     if (node == parent.right) {
//                         rotateLeft(parent);
//                         node = parent;
//                         parent = node.parent;
//                     }

//                     // Case 3: node is left child
//                     rotateRight(grandParent);
//                     boolean tempColor = parent.color;
//                     parent.color = grandParent.color;
//                     grandParent.color = tempColor;
//                     node = parent;
//                 }
//             } else {
//                 Node uncle = grandParent.left;

//                 // Case 1: Uncle is red
//                 if (uncle != null && uncle.color == RED) {
//                     grandParent.color = RED;
//                     parent.color = BLACK;
//                     uncle.color = BLACK;
//                     node = grandParent;
//                 } else {
//                     // Case 2: node is left child
//                     if (node == parent.left) {
//                         rotateRight(parent);
//                         node = parent;
//                         parent = node.parent;
//                     }

//                     // Case 3: node is right child
//                     rotateLeft(grandParent);
//                     boolean tempColor = parent.color;
//                     parent.color = grandParent.color;
//                     grandParent.color = tempColor;
//                     node = parent;
//                 }
//             }
//         }

//         root.color = BLACK;
//     }

//     // Left rotation
//     private void rotateLeft(Node node) {
//         Node rightChild = node.right;
//         node.right = rightChild.left;

//         if (rightChild.left != null) {
//             rightChild.left.parent = node;
//         }

//         rightChild.parent = node.parent;

//         if (node.parent == null) {
//             root = rightChild;
//         } else if (node == node.parent.left) {
//             node.parent.left = rightChild;
//         } else {
//             node.parent.right = rightChild;
//         }

//         rightChild.left = node;
//         node.parent = rightChild;
//     }

//     // Right rotation
//     private void rotateRight(Node node) {
//         Node leftChild = node.left;
//         node.left = leftChild.right;

//         if (leftChild.right != null) {
//             leftChild.right.parent = node;
//         }

//         leftChild.parent = node.parent;

//         if (node.parent == null) {
//             root = leftChild;
//         } else if (node == node.parent.right) {
//             node.parent.right = leftChild;
//         } else {
//             node.parent.left = leftChild;
//         }

//         leftChild.right = node;
//         node.parent = leftChild;
//     }

//     // Inorder traversal
//     public void inorderTraversal() {
//         inorderHelper(root);
//         System.out.println();
//     }

//     private void inorderHelper(Node node) {
//         if (node != null) {
//             inorderHelper(node.left);
//             System.out.print(node.data +
//                     (node.color == RED ? "(R) " : "(B) "));
//             inorderHelper(node.right);
//         }
//     }

//     // Search operation
//     public boolean search(T data) {
//         return searchHelper(root, data);
//     }

//     private boolean searchHelper(Node node, T data) {
//         if (node == null) {
//             return false;
//         }

//         if (data.compareTo(node.data) == 0) {
//             return true;
//         }

//         if (data.compareTo(node.data) < 0) {
//             return searchHelper(node.left, data);
//         }

//         return searchHelper(node.right, data);
//     }

//     // Main method with example usage
//     public static void main(String[] args) {
//         RedBlackTree<Integer> tree = new RedBlackTree<>();

//         // Insert some values
//         tree.insert(7);
//         tree.insert(3);
//         tree.insert(18);
//         tree.insert(10);
//         tree.insert(22);
//         tree.insert(8);
//         tree.insert(11);

//         System.out.println("Inorder traversal of Red-Black Tree:");
//         tree.inorderTraversal();

//         // Search for values
//         System.out.println("Searching for 10: " + tree.search(10));
//         System.out.println("Searching for 15: " + tree.search(15));
//     }
// }
