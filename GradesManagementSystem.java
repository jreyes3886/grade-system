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
            choice = input.readLine().trim().toLowerCase();
            switch (choice) { // get the input from choice
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
                    deleteStudent();
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
            studentName[studentCount] = name; // gets the student name in the specified row
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
        studentCount++; // goes to another student if applicable, counts the students and loops
    }

    static void printStudent(int i) { // method that gets the current array/row
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

    static double average(double[] grades) { // gets all the grade by looping
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

    // input validation, (String variable) means displaying the sysout
    static String onlyLetters(String name) throws Exception {
        String letter = "";
        while (true) {
            System.out.print(name);
            letter = input.readLine().trim();
            if (letter.matches("[a-zA-Z ]+")) {
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

    static String onlySubject(String name) throws Exception {
        String letter = "";
        while (true) {
            System.out.print(name);
            letter = input.readLine().trim();
            if (letter.equalsIgnoreCase("cancel")) {
                System.out.println("Cancelled. No inputs were saved.");
                return null;
            }
            if (letter.matches("[a-zA-Z0-9 &]+")) {
                return letter;
            } else {
                System.out.println("Invalid input. Please enter letters, numbers and symbols only (A-Z).");
            }
        }
    }

    static int onlySubjectAmount(String prompt) throws Exception {
        while (true) {
            System.out.print(prompt);
            String text = input.readLine().trim();
            if (text.matches("[1-9]")) {
                return Integer.parseInt(text);
            }
            System.out.println("Invalid input. Enter subjects ranging from 1 to 9");
        }
    }

    static double onlyGrades(String grades) throws Exception {
        double value;
        while (true) {
            System.out.print(grades);
            String number = input.readLine().trim();

            if (number.equalsIgnoreCase("cancel")) {
                return -1;
            }
            if (number.matches("(\\d+(\\.\\d*)?|\\.\\d+)")) {
                value = Double.parseDouble(number);

                if (value >= 0.0 && value <= 100.0) {
                    return value;
                }
                System.out.println("Value must be between 0 and 100.");
            } else {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    static int selectStudent() throws Exception {
        String name = onlyLetters("Enter student name: "); // calls onlyLetters method for input
        name = name.toLowerCase();
        int matches[] = new int[studentCount]; // create new array for matching names
        int matchCount = 0;
        for (int i = 0; i < studentCount; i++) { // counts all students
            if (studentName[i].toLowerCase().startsWith(name)) { // if any student name starts with the user input
                matches[matchCount] = i; // stores the row or remembers
                matchCount++; // counts in every store
            }
        }
        if (matchCount == 0) { // if there is none
            System.out.println("Student not found.");
            return -1; // doesnt exist
        }
        if (matchCount == 1) { // if there is one
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
        } while (again.equals("y"));
        pause();
    }

    static void addStudent() throws Exception {
        if (studentCount < maxStudents) {
            String name = onlyLetters("Enter student name: ");
            int numOfSubs = onlySubjectAmount("Enter amount of subjects: ");
            String[] subs1 = new String[numOfSubs];
            double[] g1 = new double[numOfSubs];
            String[] subs2 = new String[numOfSubs];
            double[] g2 = new double[numOfSubs];
            System.out.println("SEMESTER 1:");
            System.out.println("TYPE CANCEL TO CANCEL");
            for (int i = 0; i < numOfSubs; i++) {
                subs1[i] = onlySubject("Enter Subject Name: ");
                if (subs1[i] == null) {
                    System.out.println("Cancelled. No inputs were saved.");
                    pause();
                    return;
                }
                g1[i] = onlyGrades("Enter grade: ");
                if (g1[i] == -1) {
                    System.out.println("Cancelled. No inputs were saved.");
                    pause();
                    return;
                }
            }
            for (int j = 0; j < numOfSubs; j++) {
                subs2[j] = onlySubject("Enter Subject Name: ");
                if (subs2[j] == null) {
                    System.out.println("Cancelled. No inputs were saved.");
                    pause();
                    return;
                }
                g2[j] = onlyGrades("Enter grade: ");
                if (g2[j] == -1) {
                    System.out.println("Cancelled. No inputs were saved.");
                    pause();
                    return;
                }
            }
            pause();
            System.out.println("Student saved.");
            saveStudent(name, subs1, g1, subs2, g2);
        }
    }

    static void updateStudent() throws Exception {
        System.out.println("Not implemented yet.");
        pause();
    }

    static void deleteStudent() throws Exception {
        System.out.println("Not implemented yet.");
        pause();
    }

}
