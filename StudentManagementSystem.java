import java.util.*;

// Subject class
class Subject {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Course class
class Course {
    private String name;
    private ArrayList<Subject> subjects;

    public Course(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSubject(Subject s) {
        subjects.add(s);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void displaySubjects() {
        for (Subject s : subjects) {
            System.out.print(s.getName() + " ");
        }
    }
}

// Student class
class Student {
    private String name;
    private int rollNo;
    private int age;
    private Course course;

    public Student(String name, int rollNo, int age, Course course) {
        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
        this.course = course;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("\nName: " + name + ", Roll No: " + rollNo + ", Age: " + age);
        if (course != null) {
            System.out.println("Course: " + course.getName());
            System.out.print("Subjects: ");
            course.displaySubjects();
            System.out.println();
        } else {
            System.out.println("Course: Not assigned");
        }
    }
}

// Management class
class StudentManager {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public StudentManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addCourse(String name) {
        courses.add(new Course(name));
        System.out.println("Course added successfully!");
    }

    public void addSubjectToCourse(String courseName, String subjectName) {
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(courseName)) {
                c.addSubject(new Subject(subjectName));
                System.out.println("Subject added to course!");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void addStudent(String name, int rollNo, int age, String courseName) {
        Course assignedCourse = null;
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(courseName)) {
                assignedCourse = c;
                break;
            }
        }
        if (assignedCourse == null) {
            System.out.println("Course not found. Student not added.");
            return;
        }
        students.add(new Student(name, rollNo, age, assignedCourse));
        System.out.println("Student added successfully!");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student s : students) {
            s.displayInfo();
        }
    }

    public void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course c : courses) {
            System.out.println("Course: " + c.getName() + ", Subjects: ");
            c.displaySubjects();
            System.out.println();
        }
    }
}

// Main class
public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Course");
            System.out.println("2. Add Subject to Course");
            System.out.println("3. Add Student");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course name: ");
                    String courseName = sc.nextLine();
                    manager.addCourse(courseName);
                    break;

                case 2:
                    System.out.print("Enter course name to add subject to: ");
                    String cName = sc.nextLine();
                    System.out.print("Enter subject name: ");
                    String subjectName = sc.nextLine();
                    manager.addSubjectToCourse(cName, subjectName);
                    break;

                case 3:
                    System.out.print("Enter student name: ");
                    String sName = sc.nextLine();
                    System.out.print("Enter roll number: ");
                    int roll = sc.nextInt();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter course to assign: ");
                    String assignCourse = sc.nextLine();
                    manager.addStudent(sName, roll, age, assignCourse);
                    break;

                case 4:
                    manager.viewStudents();
                    break;

                case 5:
                    manager.viewCourses();
                    break;

                case 6:
                    System.out.println("Exiting system.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
