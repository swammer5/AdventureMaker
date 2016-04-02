package model;

/**
 * <b>Node</b> represents an node of a graph.
 * 
 * @param <S> the type of node data
 * 
 * @specfield data : S // stores node data
 */
public class Node<S> {

    private S data;

    // Abstraction Function:
    // For a given Node n, data represents the data this node stores
    //
    // Representation Invariant:
    // data != null

    /**
     * @param data Object of type S to represent the node data
     * @throws IllegalArgumentException
     *         if (data == null)
     */
    public Node(S data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        this.data = data;
        checkRep();
    }

    /**
     * Returns this Node's data
     * 
     * @return node data
     */
    public S name() {
        return data;
    }

    /**
     * Standard equality operation.
     * 
     * @return true if and only if o is an instance of Node and node data of
     *         this and o are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node<?>)) {
            return false;
        }
        Node<?> otherNode = (Node<?>) o;
        return data.equals(otherNode.data);
    }

    /**
     * Standard hashCode function.
     * 
     * @return an int that all objects equal to this will also return.
     */
    @Override
    public int hashCode() {
        return data.hashCode();
    }

    /**
     * Checks that the representation invariant holds
     */
    private void checkRep() {
        assert (data != null) : "data cannot be null";
    }
}
