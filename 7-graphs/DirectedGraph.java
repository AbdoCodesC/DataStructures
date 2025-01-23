/*
    Directed {one way} and Undirected {both way}
    Weighted and Unweighted
    Cyclic and Acyclic

 */

import java.util.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Stack;

public class DirectedGraph {

    private class Node {
        String label;
        public Node (String label) {
            this.label = label;
        }
        @Override
        public String toString(){
            return this.label;
        }
    }

    private HashMap <String, Node> nodes = new HashMap<>();
    private HashMap <Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label){
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label){
        Node node = nodes.get(label);
        if (node == null) return;

        for (var key: adjacencyList.keySet()) {
            adjacencyList.get(key).remove(node);
        }

        adjacencyList.remove(node);
        nodes.remove(label);
    }



    public void removeEdge (String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print () {
        for (Node source: adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty()){
                System.out.println(source+" is connected to "+targets);
            }
        }
    }

    public void dfs (String root) {
        Node node = nodes.get(root);
        if (node == null) return;
        dfs(node, new HashSet<>());
    }

    public void dfs (Node root, HashSet <Node> visited) {
        System.out.print(root+" > ");
        visited.add(root);
        for (Node node: adjacencyList.get(root)) {
            if (!visited.contains(node))
                dfs(node, visited);
        }
    }

    public void dfsIter (String root) {
        Node node = nodes.get(root);
        if (node == null) return;
        Stack <Node> stack = new Stack<>();
        HashSet <Node> visited = new HashSet<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (!visited.contains(current)) {
                System.out.print(current+" ");
                visited.add(current);
                for (var neighbor: adjacencyList.get(current)) {
                    if (!visited.contains(neighbor))
                        stack.push(neighbor);
                }
            }
        }

    }

    public void bfsIter (String root) {
        Node node = nodes.get(root);
        if (node == null) return;
        Queue<Node> queue = new ArrayDeque<>();
        HashSet <Node> visited = new HashSet<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (!visited.contains(current)) {
                System.out.print(current+" ");
                visited.add(current);
                for (var neighbor: adjacencyList.get(current)) {
                    if (!visited.contains(neighbor))
                        queue.add(neighbor);
                }
            }
        }
    }

    // Works for DAG - directed acyclic graphs
    public ArrayList<String> topologicalSort () {
        Set <Node> visited = new HashSet<>();
        Stack <Node> stack = new Stack<>();
        for (Node node: nodes.values())
            topologicalSort(node, visited, stack);
        ArrayList<String> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop().label);
        }
        return list;
    }

    private void topologicalSort (Node node, Set<Node> visited, Stack<Node> stack) {
        if (visited.contains(node)) return;

        visited.add(node);
        for (Node neighbor: adjacencyList.get(node)) {
            topologicalSort(neighbor, visited, stack);
        }

        stack.push(node);
    }

    public boolean hasCycle(){
        Set <Node> all = new HashSet<>(nodes.values());
        Set <Node> visiting = new HashSet<>();
        Set <Node> visited = new HashSet<>();
        while (!all.isEmpty()) {
           var current =  (Node) all.toArray()[0];
           System.out.println(current);
           if (hasCycle(current, all, visiting, visited)) return true;
        }
        return false;
    }

    private boolean hasCycle(Node node, Set <Node> all, Set <Node> visiting, Set <Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (Node neighbor: adjacencyList.get(node)) {
            if (!visited.contains(neighbor)) {
                if (visiting.contains(neighbor)) return true;
                if (hasCycle(neighbor, all, visiting, visited)) return true;
            }
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }

}
