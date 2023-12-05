import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Case Cash system that manages a list of students with their balances.
 *
 * @author Ryan Cesar Irizarry
 */
public class CaseCashSystem {

    private List<Student> students = new ArrayList<Student>(); //The list of students in the system

    /**
     * Gets the list of students.
     *
     * @return The list of students.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Runs a simulation based on a list of commands.
     *
     * @param commands The list of commands to be executed in the simulation.
     * @return A list of outputs corresponding to the executed commands.
     */
    public List<String> runSimulation (List<String> commands) {
        students.clear();
        List<String> output = new ArrayList<>();

        for (String command : commands) {
            String[] parts = command.split(",");

            switch (parts[0]) {
                case "INIT":
                    boolean initResult = init(parts[1], Integer.parseInt(parts[2].trim()));
                    output.add(String.valueOf(initResult));
                    break;
                case "GET":
                    int balance = getBalance(parts[1]);
                    output.add(String.valueOf(balance));
                    break;
                case "TRANSFER":
                    boolean transferResult = transfer(findStudent(parts[1]), findStudent(parts[2]), Integer.parseInt(parts[3].trim()));
                    output.add(String.valueOf(transferResult));
                    break;
                case "WITHDRAWAL":
                    boolean withdrawalResult = withdrawal(findStudent(parts[1]), Integer.parseInt(parts[2].trim()));
                    output.add(String.valueOf(withdrawalResult));
                    break;
                case "DEPOSIT":
                    boolean depositResult = deposit(findStudent(parts[1]), Integer.parseInt(parts[2].trim()));
                    output.add(String.valueOf(depositResult));
                    break;
                case "SORT":
                    String sortType = parts[1];
                    List<String> sortedList = sort(parts[1]);
                    output.add(sortedList.toString());
                    break;
                default:
                    output.add("Invalid command: " + command);
            }
        }
        return output;
    }

    /**
     * Helper method that sorts the list of students based on the specified sort type.
     *
     * @param sortType The type of sorting to be applied ("name" or "balance").
     * @return A sorted list based on the specified sort type.
     * @throws IllegalArgumentException If an invalid sort type is provided.
     */
    private List<String> sort(String sortType) {
        switch (sortType.trim()) {
            case "name":
                return sortName();
            case "balance":
                return sortBalance();
            default:
                throw new IllegalArgumentException("Invalid sort type: " + sortType);
        }
    }

    /**
     * Helper method that finds a student by their name in the list of students.
     *
     * @param name The name of the student to find.
     * @return The student with the specified name, or null if not found.
     */
    private Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Initializes a new student with the given name and initial balance.
     *
     * @param name The name of the new student.
     * @param initialBalance The initial balance of the new student.
     * @return True if the student is successfully initialized, false otherwise.
     */
    public boolean init (String name, int initialBalance) {
        if (initialBalance < 0) {
            return false;
        }
        Student newStudent = new Student(name, initialBalance);
        for (int i = 0; i < students.size(); i++) {
            Student existingStudent = students.get(i);
            if (existingStudent.getName().equals(name)) {
                return false;
            }
        }
        students.add(newStudent);
        return true;
    }

