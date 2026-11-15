import java.io.*;

public class GradesManagementSystem {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int maxStudents = 50;
    static int maxSubjects = 9; // this means the user can only input a minimum of max subjects
    static double blank = -1; //-1 in array means nothing, this is used when the student wasnt found or cancelled or blank grades
    static String[] studentName = new String[maxStudents]; //our limit to store is 50 students, may change
    static double[][] sem1 = new double[maxStudents][maxSubjects]; //for first semester in grades
    static double[][] sem2 = new double[maxStudents][maxSubjects]; //for second semester 

    public static void main(String[] args) throws Exception {
        String choice;
        do {
            showMenu();
            choice = readChoice(); //calls readChoice function
            switch (choice) { // get the input from readChoice
                case "1":
                case "add":
                    addGrade();
                    break;
                case "2":
                case "search":
                    searchStudent();
                    break;
                case "3":
                case "display":
                    displayAll();
                    break;
                case "4":
                case "update":
                    updateStudent();
                    break;
                case "5":
                case "delete":
                    deleteStudent();
                    break;
                case "0":
                case "exit":
                    System.out.println("Program Terminated.");
                default: // if none is met, the loop is terminated again
                    System.out.println("Invalid Choice.");
            }
        } while (!choice.equals("0") && !choice.equals("exit"));
    }
static void showMenu() {
    System.out.print("========================================================\n");
        System.out.print("         GRADE AND GRADE VIEWING SYSTEM\n");
        System.out.println("========================================================\n");
        System.out.println("1. Display Grade Records");
        System.out.println("2. Search Grade Records");
        System.out.println("3. Update Grade Records");
        System.out.println("4. Add Grade Records");
        System.out.println("5. Delete Grade Records");
        System.out.println("0. Exit");
        System.out.print("\nEnter menu number: ");
    }
    static String readChoice() throws Exception {
        return input.readLine().trim().toLowerCase();
    }
     static void addGrade() throws Exception {
        System.out.println("hi");
    }
}
