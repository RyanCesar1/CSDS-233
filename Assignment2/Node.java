/**
 * @author Ryan Cesar Irizarry
 * A class that represents a node in the Binary Search Tree
 */
  public class Node {
    private int key;
    private Node left;
    private Node right;
    
    /**
     * Constructor to create a new Node with the specified key
     *
     * @param key The key value for this new node
     */
    public Node(int key) {
      this.key = key;
      left = null;
      right = null;
    }
    
    /**
     * Gets the key value stored in the node.
     *
     * @return The key value stored in the node.
     */
    public int getKey() {
      return key;
    }
    
    /**
     * Gets the left child node of this node.
     *
     * @return The left child node of this node, or null if it doesn't exist.
     */
    public Node getLeft() {
      return left;
    }
    
    /**
     * Sets the left child node of this node.
     *
     * @param leftNode The node to be set as the left child of this node.
     */
    public void setLeft(Node leftNode) {
      left = leftNode;
    }
    
    /**
     * Gets the right child node of this node.
     *
     * @return The right child node of this node, or null if it doesn't exist.
     */
    public Node getRight() {
      return right;
    }
    
    /**
     * Sets the right child node of this node.
     *
     * @param rightNode The node to be set as the right child of this node.
     */
    public void setRight(Node rightNode) {
      right = rightNode;
    }
  }