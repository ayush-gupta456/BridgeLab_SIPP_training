public class PostgraduateStudent extends Student {
    public PostgraduateStudent(int roll, String name, double cgpa) {
        super(roll, name, cgpa);
    }

    public void displayDetails() {
        System.out.println("Roll: " + rollNumber + ", Name: " + name);
    }
}