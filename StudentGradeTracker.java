import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    private static final int SUBJECT_COUNT = 5;
    private static final String[] SUBJECTS = {"Java", "Database", "Web", "Mathematics", "Communication"};
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadSampleStudents();
        boolean running = true;

        System.out.println("========================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("========================================");

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Class Summary");
            System.out.println("5. Exit");

            int choice = readInt("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> searchStudent();
                case 4 -> displaySummary();
                case 5 -> running = false;
            }
        }
        System.out.println("Thank you for using Student Grade Tracker.");
        scanner.close();
    }

    private static void loadSampleStudents() {
        students.add(new Student("Ananya", new double[]{88, 92, 85, 90, 86}));
        students.add(new Student("Rahul", new double[]{72, 68, 75, 70, 74}));
        students.add(new Student("Priya", new double[]{95, 91, 93, 96, 94}));
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        String name;
        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name cannot be empty.");
        } while (name.isEmpty());

        double[] marks = new double[SUBJECT_COUNT];
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            marks[i] = readDouble(SUBJECTS[i] + " marks (0-100): ", 0, 100);
        }
        students.add(new Student(name, marks));
        System.out.println("Student added successfully.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n---------------- STUDENT REPORT ----------------");
        System.out.printf("%-18s %-10s %-10s %-8s%n", "Name", "Total", "Average", "Grade");
        System.out.println("-------------------------------------------------");
        for (Student s : students) {
            System.out.printf("%-18s %-10.2f %-10.2f %-8s%n",
                    s.getName(), s.getTotal(), s.getAverage(), s.getGrade());
        }
    }

    private static void searchStudent() {
        System.out.print("Enter name to search: ");
        String query = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(query)) {
                System.out.printf("%s - Average: %.2f - Grade: %s%n",
                        s.getName(), s.getAverage(), s.getGrade());
                found = true;
            }
        }
        if (!found) System.out.println("No matching student found.");
    }

    private static void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double totalAverage = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student s : students) {
            totalAverage += s.getAverage();
            if (s.getAverage() > highest.getAverage()) highest = s;
            if (s.getAverage() < lowest.getAverage()) lowest = s;
        }

        System.out.println("\n------------- CLASS SUMMARY -------------");
        System.out.println("Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", totalAverage / students.size());
        System.out.printf("Highest Average: %s (%.2f)%n", highest.getName(), highest.getAverage());
        System.out.printf("Lowest Average: %s (%.2f)%n", lowest.getName(), lowest.getAverage());
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a valid option.");
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }
}
