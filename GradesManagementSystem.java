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
                    break; // stops loop and vice versa
                case "2":
                case "search":
                    searchStudent();
                    break;
                case "3":
                case "add":
                    addStudent();
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
        System.out.println("3. Add Student Grades");
        System.out.println("4. Update Student Grades");
        System.out.println("5. Delete Student Grades");
        System.out.println("0. Exit");
        System.out.print("\nEnter menu number: ");
    }

    static void pause() throws Exception {
        System.out.print("Press Enter To Continue.");
        input.readLine();
    }

    // preloaded names
    static void preLoaded() throws Exception {
        saveStudent("Juan Dela Cruz",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 90, 85, 92, 88, 84, 97, 89 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 87, 95, 83, 93, 89, 92, 86 });
        saveStudent("Julian Dela Cruz",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 88, 90, 85, 91, 81, 95, 88 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 89, 92, 86, 90, 93, 81, 96 });
        saveStudent("Maria Santos",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 95, 89, 94, 90, 85, 90, 97 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 92, 88, 96, 91, 94, 88, 95 });
        saveStudent("Samantha Villanueva",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 96, 93, 90, 95, 89, 92, 87 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 94, 97, 92, 96, 90, 85, 93 });
        saveStudent("Nathaniel Cruz",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 85, 92, 88, 90, 84, 96, 88 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 89, 94, 86, 91, 93, 87, 90 });
        saveStudent("Gehlee Dangca",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 87, 94, 89, 96, 89, 91, 88 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 89, 94, 86, 91, 96, 94, 90 });
        saveStudent("Jeo Ong",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 91, 84, 98, 87, 93, 82, 95 },
                new String[] { "Basic Calculus", "Earth Science", "General Biology 2", "General Chemistry 2",
                        "General Physics 2", "Practical Research 2", "Work Immersion" },
                new double[] { 86, 92, 97, 81, 89, 95, 84 });
        saveStudent("Primo Alterado",
                new String[] { "Pre-Calculus", "Probability and Statistics", "General Biology 1", "General Chemistry 1",
                        "General Physics 1", "Practical Research 1", "Disaster Readiness and Risk Reduction", },
                new double[] { 71, 73, 82, 96, 89, 91, 85 },
                new String[] { "Pre-Calculus", "Probability and Statistics", "Basic Calculus", "Earth Science",
                        "General Biology 2", "General Chemistry 2", "General Physics 2", "Practical Research 2",
                        "Work Immersion" },
                new double[] { 83, 85, 83, 97, 88, 92, 90, 95, 86 });

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
        System.out.printf("%-45s%s%n", "SUBJECTS", "GRADES"); // %-30s%%s%n means exactly 30 spaces
        for (int j = 0; j < maxSubjects; j++) {
            if (sem1[i][j] != blank) { // if student records exists
                System.out.printf("%-45s%s%n", sem1Subjects[i][j], sem1[i][j]); // prints all subjects and grades
            }
        }
        System.out.printf("Average: %.2f%n", average(sem1[i])); // calls the average function and prints average
        System.out.println();

        System.out.println("SEMESTER 2");
        System.out.printf("%-45s%s%n", "SUBJECTS", "GRADES");
        for (int j = 0; j < maxSubjects; j++) {
            if (sem2[i][j] != blank) {
                System.out.printf("%-45s%s%n", sem2Subjects[i][j], sem2[i][j]);
            }
        }
        System.out.printf("Average: %.2f%n", average(sem2[i]));
        double genAve = (average(sem1[i]) + average(sem2[i])) / 2;
        System.out.printf("General Average: %.2f%n", genAve);
        System.out.println();
        System.out.println("===============================================");
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
            letter = input.readLine().trim(); // remove white spaces
            if (letter.matches("[a-zA-Z ]+")) { // only accepts letters
                return letter; // returns and the condition is now false
            } else { // if invalid loops again
                System.out.println("Invalid input. Please enter letters only (A-Z).");
            }
        }
    }

    static String onlyNumbers(String number) throws Exception {
        while (true) {
            System.out.print(number); // gets the prompt of number
            String numberInput = input.readLine();

            // "\\d+" checks that the string has 1 or more digits (0-9) and nothing else
            if (numberInput.matches("\\d+")) {
                return numberInput; // returns and the condition is false
            } else { // if invalid, loops again
                System.out.println("Invalid input. Please enter numbers only.");
            }
        }
    }

    static String onlySubject(String name) throws Exception {
        String letter = "";
        while (true) {
            System.out.print(name); // gets the prompt of subjects
            letter = input.readLine().trim(); // remove white spaces
            if (letter.equalsIgnoreCase("cancel")) { // if the user cancels
                System.out.println("Cancelled. No inputs were saved.");
                return null; // returns and the condition is false
            }
            if (letter.matches("[a-zA-Z0-9 &]+")) { // if its letters, numbers and symbols
                return letter; // returns and the condition is false
            } else { // if its invalid, loops again since the condition is true
                System.out.println("Invalid input. Please enter letters, numbers and symbols only (A-Z).");
            }
        }
    }

    static int onlySubjectAmount(String prompt) throws Exception {
        while (true) { // loops until it is a valid value
            System.out.print(prompt); // geths the prompt of amount of subjects
            String text = input.readLine().trim(); // read and remove white spaces
            if (text.matches("[1-9]")) { // if the input is 1-9
                return Integer.parseInt(text); // convert it to integer and return from where its called
            }
            System.out.println("Invalid input. Enter subjects ranging from 1 to 9"); // if the input is invalid
        }
    }

    static double onlyGrades(String grades) throws Exception {
        double value;
        while (true) { // loops until the input is a valid value
            System.out.print(grades); // gets the prompt of grades
            String number = input.readLine().trim(); // read and remove white spaces

            if (number.equalsIgnoreCase("cancel")) { // if the user cancles
                System.out.println("Cancelled. No inputs were saved.");
                return -1; // return no position and becomes false
            }
            if (number.matches("(\\d+(\\.\\d*)?|\\.\\d+)")) { // if the number matches any number without letters, or
                                                              // symbols
                value = Double.parseDouble(number); // converts the string into double and saves it to valye

                if (value >= 0.0 && value <= 100.0) { // if the value is greater than 0.0 AND less than 100.0
                    return value; // returns the value from where its valled and becomes false
                }
                System.out.println("Value must be between 0 and 100."); // if it exceeds 100 or its negative loops again
            } else {
                System.out.println("Invalid input. Enter a valid number."); // if its symbol or letter loops again
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
        for (int j = 0; j < matchCount; j++) { // gets every match in the first for loop
            System.out.println((j + 1) + ". " + studentName[matches[j]]); // display every match
        }
        System.out.println("0. Cancel");
        String answer = onlyNumbers("Pick a number: "); // calls onlyNumber function

        for (int k = 0; k < matchCount; k++) {
            if (answer.equals(String.valueOf(k + 1))) { // onlyNumbers returns string, so if the answer is equal to the
                                                        // value of the row k + 1 (since int k = 0)
                return matches[k]; // returns the row
            }
        }
        System.out.println("Cancelled.");
        return -1;
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

    static void searchStudent() throws Exception {
        int i;
        String again;
        do {
            i = selectStudent(); // gets the selected student
            if (i != -1) { // if it exists
                System.out.println("\nStudent: " + studentName[i]); // display the student row
                printStudent(i); // display student info
                break; // stops the do while loop
            }
            System.out.print("Try again? (y/n): "); // if it cancels gives an option to try again
            again = input.readLine().trim().toLowerCase(); // removes whitespaces and converts to lowercase
        } while (again.equals("y")); // loops if the answer is yes, stops the loop if not
        pause();
    }

    static void addStudent() throws Exception {
        if (studentCount < maxStudents) { // if studentcount is less than 50 or the limit
            String name = onlyLetters("Enter student name: "); // only accepts letters
            int numOfSubs1 = onlySubjectAmount("Enter amount of subjects: "); // only accepts number 1-9
            String[] subs1 = new String[numOfSubs1]; // gets the user input of amount of subjects and stores into the
                                                     // local array
            double[] g1 = new double[numOfSubs1];
            System.out.println("SEMESTER 1:");
            System.out.println("TYPE CANCEL TO CANCEL");
            for (int i = 0; i < numOfSubs1; i++) { // loops the same amount of times of what the value of numOfSubs
                subs1[i] = onlySubject("Enter Subject Name: "); // gets subject name
                if (subs1[i] == null) { // if the user cancels
                    pause();
                    return; // exit
                }
                g1[i] = onlyGrades("Enter grade: ");
                if (g1[i] == -1) { // if the user cancels
                    pause();
                    return; // exit
                }
            }
            System.out.println("SEMESTER 2: ");
            int numOfSubs2 = onlySubjectAmount("Enter amount of subjects: ");
            String[] subs2 = new String[numOfSubs2];
            double[] g2 = new double[numOfSubs2];
            for (int j = 0; j < numOfSubs2; j++) {
                subs2[j] = onlySubject("Enter Subject Name: ");
                if (subs2[j] == null) {
                    pause();
                    return;
                }
                g2[j] = onlyGrades("Enter grade: ");
                if (g2[j] == -1) {
                    pause();
                    return;
                }
            }
            System.out.println("Student saved.");
            saveStudent(name, subs1, g1, subs2, g2); // passes the arrays to save stydent
            pause();
        } else {
            System.out.println("Students are full!");
            pause();
        }
    }

    static void updateStudent() throws Exception {
        int i = selectStudent(); // select the student and get the row position
        if (i == -1) {
            pause();
            return;
        }
        System.out.println("\nStudent: " + studentName[i]);
        printStudent(i);
        String semSelection = "";
        // input validation for the user to only enter 1-2
        do {
            semSelection = onlyNumbers("Enter semester(1-2): ");
            if (!semSelection.equals("1") && !semSelection.equals("2")) {
                System.out.println("Only enter 1 or 2 for semester.");
            }
        } while (!semSelection.equals("1") && !semSelection.equals("2"));
        double[] grade; // grade array
        String[] subject; // subject array
        if (semSelection.equals("1")) {
            grade = sem1[i]; // stores the grade column
            subject = sem1Subjects[i]; // stores the subject column
        } else {
            grade = sem2[i];
            subject = sem2Subjects[i];
        }
        int count = 0; // to count for amount of subjects
        for (int j = 0; j < maxSubjects; j++) {
            if (grade[j] != blank) { // if the grade exist
                System.out.println((j + 1) + ". " + subject[j] + " - " + grade[j]); // output the subject name and grade
                count++; // increases the count
            }
        }
        int index = 0; // the value of the array position
        // input validation that also storess the index position to ask the user the
        // subject number
        boolean valid = false;
        while (!valid) {
            String pick = onlyNumbers("Enter subject number: ");
            int value = Integer.parseInt(pick);
            if (value >= 1 && value <= count) { // if the subject number is 1 and it is less or equal to the number of
                                                // subjects
                index = value - 1; // index will be set to vavlue. -1 because arrays always starts at 0
                valid = true; // breaks the loop
            }
            if (!valid) { // if the user enters beyond the number or negative
                System.out.println("Enter a number from 1 to " + count + ".");
            } // loops
        }
        System.out.println("Updating " + subject[index] + " (" + grade[index] + ")");
        double newGrade = onlyGrades("Enter new grade: "); // gets the new grade
        grade[index] = newGrade;
        System.out.println("Grade updated.");
        pause();
    }

    static void deleteStudent() throws Exception {
        int i = selectStudent(); // select the student and get the row position
        if (i == -1) {
            pause();
            return;
        }
        System.out.println("\nStudent: " + studentName[i]);
        printStudent(i);

        String answer;
        do {
            System.out.print("Delete this student?(y/n): ");
            answer = input.readLine().toLowerCase().trim();
        } while (!answer.equals("y") && !answer.equals("n"));
        if (answer.equals("n")) {
            System.out.println("Cancelled. Nothing was deleted.");
            pause();
            return;
        }
        int last = studentCount - 1; // shifts and overwrites the selected student
        studentName[i] = studentName[last];
        for (int j = 0; j < maxSubjects; j++) {
            sem1[i][j] = sem1[last][j];
            sem2[i][j] = sem2[last][j];
            sem1Subjects[i][j] = sem1Subjects[last][j];
            sem2Subjects[i][j] = sem2Subjects[last][j];
        }
        for (int r = i; r < studentCount; r++) { // after deleting move every student after the student position up by 1
                                                 // because of the overwrite
            studentName[r] = studentName[r + 1];
            for (int j = 0; j < studentCount; j++) {
                sem1[r][j] = sem1[r + 1][j];
                sem2[r][j] = sem2[r + 1][j];
                sem1Subjects[r][j] = sem1Subjects[r + 1][j];
                sem2Subjects[r][j] = sem2Subjects[r + 1][j];
            }
        }
        studentCount--;
        System.out.println("Student deleted.");
        pause();
    }

}
