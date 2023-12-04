/** 
 * @author Ryan Cesar Irizarry
 */
import java.util.Scanner;

/**
 * This class provides various utility methods for working with strings.
 */
public class Assignment1 extends Object {
  
  /**
   * Checks if a given string is a palindrome using an iterative approach.
   * Time Complexity: O(N)
   * 
   * @param input The input string to check.
   * @return True if the input string is a palindrome, false otherwise.
   */
  public static boolean palindromeIterative(String input) {
    String newString = new String();
    for (int i = 0; i < input.length(); i++) {
      if (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i))) {
        newString = newString + Character.toLowerCase(input.charAt(i));
      }
    }
    int left = 0; //leftmost character
    int right = newString.length() - 1; //rightmost character
    while (left < right) {
      if (newString.charAt(left) != newString.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
  
  /**
   * Checks if a given string is a palindrome using a recursive approach.
   * Time Complexity: O(N).
   * 
   * @param input The input string to check.
   * @return True if the input string is a palindrome, false otherwise.
   */
  public static boolean palindromeRecursive(String input) {
    String newString = new String();
    for (int i = 0; i < input.length(); i++) {
      if (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i))) {
        newString = newString + Character.toLowerCase(input.charAt(i));
      }
    }
    if (newString.length() == 0 || newString.length() == 1) 
      return true;
    if (newString.charAt(0) == newString.charAt(newString.length() - 1))
      return palindromeRecursive(newString.substring(1, newString.length() - 1));
    return false;
  }
  
  /** Checks if two string are anagrams of each other.
    * Time Complexity: O(N)
    * 
    * @param x The first string.
    * @param y The second string.
    * @return True if the strings are anagrams, false otherwise.
    */
  public static boolean anagramChecker(String x, String y) {
    if (x.length() != y.length())
      return false;
    char[] xList = x.toCharArray();
    char[] yList = y.toCharArray();
    int xCounter = 0;
    int yCounter = 0;
    for (int i = 0; i < xList.length; i++) {
      xCounter = xCounter + (int) xList[i]; //obtains the ASCII values of the elements in xList
      yCounter = yCounter + (int) yList[i]; //obtains the ASCII values of the elements in yList
    }
    if (xCounter != yCounter) //total ASCII are not equivalent
      return false;
    else return true;                          
  }
  
  /**
   * Adds a substring to a given input string at the specified index.
   * Time Complexity: O(N)
   * 
   * @param input The input string to which the substring is added.
   * @param substring The substring to be added.
   * @param index The index at which the substring should be added.
   * @return The resulting string after adding the substring.
   * @throws IllegalArgument Exception If the index is out of bounds.
   */
  public static String addSubstring(String input, String substring, int index) {
    StringBuilder finalString = new StringBuilder();
    if (index < 0 || index > input.length()) 
      throw new IllegalArgumentException("Index cannot be applied to input string");
    for (int i = 0; i < input.length(); i++) {
        finalString.append(input.charAt(i));
        if (i == index - 1)
          finalString.append(substring);
    }
    return finalString.toString();
  }
  
  /**
   * Gets the length of a given input string.
   * Time Complexity: O(N)
   * 
   * @param input The input string. 
   * @return The length of the input string.
   */
  public static int getLength(String input) {
    char[] sList = input.toCharArray();
    char[] finalList = new char[input.length()];
    for (int i = 0; i < finalList.length; i++) {
      finalList[i] = sList[i];
    }
    return finalList.length;
  }
  
  /**
   * Counts the number of ocurrances of a substring in a given input string.
   * Time Complexity: O(N)
   * 
   * @param input The input string to search in.
   * @param substring The substring to search for.
   * @return The number of occurances of the substring.
   */
  public static int occuranceCounter(String input, String substring) {
    if (input.isEmpty() || substring.isEmpty())
      return 0;
    else {
      String[] section = input.split(substring, -1);
      return section.length - 1; //each element represents each smaller section of the string after it has been broken up via the split(), the amount of sections will always be 1 greater than the occurances of the substring which caused the splits  
    }  
  }
  
  /**
   * Reverses the words in a given input sentence.
   * Time Complexity: O(N)
   * 
   * @param input The input sentence.
   * @return The reversed sentence.
   */
  public static String sentenceReversal(String input) {
    String newString = new String(input.substring(0, input.length() - 1));
    String[] section = newString.split(" ");
    String[] newList = new String[section.length];
    String firstString = new String();
    String secondString = String.valueOf(input.charAt(input.length() - 1)); //holds the punctuation ( "." or "?" or "!" ) so that it can be appended to the final result
    for (int i = 0; i < section.length; i++) {
      newList[i] = section[section.length - (i + 1)];
      firstString = firstString + newList[i];
      if (i < newList.length - 1) {
        firstString = firstString + " ";
      } 
    }
    return firstString + secondString;
  }
  
  /**
   * The main entry point of the application. Creates a menu from where the user can select options.
   * 
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    boolean loopThrough = true;
    System.out.println("Welcome to the App");
    System.out.println("1. Palindrome Check");
    System.out.println("2. Anagram Check");
    System.out.println("3. Add Substring");
    System.out.println("4. Get Length");
    System.out.println("5. Count Occurances");
    System.out.println("6. Reverse Sentence");
    System.out.println("7. Quit");
    while (loopThrough == true){
      System.out.println("Choose an option:\n");
      
      Scanner menuOption = new Scanner(System.in);
      String option = menuOption.nextLine();
      
      switch (option) {
        case "1":
          System.out.println("Please enter a string to check for palindrome: ");
          Scanner methodOption = new Scanner(System.in);
          String palinCheck = methodOption.nextLine();
          Boolean bool = palindromeRecursive(palinCheck);
          System.out.println(bool);
          break;
          
        case "2":
          System.out.println("Enter string: ");
          Scanner methodOption2 = new Scanner(System.in);
          String anagCheckFirstString = methodOption2.nextLine();
          System.out.println("String to compare in order to determine valid anagram: ");
          String anagCheckSecondString = methodOption2.nextLine();
          Boolean bool2 = anagramChecker(anagCheckFirstString, anagCheckSecondString);
          System.out.println(bool2);
          break;
          
        case "3":
          System.out.println("Enter string:");
          Scanner methodOption3 = new Scanner(System.in);
          String addSubCheckFirst = methodOption3.nextLine();
          System.out.println("Substring to be inserted:");
          String addSubCheckSecond = methodOption3.nextLine();
          System.out.println("Index placement:");
          String addSubCheckIndex = methodOption3.nextLine();
          int index = Integer.valueOf(addSubCheckIndex);
          String stringResult = addSubstring(addSubCheckFirst, addSubCheckSecond, index);
          System.out.println("New string:");
          System.out.println(stringResult);
          break;
          
        case "4":
          System.out.println("Please enter a string in order to obtain its length: ");
          Scanner methodOption4 = new Scanner(System.in);
          String getLengthCheck = methodOption4.nextLine();
          int intResult = getLength(getLengthCheck);
          System.out.println(intResult);
          break;
          
        case "5":
          System.out.println("Enter string: ");
          Scanner methodOption5 = new Scanner(System.in);
          String occuranceCountCheckFirst = methodOption5.nextLine();
          System.out.println("Substring to be searched for in respect to its amount of occurances: ");
          String occuranceCountCheckSecond = methodOption5.nextLine();
          int occurancesResult = occuranceCounter(occuranceCountCheckFirst, occuranceCountCheckSecond);
          System.out.println(occurancesResult);
          break;
          
        case "6": 
          System.out.println("Enter a sentence: ");
          Scanner methodOption6 = new Scanner(System.in);
          String sentenceRevCheck = methodOption6.nextLine();
          String stringResult6 = sentenceReversal(sentenceRevCheck);
          System.out.println(stringResult6);
          break;
          
        case "7":
          loopThrough = false;
          break;
      }
    }
    System.out.println(" Have a nice day :)");
  }
  
  /**
   * Resources Used:
   * w3schools - java
   * StackOverflow
   * Geeks for Geeks
   */
}