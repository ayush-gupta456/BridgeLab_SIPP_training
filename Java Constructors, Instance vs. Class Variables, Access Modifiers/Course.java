public class Course {
    private String courseName;
    private int duration;
    private double fee;
    private static String instituteName = "GLA University";

    public Course(String courseName, int duration, double fee) {
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
    }

    public void displayCourseDetails() {
        System.out.println(courseName + ", " + duration + " weeks, Rs." + fee + ", Institute: " + instituteName);
    }

    public static void updateInstituteName(String newName) {
        instituteName = newName;
    }
}