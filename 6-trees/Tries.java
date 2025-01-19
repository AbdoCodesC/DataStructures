/*
    prefix - autocompletion
    lookup O(L) L->length of chars
    insert O(L) L->length of chars
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tries {
    private class Node {
        HashMap <Character, Node> children = new HashMap<>();
        char data;
        boolean isEndOfWord;
        public Node (char data) {
            this.data = data;
        }
        @Override
        public String toString(){
            return "value="+this.data;
        }

        private boolean hasChild (char ch) {
            return children.containsKey(ch)
;       }

        private void addChild (char ch) {
            children.put(ch, new Node(ch));
        }

        private Node getChild (char ch) {
            return children.get(ch);
        }

        public Node [] getChildren () {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren(){
            return !children.isEmpty();
        }

        private void removeChild (char ch) {
            children.remove(ch);
        }
    }

    private Node root = new Node(' ');

    public void insert (String word) {
        Node curr = root;
        for (char ch: word.toCharArray()) {
            ch = Character.toLowerCase(ch);
            if (!curr.hasChild(ch))
                curr.addChild(ch);
            curr = curr.getChild(ch);
        }
        curr.isEndOfWord = true;
    }

    public boolean contains (String word) {
        if (word == null) return false;
        Node curr = root;
        for (char ch: word.toCharArray()) {
            ch = Character.toLowerCase(ch);
            if (!curr.hasChild(ch))
                return false;
            curr = curr.getChild(ch);
        }
        return curr.isEndOfWord;
    }

    public void preOrder () {
        preOrder(root);
    }
    private void preOrder(Node curr) {
        System.out.print(curr.data+" ");
        for (Node ch: curr.getChildren()) {
            preOrder(ch);
        }
    }

    public void remove (String word) {
        if (word == null) return;
        remove(root, word.toLowerCase(), 0);
    }

    private void remove (Node curr, String word, int index) {
        if (index == word.length()) {
            curr.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = curr.getChild(ch);
        if (child == null) return;

        remove(child, word, index+1);

        if (!child.hasChildren() && !child.isEndOfWord) {
            curr.removeChild(ch);
        }
    }

    public List<String> findWords (String prefix) {
        List <String> words = new ArrayList<>();
        Node lastNode = findLastNodeOf(prefix);
        findWords(lastNode, prefix, words);
        return words;
    }

    private void findWords(Node root, String prefix, List<String> words) {
        if (root == null) return;
        if (root.isEndOfWord) words.add(prefix);
        for (Node child: root.getChildren()) {
            findWords(child, prefix + child.data ,words);
        }
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null) return null;
        Node curr = root;
        for (char ch: prefix.toCharArray()) {
            Node child = curr.getChild(ch);
            if (child == null) return null;
            curr = child;
        }
        return curr;
    }

}
