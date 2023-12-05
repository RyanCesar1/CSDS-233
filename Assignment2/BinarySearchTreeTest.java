/**
 * @author Ryan Cesar Irizarry
 * A JUnit Tester to test the methods of the Binary Search Tree Class
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
  
  private BinarySearchTree bst;
  
  @Before
  public void setUp() {
    BinarySearchTree bst = new BinarySearchTree();
    Node root = new Node(5);
  }
  
  @Test
  public void testInsert() {
    BinarySearchTree tree = new BinarySearchTree();
    Node root = new Node(5);
    bst.insert(root, 9);
    assertEquals(5, root.getKey());
  }
  
  @Test
  public void testPreorderRec() {
    Node root = new Node(5);
    bst.insert(root, 5);
    bst.insert(root, 5);
    bst.insert(root, 3);
    bst.insert(root, 7);
    
    // Create a StringBuilder to collect the preorder traversal output
    StringBuilder output = new StringBuilder();
    
    // Redirect System.out to collect output
    System.setOut(new java.io.PrintStream(
                                          new java.io.ByteArrayOutputStream() {
      public void print(String str) {
        output.append(str);
      }
    }
    ));
    
    bst.preorderRec(root);
    
    // Reset System.out
    System.setOut(System.out);
    
    assertEquals("Preorder Traversal: 5 3 7 ", output.toString());
  }
  
  @Test
  public void testSum() {
    Node root = new Node(5);
    bst.insert(root, 5);
    bst.insert(root, 3);
    bst.insert(root, 7);
    
    assertEquals(15, bst.sum(root));
  }
  
  @Test
  public void testKthBiggest() {
    Node root = new Node(5);
    bst.insert(root, 5);
    bst.insert(root, 3);
    bst.insert(root, 7);
    
    Node kthBiggestNode = bst.kthBiggest(root, 2);
    assertNotNull(kthBiggestNode);
    assertEquals(5, kthBiggestNode.getKey());
  }
}