package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Graph represents a mutable, directed, non-labeled, non-multigraph. Edges of
 * Graph are one way, there are no edge labels, and edges are unique. Graphs can
 * consist of a number of nodes connected by edges.
 * 
 * Nodes store any objects of type K. All objects in graph of type K in nodes
 * must be unique, that is, for two objects in this i,j i.equals(j) must be
 * false.
 * 
 * @author Sean Wammer
 * 
 * @param <K> the type of the data that nodes in the this Graph store
 */
public class Graph<K> {
    // not yet implemented!

    private Map<Node<K>, Set<Edge<K>>> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    /**
     * Returns true iff this contains a node with the given data.
     * 
     * @param data - the data of the node to check
     * @return true iff the given node is in this graph.
     * @throws IllegalArgumentException if (data == null)
     */
    public boolean containsNode(K data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        Node<K> node = new Node<>(data);
        return nodes.containsKey(node);
    }

    /**
     * Adds a new node to this Graph.
     * 
     * If the given node is already in this graph, then the previous node is
     * overwritten. The previous node is overwritten if and only if the two
     * nodes are equal, that is, for two nodes A and B, if A.equals(B), and B is
     * added after A is added, B will overwrite A.
     * 
     * @param data The data to be inserted as a node in this Graph
     * @throws IllegalArgumentException if (data == null)
     */
    public void addNode(K data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        Node<K> node = new Node<K>(data);
        if (!nodes.containsKey(node)) {
            nodes.put(node, new HashSet<Edge<K>>());
        }
    }

    /**
     * Adds an edge to the graph from parent to child. If parent or child nodes
     * are not in this graph they will be added to the graph as well as new
     * nodes.
     * 
     * Duplicate edges are not added.
     * 
     * @param parent The K the added edge points from
     * @param child The K the added edge points to
     * @throws IllegalArgumentException if (parent == null || child == null)
     */
    public void addEdge(K parent, K child) {
        if (parent == null || child == null) {
            throw new IllegalArgumentException(
                    "parent and child must not be null");
        }

        Edge edge = new Edge<K>(new Node<K>(parent), new Node<K>(child));
        Node<K> parentNode = edge.parent();
        Node<K> childNode = edge.child();

        if (!nodes.containsKey(parentNode)) { // missing parent
            addNode(parent);
        }
        if (!nodes.containsKey(childNode)) { // missing child
            addNode(child);
        }

        // add edge to existing set in map
        nodes.get(parentNode).add(edge);
    }

    /**
     * Removes the edge in this Graph from given parent to the given child. Has
     * no effect if the edge does not exist.
     * 
     * You specify the target edge by specifying end point nodes, A and B in
     * edge, (A,B).
     * 
     * @param parent The node the target edge points from
     * @param child The node the target edge points to
     */
    public void removeEdge(K parent, K child) {
        Node<K> parentNode = new Node<K>(parent);
        if (nodes.containsKey(parentNode)) {
            nodes.get(parentNode).remove(new Node<K>(child));
        }
    }

    /**
     * Returns all the node objects K that are adjacent to source, or null if
     * there is no node with object source.
     * 
     * @param source The node to retrieve adjacent nodes from
     */
    public Set<K> adjacent(K source) {
        Set<K> adjacent = new HashSet<>();
        Node<K> sourceNode = new Node<>(source);

        // get all the adjacent objects
        for (Edge<K> edge : nodes.get(sourceNode)) {
            adjacent.add(edge.child().data());
        }

        return adjacent;
    }
}
