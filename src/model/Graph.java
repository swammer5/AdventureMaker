package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Graph represents a mutable, directed, non-labeled, non-multigraph. Edges of
 * Graph are one way, there are no edge labels, and edges are unique. Graphs can
 * consist of a number of Nodes connected by Edges.
 * 
 * Nodes hold any objects of type K. All objects in graph of type K in nodes
 * must be unique, that is, for two objects in this i,j i.equals(j) must be
 * false.
 * 
 * @author Sean Wammer
 * 
 * @param <K> the type of the data that Node stores
 */
public class Graph<K> {
	// not yet implemented!

	private Map<Node<K>, Set<Edge<K>>> nodes;

	public Graph() {
		nodes = new HashMap<>();
	}

	/**
	 * Adds a new Node to this Graph.
	 * 
	 * @param node The Node to be inserted
	 * @throws IllegalArgumentException
	 *             if (node == null)
	 */
	public void addNode(Node<K> node) {
		if (node == null) {
			throw new IllegalArgumentException("node must not be null");
		}
		if (!nodes.containsKey(node)) {
			nodes.put(node, new HashSet<Edge<K>>());
		}
	}
}
