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
    static String[][] sem1Subjects = new String[maxStudents][maxSubjects]; // for subjects of first sem
    static String[][] sem2Subjects = new String[maxStudents][maxSubjects]; // for subjects of second sem
    static int studentCount = 0;

    public static void main(String[] args) throws Exception {
        preLoaded();
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
                    break;
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
        System.out.print("Press Enter To Continue.");
        input.readLine();
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    // preloaded names
    static void preLoaded() throws Exception {
        saveStudent("Juan Dela Cruz",
                new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
                new double[] { 90, 85, 92, 88 },
                new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
                new double[] { 87, 95, 83, 93 });
        saveStudent("Julian Dela Cruz",
                new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
                new double[] { 88, 90, 85, 91 },
                new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
                new double[] { 89, 92, 86, 90 });
        saveStudent("Maria Santos",
            new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
            new double[] { 95, 89, 94, 90 },
            new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
            new double[] { 92, 88, 96, 91 });
        saveStudent("Samantha Villanueva",
            new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
            new double[] { 96, 93, 90, 95 },
            new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
            new double[] { 94, 97, 92, 96 });
         saveStudent("Nathaniel Cruz",
            new String[] { "General Mathematics", "Modern Physics", "Communication", "Filipino" },
            new double[] { 85, 92, 88, 90 },
            new String[] { "Statistics & Probability", "Biology", "Speech Styles", "Pagpapahalaga sa Filipino" },
            new double[] { 89, 94, 86, 91 });
    }

    // save students grades
    static void saveStudent(String name, String[] subs1, double[] g1, String[] subs2, double[] g2) throws Exception {
        if (studentCount < maxStudents) {
            studentName[studentCount] = name; // gets the amount of the current students
        }
        for (int i = 0; i < maxSubjects; i++) {
            if (i < g1.length) { // if i is less than first semester length
                sem1Subjects[studentCount][i] = subs1[i]; // stores in string subject index
                sem1[studentCount][i] = g1[i]; // stores in grade index
            } else {
                sem1Subjects[studentCount][i] = ""; // becomes blank
                sem1[studentCount][i] = blank; // blank = -1, in array -1 means nothing
            }
            if (i < g2.length) { // second sem
                sem2Subjects[studentCount][i] = subs2[i];
                sem2[studentCount][i] = g2[i];
            } else {
                sem2Subjects[studentCount][i] = "";
                sem2[studentCount][i] = blank;
            }
        }
        studentCount++; // goes to another student if applicable and loops
    }

    static void printStudent(int i) { // method that gets the current array/student
        System.out.println("-----------------------------------------------");

        System.out.println("SEMESTER 1");
        System.out.printf("%-30s%s%n", "SUBJECTS", "GRADES"); // %-30s%%s%n means exactly 30 spaces
        for (int j = 0; j < maxSubjects; j++) {
            if (sem1[i][j] != blank) { // if student records exists
                System.out.printf("%-30s%s%n", sem1Subjects[i][j], sem1[i][j]); // prints all subjects and grades
            }
        }
        System.out.printf("Average: %.2f%n", average(sem1[i])); // calls the average function and prints average
        System.out.println();

        System.out.println("SEMESTER 2");
        System.out.printf("%-30s%s%n", "SUBJECTS", "GRADES");
        for (int j = 0; j < maxSubjects; j++) {
            if (sem2[i][j] != blank) {
                System.out.printf("%-30s%s%n", sem2Subjects[i][j], sem2[i][j]);
            }
        }
        System.out.printf("Average: %.2f%n", average(sem2[i]));
        System.out.println();
        System.out.println("===============================================");
    }

    static void displayAll() throws Exception {
        System.out.println("GRADE RECORDS");
        System.out.println("===============================================");
        if (studentCount == 0) { // if theres no student records
            System.out.println("No Student Records Yet.");
            return; // ends
        }
        for (int i = 0; i < studentCount; i++) { // loops to display all students
            System.out.println("Student: " + (i + 1) + ": " + studentName[i]);
            printStudent(i); // calls printstudent function and send its current array
        }
        pause();
    }

    static double average(double[] grades) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < maxSubjects; i++) {
            if (grades[i] != blank) { // if the grades exist
                sum += grades[i]; // adds grades each in that array
                count++; // count every loop
            }
        }
        if (count == 0) { // if the grades doesn't exist
            return 0; // returns nothing
        }
        return sum / count; // if grades exist the sum of grades is divided by the number of arrays or count
    }
    static String onlyLetters(String name) throws Exception {
        String letter = "";
        while (true) {
            System.out.print(name);
            letter = input.readLine().trim().toLowerCase();
            if (letter.matches("[a-zA-Z]+")) {
                return letter;
            } else {
                System.out.println("Invalid input. Please enter letters only (A-Z).");
            }
        }
    }
    static String onlyNumbers(String number) throws Exception {
         while (true) {
            System.out.print(number);
            String numberInput = input.readLine();

            // "\\d+" checks that the string has 1 or more digits (0-9) and nothing else
            if (numberInput.matches("\\d+")) {
                return numberInput;
            } else {
                System.out.println("Invalid input. Please enter digits only (0-9).");
            }
        }

    }
    static int selectStudent() throws Exception {
        String name = onlyLetters("Enter student name: ");
        int matches[] = new int[studentCount]; // create new array for matching names
        int matchCount = 0;
        for (int i = 0; i < studentCount; i++) { // counts all students
            if (studentName[i].toLowerCase().startsWith(name)) { // if any student name starts with the user input
                matches[matchCount] = i; // stores the row or remembers
                matchCount++; // counts in every store
            }
        }
        if (matchCount == 0) {
            System.out.println("Student not found.");
            return -1; // doesnt exist
        }
        if (matchCount == 1) {
            System.out.println("Student found!");
            return matches[0]; // doesn't need to ask
        }
        System.out.println("Multiple students found.");
        for (int j = 0; j < matchCount; j++) {
            System.out.println((j + 1) + ". " + studentName[matches[j]]);
        }
        System.out.println("0. Cancel");
        String answer = onlyNumbers("Pick a number: ");

        for (int k = 0; k < matchCount; k++) {
            if (answer.equals(String.valueOf(k + 1))) {
                return matches[k];
            }
        }
        System.out.println("Cancelled.");
        return -1;
    }

    static void searchStudent() throws Exception {
        int i;
        String again;
        do {
            i = selectStudent();
        if (i != -1) {
            System.out.println("\nStudent: " + studentName[i]);
            printStudent(i);
            break;
        }
       System.out.print("Try again? (y/n): ");
       again = input.readLine().trim().toLowerCase();
    } while(again.equals("y"));
    pause();
}

    static void deleteGrade() throws Exception {
        System.out.println("Not implemented yet.");
        pause();
    }

    static void updateStudent() throws Exception {
        System.out.println("Not implemented yet.");
        pause();
    }

    static void addStudent() throws Exception {
        System.out.println("Not implemented yet.");
        pause();
    }
}
