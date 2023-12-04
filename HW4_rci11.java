/**
 * This class represents a Hash Table implementation with open addressing using linear probing and double hashing.
 * @author Ryan Cesar Irizarry
 */
public class HW4_rci11 {

  //Size of the Hash Table
  private int size;

  //Array to store keys in the hash table
  private int[] table;

  //Array to track whether a position in the table is occupied
  private boolean[] occupied;

  /**
   * Hash function for linear probing.
   *
   * @param key The key to be hashed.
   * @return The hash value.
   */
  private int hashFunc(int key) {
      return key % size;
  }

  /**
   * Hash function for double hashing.
   *
   * @param key The key to be hashed.
   * @return The hash value.
   */
  private int hashFunc2(int key) {
      return 7 - (key % 7);
  }

  /**
   * Checks if a given index in the table is occupied.
   *
   * @param index The index to check
   * @return True if the index is occupied, false otherwise.
   */
  private boolean isOccupied(int index) {
      return occupied[index];
  }

  /**
   * Constructs a new Hash Table with the specified size.
   *
   * @param size The size of the hash table.
   */
  public HW4_rci11(int size) { //we initialize with a size since we are implementing open address probing methods linear and double hashing
      this.size = size;
      this.table = new int[size];
      this.occupied = new boolean[size];
  }

  /**
   * Inserts a key into the hash table using linear probing.
   *
   * @param key The key to be inserted.
   */
  public void linearProbingInsert(int key) {
      int pos = hashFunc(key); //pos represents position which is synonymous with the index
      while (isOccupied(pos)) {
          pos = (pos + 1) % size;
          if (pos != 0) {
              System.out.println("Collision at position " + (pos - 1) + ". Attempt next position " + pos);
          }
          else {
              System.out.println("Collision at position  9. Attempt next position " + pos);
          }
      }
      table[pos] = key;
      occupied[pos] = true; //current index slot is filled by an element
      System.out.println("Key " + key + " inserted at position " + pos + "\n");
  }

  /**
   * Inserts a key into the hash table using double hashing.
   *
   * @param key The key to be inserted.
   */
  public void doubleHashInsert(int key) {
      int pos = hashFunc(key);
      int i = 1;
      while (isOccupied(pos)) {
          pos = (hashFunc(key) + i * hashFunc2(key)) % size;;
          if (pos != 0) {
              System.out.println("Collision at position " + (pos - 1) + ". Attempt next position " + pos);
          }
          else {
              System.out.println("Collision at position  19. Attempt next position " + pos);
          }

          i++;
      }
      table[pos] = key;
      occupied[pos] = true; //current index slot is filled by an element
      System.out.println("Key " + key + " inserted at position " + pos + "\n");
  }

  /**
   * Prints the contents of the hash table using linear probing.
   */
  public void printLinearHashTable() {
      System.out.println("Linear Probing Hash Table Result:");
      for (int i = 0; i < size; i++) {
          if (occupied[i]) {
              System.out.println("Index " + i + ": " + table[i]);
          }
          else {
              System.out.println("Index " + i + ": Empty");
          }
      }
      System.out.println();
  }

    /**
     * Prints the contents of the hash table using double hashing.
     */
    public void printDoubleHashTable() {
        System.out.println("Double Hashing Hash Table Result:");
        for (int i = 0; i < size; i++) {
            if (occupied[i]) {
                System.out.println("Index " + i + ": " + table[i]);
            }
            else {
                System.out.println("Index " + i + ": Empty");
            }
        }
        System.out.println();
    }

    /**
     * A utility method to print an array
     *
     * @param array The array to be printed
     */
    private static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.print("]");
    }
    /**
     * Main method for testing and running the Hash Implementation class.
     * Note that Position is synonymous with the index of the Hash Table array
     *
     * @param args Common-line arguments.
     */
    public static void main(String[] args){

        HW4_rci11 hashTable1 = new HW4_rci11(10);

        System.out.println("Linear Probing Steps:");
        hashTable1.linearProbingInsert(14);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(17);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(18);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(3);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(8);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(1);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(18);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(11);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(13);
        hashTable1.printLinearHashTable();

        hashTable1.linearProbingInsert(20);
        hashTable1.printLinearHashTable();

        int[] linearProbingFinal = new int[hashTable1.size];
        for (int i = 0; i < hashTable1.size; i++) {
            linearProbingFinal[i] = hashTable1.table[i];
        }

        System.out.print("Final Linear Probing Hash Table: ");
        printArray(linearProbingFinal);

        HW4_rci11 hashTable2 = new HW4_rci11(20);

        System.out.println("\nDouble Hashing Steps:");
        hashTable2.doubleHashInsert(2);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(12);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(22);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(32);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(42);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(52);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(62);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(72);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(82);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(92);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(14);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(17);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(18);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(3);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(8);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(1);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(18);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(11);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(13);
        hashTable2.printDoubleHashTable();

        hashTable2.doubleHashInsert(20);

        hashTable2.printDoubleHashTable();

        int[] doubleHashingFinal = new int[hashTable2.size];
        for (int i = 0; i < hashTable2.size; i++) {
            doubleHashingFinal[i] = hashTable2.table[i];
        }

        System.out.print("Final Double Hashing Hash Table: ");
        printArray(doubleHashingFinal);

    }
}
