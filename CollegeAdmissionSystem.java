import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class Student {
    int id;
    String name;
    double marks;
    String courseApplied;
    String status;

    Student(int id, String name, double marks, String courseApplied) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.courseApplied = courseApplied;
        this.status = "Pending";
    }
}

class Course {
    String courseName;
    double cutOff;

    Course(String courseName, double cutOff) {
        this.courseName = courseName;
        this.cutOff = cutOff;
    }
}

public class CollegeAdmissionSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static int studentIdCounter = 1;

    public static void main(String[] args) {

        addDefaultCourses();

        while (true) {
            System.out.println("\n===== COLLEGE ADMISSION MANAGEMENT SYSTEM =====");
            System.out.println("1. Student Registration");
            System.out.println("2. View Courses");
            System.out.println("3. Admin Approval (Merit Based)");
            System.out.println("4. View Admission List");
            System.out.println("5. Generate Admission CSV");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    adminApproval();
                    break;
                case 4:
                    viewAdmissionList();
                    break;
                case 5:
                    generateCSV();
                    break;
                case 6:
                    System.out.println("Thank you! Application closed.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addDefaultCourses() {
        courses.add(new Course("Computer Science", 75));
        courses.add(new Course("Mechanical Engineering", 65));
        courses.add(new Course("Electrical Engineering", 60));
        courses.add(new Course("Civil Engineering", 55));
    }

    static void registerStudent() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks (%): ");
        double marks = sc.nextDouble();
        sc.nextLine();

        viewCourses();
        System.out.print("Apply for Course: ");
        String course = sc.nextLine();

        students.add(new Student(studentIdCounter++, name, marks, course));
        System.out.println("✅ Registration Successful!");
    }

    static void viewCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            System.out.println("- " + c.courseName + " (Cut-off: " + c.cutOff + "%)");
        }
    }

    static void adminApproval() {
        for (Student s : students) {
            for (Course c : courses) {
                if (s.courseApplied.equalsIgnoreCase(c.courseName)) {
                    if (s.marks >= c.cutOff) {
                        s.status = "Approved";
                    } else {
                        s.status = "Rejected";
                    }
                }
            }
        }
        System.out.println("✅ Merit evaluation completed!");
    }

    static void viewAdmissionList() {
        System.out.println("\n===== ADMISSION LIST =====");
        for (Student s : students) {
            System.out.println(
                "ID: " + s.id +
                ", Name: " + s.name +
                ", Marks: " + s.marks +
                ", Course: " + s.courseApplied +
                ", Status: " + s.status
            );
        }
    }

    static void generateCSV() {
        try {
            FileWriter fw = new FileWriter("AdmissionList.csv");
            fw.write("ID,Name,Marks,Course,Status\n");

            for (Student s : students) {
                fw.write(
                    s.id + "," +
                    s.name + "," +
                    s.marks + "," +
                    s.courseApplied + "," +
                    s.status + "\n"
                );
            }

            fw.close();
            System.out.println("✅ CSV file generated successfully!");

        } catch (IOException e) {
            System.out.println("❌ Error generating CSV file.");
        }
    }
}