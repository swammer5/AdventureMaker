package model;

/**
 * <b>Edge</b> represents an edge for a directed graph.
 * 
 * An edge e denoted (A,B) points from Node A to Node B and can be illustrated
 * as such,
 * 
 * A --e--> B
 * 
 * where A is the parent Node and B is the child node.
 * 
 * @param <S>
 *            the type that Node stores
 * 
 * @specfield parent: Node<S> // the Node that this points from
 * @specfield child : Node<S> // the Node that this points to
 */
public class Edge<S> {

	/** Node that this Edge points from */
	private Node<S> parent;
	/** Node that this Edge points to */
	private Node<S> child;

	// Abstraction Function:
	// For a given Edge e, "the node that e points to" is synonymous with
	// 'child.'
	// and "the node that e points from" is synonymous with 'parent'
	//
	// Representation Invariant:
	// label != null
	// parent != null
	// child != null

	/**
	 * Constructs a new Edge with the given parent and child nodes
	 * 
	 * @param child
	 *            The Node that this Edge points to
	 * @throws IllegalArgumentException
	 *             if (label == null || child == null)
	 */
	public Edge(Node<S> parent, Node<S> child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException("neither argument can be null");
		}
		this.parent = parent;
		this.child = child;
		checkRep();
	}

	/**
	 * Returns the parent Node this Edge points from
	 * 
	 * @return the parent Node this Edge points from
	 */
	public Node<S> parent() {
		return parent;
	}

	/**
	 * Returns the child Node this Edge points to
	 * 
	 * @return the child Node this Edge points to
	 */
	public Node<S> child() {
		return child;
	}

	/**
	 * Standard equality operation.
	 * 
	 * @return true if and only if o is an instance of Edge, the label of this
	 *         and obj are equal and the child of this and obj are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Edge<?>)) {
			return false;
		}

		Edge<?> other = (Edge<?>) o;
		return parent().equals(other.parent()) && child().equals(other.child());
	}

	/**
	 * Standard hashCode function.
	 * 
	 * @return an int that all objects equal to this will also return.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + parent.hashCode() + child.hashCode();
		return result;
	}

	/**
	 * Checks that the representation invariant holds
	 */
	private void checkRep() {
		assert (parent != null) : "parent == null";
		assert (child != null) : "child == null";
	}
}
