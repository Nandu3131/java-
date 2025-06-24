
import java.sql.*;
import java.util.*;

class Course {
    int courseId;
    String name;

    public Course(int courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Name: " + name;
    }
}

public class StudentCourse {

    static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    static final String USER = "root";
    static final String PASS = "root";
    static final String Driver = "com.mysql.jdbc.Driver";

    private Connection conn;
    private Scanner scanner = new Scanner(System.in);
    private HashMap<Integer, Course> courseCache = new HashMap<>();

    public static void main(String[] args) {
        try {
            Class.forName(Driver);
            StudentCourse system = new StudentCourse();
            system.connect();
            system.menu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to database successfully.");
    }

    void menu() throws SQLException {
        while (true) {
            System.out.println("\n--- Student Course Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. View Student Enrollments");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 : {
                    addStudent();
                    break;
                }
                case 2 : {
                    addCourse();
                    break;
                }
                case 3 : {
                    enrollStudent();
                    break;
                }
                case 4 : {
                    viewStudents();
                    break;
                }
                case 5 : {
                    viewCourses();
                    break;
                }
                case 6 : {
                    viewEnrollments();
                    break;
                }
                case 7 : {
                    conn.close();
                    return;
                }
                default : System.out.println("Invalid choice.");
            }
        }
    }

    void addStudent() throws SQLException {
        System.out.print("Student Name: ");
        String name = scanner.nextLine();
        String sql = "INSERT INTO students (name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Student added successfully.");
    }

    void addCourse() throws SQLException {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        String sql = "INSERT INTO courses (name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Course added successfully.");
    }

    void enrollStudent() throws SQLException {
        System.out.print("Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Course ID: ");
        int courseId = scanner.nextInt();

        if (!courseCache.containsKey(courseId)) {
            loadCourseToCache(courseId);
        }

        if (courseCache.containsKey(courseId)) {
            String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Enrollment successful into course: " + courseCache.get(courseId).name);
        } else {
            System.out.println("Course ID not found in database.");
        }
    }

    void viewStudents() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
        System.out.println("\nStudents:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("student_id") + ", Name: " + rs.getString("name"));
        }
    }

    void viewCourses() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM courses");
        System.out.println("\nCourses:");
        while (rs.next()) {
            int id = rs.getInt("course_id");
            String name = rs.getString("name");
            courseCache.put(id, new Course(id, name));
            System.out.println("ID: " + id + ", Name: " + name);
        }
    }

    void viewEnrollments() throws SQLException {
        String sql = "SELECT s.name AS student_name, c.name AS course_name FROM enrollments e " +
                "JOIN students s ON e.student_id = s.student_id " +
                "JOIN courses c ON e.course_id = c.course_id";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        System.out.println("\nEnrollments:");
        while (rs.next()) {
            System.out.println("Student: " + rs.getString("student_name") + " -> Course: " + rs.getString("course_name"));
        }
    }

    void loadCourseToCache(int courseId) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, courseId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            courseCache.put(courseId, new Course(courseId, rs.getString("name")));
        }
    }
} // End of class

