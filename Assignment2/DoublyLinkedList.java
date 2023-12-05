/** @author Ryan Cesar Irizarry
  * DoublyLinkedList is a class that represents a doubly linked list of characters
  */
public class DoublyLinkedList {
  
  /**
   * StringNode is an innter class representing a node in the doubly linked list
   */
  private class StringNode {
    private char ch;
    private StringNode next;
    private StringNode prev;
    
    /**
     * Constructs a new StringNode with the specified character.
     *
     * @param myCh The character value to be stored in the node.
     */
    public StringNode(char myCh) {
      ch = myCh;
      next = null;
      prev = null;
    }
  }
  
  private StringNode lst_head;
  private StringNode lst_tail;
  private int theSize;
  
  /**
   * Constructs an empty DoublyLinkedList.
   */
  public DoublyLinkedList() {
    lst_head = null;
    lst_tail = null;
    theSize = 0;
  }
  
  /**
   * Swaps the node containing the specified character with its successor.
   *
   * @param c The character to be swapped with its successor.
   */
  public void swapNode(char c) {
    StringNode currentNode = lst_head;
    while (currentNode != null && currentNode.ch != c) {
      currentNode = currentNode.next; //traverse list
    }
    
    if (currentNode != null) {
      if (currentNode.next != null) {
        StringNode s = currentNode.next; //successor node S
        StringNode temp = s.next; //node following the successor node
        s.next = currentNode;
        s.prev = currentNode.prev;
        currentNode.next = temp;
        if (currentNode.prev != null) {
          currentNode.prev.next = s;
        }
        else {
          lst_head = s;
        }
        currentNode.prev = s;
        if (temp != null) {
          temp.prev = currentNode;
        }
        
        if (currentNode == lst_tail) {
          lst_tail = s;
        }
      }
    }
  }
  
  /**
     * The main method is the entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
  public static void main(String[] args) {
    DoublyLinkedList myList = new DoublyLinkedList();
    
    // Insert some characters into the linked list
    myList.swapNode('A'); // Assuming 'A' exists in the list
    myList.swapNode('B'); // Assuming 'B' exists in the list
    
    // Print the linked list after swapping
    printLinkedList(myList);
  }
  
  /**
   * Helper method to print the contents of the linked list
   * 
   * @param list The DoublyLinkedList to be printed
   */
  private static void printLinkedList(DoublyLinkedList list) {
    DoublyLinkedList.StringNode currentNode = list.lst_head;
    while (currentNode != null) {
      System.out.print(currentNode.ch + " ");
      currentNode = currentNode.next;
    }
    System.out.println();
  }
  
}

