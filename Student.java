public class Student implements RecordOperations {
    private int studentId;
    private String name;
    private String course;
    private int age;
    private double marks;
    public Student(int studentId, String name, String course,
                   int age, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.age = age;
        this.marks = marks;
    }
    public int getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public String getCourse() {
        return course;
    }
    public int getAge() {
        return age;
    }
    public double getMarks() {
        return marks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    @Override
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
    }
}
