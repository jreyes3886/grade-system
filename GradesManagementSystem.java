import java.io.*;

public class GradesManagementSystem {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int maxStudents = 50;
    static int maxSubjects = 9; // this means the user can only input max subjects (9)
    static double blank = -1; // -1 in array means nothing, this is used when the student wasnt found or
                              // cancelled or blank grades
    static String[] studentName = new String[maxStudents]; // our limit to store is 50 students, may change
    static double[][] sem1 = new double[maxStudents][maxSubjects]; // for grades of first sem
    static double[][] sem2 = new double[maxStudents][maxSubjects]; // for grades of second sem
    static String[][] sem1Subjects = new String[maxStudents][maxSubjects]; //for subjects of first sem
    static String[][] sem2Subjects = new String[maxStudents][maxSubjects]; //for subjects of second sem
    static int studentCount = 0;

    public static void main(String[] args) throws Exception {
        String choice;
        do {
            showMenu();
            choice = readChoice(); // calls readChoice function
            switch (choice) { // get the input from readChoice
                case "1": // if the input is 1 or display
                case "display":
                    displayAll(); // calls displayAll() function/method
                    break;
                case "2":
                case "search":
                    searchStudent();
                    break;
                case "3":
                case "update":
                    updateStudent();
                    break;
                case "4":
                case "add":
                    addStudent();
                    break;
                case "5":
                case "delete":
                    deleteGrade();
                    break;
                case "0":
                case "exit":
                    System.out.println("Program Terminated.");
                default: // if none is met, the loop replays
                    System.out.println("Invalid Choice.");
                    pause();
            }
        } while (!choice.equals("0") && !choice.equals("exit"));
    }

    static void showMenu() {
        System.out.print("========================================================\n");
        System.out.print("         GRADE AND GRADE VIEWING SYSTEM\n");
        System.out.println("========================================================\n");
        System.out.println("1. Display Student Grades");
        System.out.println("2. Search Student Grades");
        System.out.println("3. Update Student Grades");
        System.out.println("4. Add Student Grades");
        System.out.println("5. Delete Student Grades");
        System.out.println("0. Exit");
        System.out.print("\nEnter menu number: ");
    }

    static String readChoice() throws Exception {
        return input.readLine().trim().toLowerCase();
    }

    static void pause() throws Exception {
        System.out.println("Press Enter To Continue.");
        input.readLine();
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
    //preloaded names
    static void preLoaded() throws Exception {
        saveStudent("Juan Dela Cruz",
                new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
                new double[] { 90, 85, 92, 88 },
                new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
                new double[] { 87, 95, 83, 93 });
    }
    //save students grades
    static void saveStudent(String name, String[] subs1, double[] g1, String[] subs2, double[] g2) throws Exception {
        studentName[studentCount] = name; //gets the amount of the current students
        for (int i = 0; i < maxSubjects; i++) {
            if (i < g1.length) { //if i is less than first semester length
                sem1Subjects[studentCount][i] = subs1[i]; //stores in string subject index
                sem1[studentCount][i] = g1[i]; //stores in grade index
            } else {
                sem1Subjects[studentCount][i] = ""; //becomes blank
                sem1[studentCount][i] = blank; //blank = -1, in array -1 means nothing
            }
            if (i < g2.length) { //second sem
                sem2Subjects[studentCount][i] = subs2[i];
                sem2[studentCount][i] = g2[i];
            } else {
                sem2Subjects[studentCount][i] = "";
                sem2[studentCount][i] = blank;
            }
        }
        studentCount++; // goes to another student if applicable and loops
    }

    static void displayAll() throws Exception {
        System.out.println("GRADE RECORDS");
        System.out.println("===============================================");
        if (studentCount == 0) { //if theres no student records
            System.out.println("No Student Records Yet.");
            pause();
            return;
        }
        for (int i = 0; i < studentCount; i++) { //loops to display all students
            System.out.println("Student: " + (i + 1) + ": " + studentName[i]);
            System.out.println("----------------------------------------------");

            System.out.println("SEMESTER 1");
            System.out.printf("%-30s%s%n", "SUBJECTS", "GRADES"); // means exactly 30 spaces
            for (int j = 0; j < maxStudents; j++) {
                if (sem1[i][j] != blank) { //if student records exists
                    System.out.printf("%-30s%s%n", sem1Subjects[i][j], sem1[i][j]); //prints all subjects and average grades
                }
            }
            System.out.println("Average" + average(sem1[i])); //calls the average function and prints average 
            System.out.println("SEMESTER 2");
            System.out.printf("%-30s%s%n", "SUBJECTS", "GRADES");
            for (int j = 0; j < maxStudents; j++) {
                if (sem2[i][j] != blank) {
                    System.out.printf("%-30s%s%n", sem2Subjects[i][j], sem2[i][j]);
                }
            }
            System.out.println("Average" + average(sem2[i]));
        }
    }

    static double average(double[] grades) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < maxSubjects; i++) {
            if (grades[i] != blank) { //if the grades exist
                sum += grades[i]; //adds grades each in that array
                count++; //count every loop or array
            }
        }
        if (count == 0) { //if the grades doesn't exist
            return 0; // returns nothing
        }
        return sum / count; //if grades exist the sum of grades is divided by the number of arrays
    }
}
