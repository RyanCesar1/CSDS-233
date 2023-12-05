import java.util.Scanner;
/**
 * @author Ryan Cesar Irizarry
 * A class that represents a Binary Search Tree object which contains a few of the typical operations of binary search trees
 */
public class BinarySearchTree {
  
  private Node root; 
  
  /**
   * Constructor to create an empty Binary Search Tree
   */
  public BinarySearchTree() {
    root = null;
  }
  
  /**
   * Inserts a new node with the specified key into the Binary Search Tree
   * 
   * @param root The root of the tree to which the new node will be inserted
   * @param key The key value of the new node to be inserted
   */
  public void insert(Node root, int key) {
    Node parent = null;
    Node trav = root; 
    //finds the parent of the node to be inserted
    while (trav != null) { 
      parent = trav;
      if (key < trav.getKey())
        trav = trav.getLeft();
      else
        trav = trav.getRight();
    }
    //insert the new node to the discovered parent
    if (parent == null)
      root = new Node(key);
    else if (key < parent.getKey())
      parent.setLeft(new Node(key));
    else 
      parent.setRight(new Node(key));
  }
  
  /**
   * Performs a preorder traversal of the Binary Search Tree
   * 
   * @param root The root node of the tree to be traversed
   */
  public void preorderRec(Node root) {
    if (root != null) { //check the left and right subtrees of each root until there do not exist any nodes to be checked
      int k = root.getKey(); //check value of current root and ensure check by storing retreived value
      preorderRec(root.getLeft()); //traverse left subtree
      preorderRec(root.getRight()); //traverse right subtree
    }
  }
  
  /**
   * Computes the sum of all the keys in the Binary Search Tree
   * 
   * @param root The root node of the tree for which the sum is calculated
   * @param The sum of all keys in the tree.
   */
  public int sum(Node root) {
    if (root != null) { 
      int k = root.getKey(); 
      int sumLeft = sum(root.getLeft());
      int sumRight = sum(root.getRight());
      return k + sumLeft + sumRight;
    }
    else return 0;
  }
  
  /**
   * Helper method to find the k'th biffest element in the Binary Search Tree
   * 
   * @param root The root node of the tree being searched 
   * @param k The value of k for which the k'th biggest element is sought
   * @param count An array to keep track of the count during traversal
   * @return The k'th biggest node in the tree
   */
  public Node kthBiggestHelper(Node root, int k, int[] count) {
    if (root != null) {
      Node rightResult = kthBiggestHelper(root.getRight(), k, count);
      if (rightResult != null) {
        return rightResult;
      }
      count[0]++;
      if (count[0] == k) {
        return root;
      }  
      return kthBiggestHelper(root.getLeft(), k, count);
    }
    else return null;
  }
  
  /**
   * Finds the k'th biggest element in the Binary Search Tree
   * 
   * @param root The root of the tree for which the k'th biggest element is found
   * @param k The value of k for which the k'th biggest element is sought
   * @return The k'th biggest node in the tree
   */
  public Node kthBiggest(Node root, int k) {
    int[] count = {0};
    return kthBiggestHelper(root, k, count);
  }
  
  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    boolean loopThrough = true;
    System.out.println("Binary Search Tree Demo");
    System.out.println("\nChoose an operation:");
    System.out.println("1. Insert");
    System.out.println("2. Preorder Traversal");
    System.out.println("3. Compute Sum");
    System.out.println("4. Find k'th Biggest");
    System.out.println("5. Exit");
    
    while (loopThrough) {
      System.out.print("Choose an option: ");
      
      Scanner menuOption = new Scanner(System.in);
      String option = menuOption.nextLine();
      
      switch (option) {
        case "1":
          System.out.print("Enter the key to insert: ");
          Scanner insertScanner = new Scanner(System.in);
          int key = insertScanner.nextInt();
          bst.insert(bst.root, key);
          break;
          
        case "2":
          System.out.println("Preorder Traversal:");
          bst.preorderRec(bst.root);
          break;
          
        case "3":
          int sum = bst.sum(bst.root);
          System.out.println("Sum of all keys: " + sum);
          break;
          
        case "4":
          System.out.print("Enter the value of k: ");
          int k = new Scanner(System.in).nextInt();
          Node kthBiggestNode = bst.kthBiggest(bst.root, k);
          if (kthBiggestNode != null) {
            System.out.println("The " + k + "th biggest element is: " + kthBiggestNode.getKey());
          } else {
            System.out.println("No " + k + "th biggest element found.");
          }
          break;
          
        case "5":
          loopThrough = false;
          break;
          
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}