    /**
     * Gets the balance of a student with the specified name.
     *
     * @param name The name of the student.
     * @return The balance of the student, or -1 if the student is not found.
     */
    public int getBalance (String name) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getName().equals(name)) {
                return student.getBalance();
            }
        }
        return -1; //indicates that there is no student in the list with the input name; Balance of a valid student cannot be negative therefore we make this return balance negative to leave no room for confusion that this student does not exist
    }

    /**
     * Deposits a specified amount to the balance of a student.
     *
     * @param student The student to deposit the amount to.
     * @param amount The amount to deposit.
     * @return True if the deposit is successful, false otherwise.
     */
    public boolean deposit (Student student, int amount) {
        if (amount < 0) {
            return false;
        }
        for (int i = 0; i < students.size(); i++) {
            Student existingStudent = students.get(i);
            if (existingStudent.equals(student)) {
                existingStudent.updateBalance(existingStudent.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Transfers a specified amount from one student to another.
     *
     * @param studentA The student to transfer the amount from.
     * @param studentB The student to transfer the amount to.
     * @param amount The amount to transfer.
     * @return True if the transfer is successful, false otherwise.
     */
    public boolean transfer(Student studentA, Student studentB, int amount){
        if (amount < 0) {
            return false;
        }
        if ((studentA.getBalance() - amount) < 0) {
            return false;
        }
        boolean validTransfer = deposit(studentB, amount);
        if (validTransfer) {
            studentA.updateBalance(studentA.getBalance() - amount);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sorts the list of students by their names using the merge sort algorithm.
     *
     * @return A sorted list of student names.
     */
    public List<String> sortName() {
        List<String> allNames = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            allNames.add(student.getName());
        }
        mergeSort(allNames);
        return allNames;
    }

    /**
     * Helper method that performs the merge sort algorithm on a list of strings.
     *
     * @param list  The list of strings to be sorted.
     */
    private static void mergeSort(List<String> list) {
        if (list.size() == 1) {
            return;
        }
        int middle = list.size() / 2;
        List<String> leftList = new ArrayList<>(list.subList(0, middle));
        List<String> rightList = new ArrayList<>(list.subList(middle, list.size()));
        mergeSort(leftList);
        mergeSort(rightList);
        merge(list, leftList, rightList);
    }


    /**
     * Helper method that merges two sorted lists into a single sorted list.
     *
     * @param list The list to be merged into.
     * @param leftList The left sorted list.
     * @param rightList The right sorted list.
     */
    private static void merge(List<String> list, List<String> leftList, List<String> rightList) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) < 0) {
                list.set(k, leftList.get(i));
                i++;
            }
            else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftList.size()) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightList.size()) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    /**
     * Sorts the list of students by their balances using the quicksort algorithm.
     *
     * @return A sorted list of student names based on their balances.
     */
    public List<String> sortBalance() {
        List<Student> sortStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sortStudents.add(student);
        }
        myQuickSort(sortStudents, 0, sortStudents.size() - 1);

        List<String> sortNames = new ArrayList<>();
        for (int i = 0; i < sortStudents.size(); i++) {
            Student student = sortStudents.get(i);
            sortNames.add(String.valueOf(student.getName()));
        }
        return sortNames;
    }

    /**
     * Helper method that performs the quicksort algorithm on a list of students based on their balances.
     *
     * @param list The list of students to be sorted.
     * @param first The index of the first element in the list.
     * @param last The index of the last element in the list.
     */
    private static void myQuickSort(List<Student> list, int first, int last) {
        if (first >= last)
            return;
        int split = partition(list, first, last);
        myQuickSort(list, first, split);
        myQuickSort(list, split + 1, last);
    }

    /**
     * Helper method that Partitions a list of students based on their balances for the quicksort algorithm.
     *
     * @param list The list of students to be partitioned.
     * @param first The index of the first element in the list.
     * @param last The index of the last element in the list.
     * @return The index where the list is partitioned.
     */
    private static int partition(List<Student> list, int first, int last) {
        int pivot = list.get((first + last) / 2).getBalance();
        int i = first - 1;
        int j = last + 1;

        while (true) {
            do {
                i++;
            } while (list.get(i).getBalance() < pivot);
            do {
                j--;
            } while (list.get(j).getBalance() > pivot);
            if (i < j) {
                swap(list, i, j);
            } else {
                return j;
            }
        }
    }


    /**
     * Swaps two elements in a list of students.
     *
     * @param list The list of students where the swap will occur.
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    private static void swap(List<Student> list, int i, int j) {
        Student temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }


    /**
     * Withdraws a specified amount from a student's balance.
     *
     * @param student The student from whom the withdrawal will be made.
     * @param amount The amount to be withdrawn.
     * @return True if the withdrawal is successful, false otherwise.
     */
    public boolean withdrawal (Student student, int amount) {
        boolean validWithdrawal = (student.getBalance() - amount) >= 0;
        if (validWithdrawal) {
            student.updateBalance(student.getBalance() - amount);
            return true;
        }
        return false;
    }

    /**
     * The main method to run a simulation of the CaseCashSystem.
     *
     * @param args Command-line arguments
     */
    public static void main (String[] args) {
        CaseCashSystem cashSystem = new CaseCashSystem();
        List<String> inputs = List.of(
                "INIT, Tammy, 200",
                "INIT, Kim, 300",
                "INIT, Quyen, 400",
                "SORT, name",
                "SORT, balance",
                "TRANSFER, Kim, Tammy, 100",
                "SORT, name",
                "SORT, balance"
        );
        List<String> outputs = cashSystem.runSimulation(inputs);
        System.out.println(outputs);
    }
}
