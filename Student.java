/**
 * Represents a student with a name and a balance.
 *
 * @author Ryan Cesar Irizarry
 */
public class Student {

    private String name; //The name of the student

    private int balance; //The balance of the student.

    /**
     * Constructs a new student with the specified name and initial balance.
     *
     * @param name The name of the student.
     * @param balance The initial balance of the student.
     */
    public Student(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Gets the name of the student.
     *
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the balance of the student.
     *
     * @return The balance of the student.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Updates the balance of the student to the specified new amount.
     *
     * @param newAmount The new balance to set for the student.
     */
    public void updateBalance(int newAmount) {
        this.balance = newAmount;
    }

}
