import java.util.ArrayList;
import java.util.List;
interface GradeCalculator {
    double calculateGrade(int score);
}
class StandardGradeCalculator implements GradeCalculator {
    @Override
    public double calculateGrade(int score) {
        if (score >= 90) {
            return 4.0;
        } else if (score >= 80) {
            return 3.0;
        } else if (score >= 70) {
            return 2.0;
        } else if (score >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
class WeightedGradeCalculator implements GradeCalculator {
    private double weight;
    public WeightedGradeCalculator(double weight) {
        this.weight = weight;
    }
    @Override
    public double calculateGrade(int score) {
        return weight * score / 100.0;
    }
}
class Course {
    private String name;
    private int score;
    private int credits;
    public Course(String name, int score, int credits) {
        this.name = name;
        this.score = score;
        this.credits = credits;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getCredits() {
        return credits;
    }
}
class CourseManager {
    private List<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }
    public void addCourse(String name, int score, int credits) {
        Course course = new Course(name, score, credits);
        courses.add(course);
    }
    public List<Course> getCourses() {
        return courses;
    }
}
class Student {
    private CourseManager courseManager;
    private GradeCalculator gradeCalculator;
    public Student() {
        courseManager = new CourseManager();
        gradeCalculator = new StandardGradeCalculator();
    }
    public void addCourse(String name, int score, int credits) {
        courseManager.addCourse(name, score, credits);
    }
    public double calculateGPA() {
        List<Course> courses = courseManager.getCourses();
        if (courses.isEmpty()) {
            return 0.0;
        }
        double totalGradePoints = 0.0;
        int totalCredits = 0;
        for (Course course : courses) {
            double grade = gradeCalculator.calculateGrade(course.getScore());
            totalGradePoints += grade * course.getCredits();
            totalCredits += course.getCredits();
        }
        return totalGradePoints / totalCredits;
    }
}
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.addCourse("Calculus 1", 71, 5);
        student.addCourse("Linear Algebra", 73, 5);
        student.addCourse("Introduction to Programming 1", 74, 5);
        student.addCourse("Information and Communication Technologies", 84, 5);
        student.addCourse("Physical Education", 73, 2);
        student.addCourse("Foreign Language 1 ", 79, 5);

        double gpa = student.calculateGPA();
        System.out.println("GPA: " + gpa);
    }
}