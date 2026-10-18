import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Main {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";
    public static void main(String[] args) {
        loadStudents();
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Generate Student Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = readInt();
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice! Enter 1 to 7.");
                continue;
            }
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    StudentReportTask reportTask =
                            new StudentReportTask();
                    reportTask.start();
                    break;
                case 7:
                    saveStudents();
                    System.out.println(
                            "Thank you for using Student Management System!");
                    break;
            }
        } while (choice != 7);
        sc.close();
    }
    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Enter a whole number.");
            sc.nextLine();
            System.out.print("Try again: ");
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
    static double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input! Enter a number.");
            sc.nextLine();
            System.out.print("Try again: ");
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = readInt();
        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();
        if (name.isEmpty() || course.isEmpty()) {
            System.out.println("Name and Course cannot be empty.");
            return;
        }
        System.out.print("Enter Age: ");
        int age = readInt();
        if (age <= 0 || age > 120) {
            System.out.println("Age must be between 1 and 120.");
            return;
        }
        System.out.print("Enter Marks (0-100): ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }
        students.add(new Student(id, name, course, age, marks));
        saveStudents();
        System.out.println("Student added successfully!");
    }
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n===== ALL STUDENTS =====");
        for (Student s : students) {
            System.out.println("--------------------");
            s.displayStudent();
        }
    }
    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = readInt();
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.println("Student found!");
                s.displayStudent();
                return;
            }
        }
        System.out.println("Student not found.");
    }
    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = readInt();
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.print("Enter new Name: ");
                String name = sc.nextLine().trim();
                System.out.print("Enter new Course: ");
                String course = sc.nextLine().trim();
                if (name.isEmpty() || course.isEmpty()) {
                    System.out.println(
                            "Name and Course cannot be empty.");
                    return;
                }
                System.out.print("Enter new Age: ");
                int age = readInt();
                if (age <= 0 || age > 120) {
                    System.out.println("Invalid age.");
                    return;
                }
                System.out.print("Enter new Marks (0-100): ");
                double marks = readDouble();
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks.");
                    return;
                }
                s.setName(name);
                s.setCourse(course);
                s.setAge(age);
                s.setMarks(marks);
                saveStudents();
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = readInt();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == id) {
                students.remove(i);
                saveStudents();
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    static void saveStudents() {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(
                        s.getStudentId() + "|" +
                                s.getName() + "|" +
                                s.getCourse() + "|" +
                                s.getAge() + "|" +
                                s.getMarks()
                );
            }
        } catch (IOException e) {
            System.out.println(
                    "Error saving student records: " + e.getMessage());
        }
    }
    static void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String course = data[2];
                    int age = Integer.parseInt(data[3]);
                    double marks = Double.parseDouble(data[4]);
                    students.add(
                            new Student(id, name, course, age, marks));
                }
            }
            System.out.println("Student records loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println(
                    "Error loading records: " + e.getMessage());
        }
    }
}
